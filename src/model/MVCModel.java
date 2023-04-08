package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import computing.ColNormalizer;
import computing.ColumnIris;
import computing.ColumnPokemon;
import computing.ColumnTitanic;
import computing.DistanceIris;
import computing.DistancePokemon;
import computing.DistanceTitanic;
import computing.IDistance;
import computing.IrisColumnNormalizer;
import computing.PokemonColumnNormalizer;
import computing.TitanicPColumnNormalizer;
import enums.Category;
import interfaces.IColumn;
import interfaces.IEntity;
import interfaces.IMVCModel;
import utils.GenericLoadingUtil;
import utils.Subject;

public class MVCModel extends Subject implements IMVCModel{
	
	protected Category catg;
	protected GenericLoadingUtil loader;
	protected List<IColumn> columns;
	protected KNN knnComp;
	protected IDistance distComp;
	protected Class<?> cls;
	protected ColNormalizer normalizer;
	public IColumn defaultX;
	public IColumn defaultY;
	protected HashMap<Integer, String> colDictionary;
	
	public MVCModel(Category c) {
		this.catg = c;
		try {
			allocateAttributes();
		}
		catch(IOException e) {
			System.err.println(e.getMessage());
		}
	}
	
	private void allocateAttributes() throws IOException {
		this.loader = LoadingUtilFactory.createLoadingUtil(catg);
		this.columns = new ArrayList<>();
		switch(catg) {
			case POKEMON:
				this.distComp = new DistancePokemon();
				this.knnComp = new KNN(loader, distComp, catg);
				cls = Pokemon.class;
				this.normalizer = new PokemonColumnNormalizer();
				this.colDictionary = Pokemon.column;
				for(int i = 0; i < Pokemon.COLUMN_COUNT; i++) {
					this.columns.add(new ColumnPokemon(i, this));
				}
				break;
			case IRIS:
				this.distComp = new DistanceIris();
				this.knnComp = new KNN(loader, distComp, catg);
				cls = Iris.class;
				this.normalizer = new IrisColumnNormalizer();
				this.colDictionary = Iris.column;
				for(int i = 0; i < Iris.COLUMN_COUNT; i++) {
					this.columns.add(new ColumnIris(i, this));
				}
				break;
			case TITANICP:
				this.distComp = new DistanceTitanic();
				this.knnComp = new KNN(loader, distComp, catg);
				cls = TitanicP.class;
				this.normalizer = new TitanicPColumnNormalizer();
				this.colDictionary = TitanicP.column;
				for(int i = 0; i < TitanicP.COLUMN_COUNT; i++) {
					this.columns.add(new ColumnTitanic(i, this));
				}
				break;
		}
		this.defaultX = columns.get(0);
		this.defaultY = columns.get(1);
	}

	@Override
	public void loadFromFile(String datafile) {
		try {
			loader.loadData(datafile, cls);
			if(catg.equals(Category.IRIS)) {
				IrisColumnNormalizer normalizerIris = (IrisColumnNormalizer) this.normalizer;
				normalizerIris.initializeCategories(loader.getData());
				this.normalizer = normalizerIris;
			}
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
	
	@Override
	public void loadFromString(String data) {
		loader.loadDataFromString(data, cls);
	}
	
	public void setDefaultXCol(String colName) {
		IColumn def = columns.get(findColumnOccurrence(colName));
		this.defaultX = def;
	}
	
	@Override
	public IColumn defaultXCol() {
		return this.defaultX;
	}
	
	public int currentColumnXInt() {
		return findColumnOccurrence(defaultX.getName());
	}
	
	public void setDefaultYCol(String colName) {
		IColumn def = columns.get(findColumnOccurrence(colName));
		this.defaultY = def;
	}
	
	@Override
	public IColumn defaultYCol() {
		return this.defaultY;
	}
	
	public int currentColumnYInt() {
		return findColumnOccurrence(defaultY.getName());
	}

	@Override
	public Category getCurrentCategory() {
		return catg;
	}
	@Override
	public String getTitle() {
		return catg.name();
	}
	@Override
	public int getNbLines() {
		return loader.getLinesN();
	}
	@Override
	public void switchCategory(Category classe) {
		this.catg = classe;
		try {
			allocateAttributes();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public List<IEntity> getData(){
		return this.loader.getData();
	}
	
	public void useKnn(int k, IEntity entity) {
		this.notifyObservers(Map.entry(this.knnComp.knn(k, entity), entity));
		}
	
	public double computeRobustness(String url, int k) {
		return this.knnComp.computeRobustness(url, k, cls);
	}
	
	public double computeMaxAxis(String name) {
		double result = 0.0;
		for(IColumn col : this.columns) {
			if(col.getName().equals(name)) {
				result = col.computeMaxValue();
			}
		}
		return result;
	}
	
	public double computeMinAxis(String name) {
		double result = 0.0;
		for(IColumn col : this.columns) {
			if(col.getName().equals(name)) {
				result = col.computeMinValue();
			}
		}
		return result;
	}
	
	public List<IColumn> getColumns(){
		return this.columns;
	}
	
	//LF column occurrence in dictionary
	private int findColumnOccurrence(String colName) {
		int resultedColumn = -1;
		for(Entry<Integer, String> e : this.colDictionary.entrySet()) {
			if(colName.equals(e.getValue())) {
				resultedColumn = e.getKey();
			}
		}
		return resultedColumn;
	}
	
	private String findVarietyOccurrence(IEntity ent) {
		String resultedColumn = "NRC";
		Set<Entry<String,Double>> varieties = ((IrisColumnNormalizer) normalizer).getVariety();
		int lastIndex = ent.columnsValue().size()-1;
		for(Entry<String, Double> e : varieties) {
			if(e.getKey().equals(ent.columnsValue().get(lastIndex))) {
				resultedColumn = e.getKey();
			}
		}
		return resultedColumn;
	}
	
	public double getNormalizedXValue(IEntity ent) {
		return this.defaultX.getNormalizedValue(ent);
	}
	
	public double getNormalizedYValue(IEntity ent) {
		return this.defaultY.getNormalizedValue(ent);
	}
	
	public String returnDataClass(IEntity ent) {
		IColumn last = this.columns.get(columns.size()-1);
		double classV;
		String result = "NRC";
		switch(catg) {
		case POKEMON:
			classV = last.getNormalizedValue(ent);
			result = classV==0?"NotLegendary":"Legendary";
			break;
		case IRIS:
			result = findVarietyOccurrence(ent);
			break;
		case TITANICP:
			classV = last.getNormalizedValue(ent);
			result = classV==0?"NotSurvivor":"Survivor";
			break;
		}
		return result;
	}
}
