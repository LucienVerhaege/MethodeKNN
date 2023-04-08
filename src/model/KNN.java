package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import computing.IDistance;
import enums.Category;
import interfaces.IEntity;
import utils.GenericLoadingUtil;

public class KNN {

	private static final String SURVIVOR = "Survivor";
	private static final String LEGENDARY = "Legendary";
	protected List<IEntity> data;
	protected IDistance de;
	public Category category;
	protected GenericLoadingUtil loader;
	
	public KNN (GenericLoadingUtil loader, IDistance de, Category cat){
		this.loader = loader;
		data = loader.getData();
		this.de = de;
		this.category = cat;
	}
	
	public Map<Double, IEntity> computeDist(IEntity entity){
		Map<Double, IEntity> map = new HashMap<>();
		de.setAmplifiers(data);
		for(IEntity entity2 : data) {
			map.put(de.computeEuclideanBalancedDistance(entity, entity2), entity2);
		}
		return map;
	}
	
	public String knn(int k, IEntity entity) {
		data = loader.getData();
		Map<Double, IEntity> dist = computeDist(entity);
		ArrayList<IEntity> nearestNeighb = new ArrayList<>();
		IEntity grabbedEntity;
		for(int i = 0; i < k; i++) {
			grabbedEntity = dist.remove(Collections.min(dist.keySet()));
			nearestNeighb.add(grabbedEntity);
		}
		String res=computeResult(nearestNeighb);
		//NRC Stand for No Result Computed
		return res==null?"NRC":res;
	}



	public double computeRobustness(String url, int k, Class c){
		double validData = 0;
		double total = 0;
		GenericLoadingUtil load = LoadingUtilFactory.createLoadingUtil(category);
		try{
			load.loadData(url, c);
		} catch(IOException e){
			System.err.println(e.getMessage());
			return 0;
		}
		for(IEntity ent : load.getData()){
			if(category.equals(Category.POKEMON)){
				Pokemon pkm = (Pokemon) ent;
				if(pkm.getIsLegendary() == knn(k, ent).equals(LEGENDARY)) validData = validData + 1;
			} else if(category.equals(Category.IRIS)){
				Iris iris = (Iris) ent;
				String result = knn(k, ent);
				if(iris.getVariety().equals(result)) validData = validData + 1;
			} else if(category.equals(Category.TITANICP)){
				TitanicP titanicp = (TitanicP) ent;
				if(titanicp.getSurvived() == ( knn(k, ent).equals(SURVIVOR)?1:0 )) validData = validData + 1;
			}
			total = total + 1;
		}
		return (validData*100) / total;
	}

	private String computeResult(ArrayList<IEntity> nearestNeighb) {
		String result = "";
		if(category.equals(Category.POKEMON)){
			result = resultPokemon(nearestNeighb);
		} else if(category.equals(Category.IRIS)){
			result = resultIris(nearestNeighb);
		} else if(category.equals(Category.TITANICP)){
			result = resultTitanic(nearestNeighb);
		}
		return result;
	}

	private String resultPokemon(ArrayList<IEntity> nearestNeighb){
		int legendaryCount = 0;
		int notLegendaryCount = 0;
		Random rnd = new Random();
		Pokemon pkm;
		String result = null;
		for(IEntity ent : nearestNeighb){
			pkm = (Pokemon) ent;
			if(pkm.is_legendary) {
				legendaryCount = legendaryCount + 1;
			}
			else {
				notLegendaryCount = notLegendaryCount + 1;
			}
		}
		if(legendaryCount == notLegendaryCount && (legendaryCount > 0 || notLegendaryCount > 0)){
			result = rnd.nextBoolean()?LEGENDARY:"NotLegendary";
		} else {
			result = legendaryCount>notLegendaryCount?LEGENDARY:"NotLegendary";
		}
		return result;
	}

	private String resultIris(ArrayList<IEntity> nearestNeighb){
		String result = null;
		HashMap<Iris, Integer> neighCount = new HashMap<>();
		Iris grabbedEntity;
		for(IEntity ent : nearestNeighb){
			grabbedEntity = (Iris) ent;
			neighCount.putIfAbsent(grabbedEntity, 1);
			neighCount.computeIfPresent(grabbedEntity, 
				(key, value) -> value = value + 1 );
		}
		Iterator<Entry<Iris, Integer>> it = neighCount.entrySet().stream().sorted(Map.Entry.comparingByValue()).iterator();
		while(it.hasNext()){
			grabbedEntity = it.next().getKey();
			result =  grabbedEntity.getVariety();
		}
		return result;
	}
	
	private String resultTitanic(ArrayList<IEntity> nearestNeighb){
		Random rnd = new Random();
		int survivorCount = 0;
		int notSurvivorCount = 0;
		TitanicP passenger;
		String result = null;
		for(IEntity ent : nearestNeighb){
			passenger = (TitanicP) ent;
			if(passenger.survived==1) {
				survivorCount = survivorCount + 1;
			}
			else {
				notSurvivorCount = notSurvivorCount + 1;
			}
		}
		if(survivorCount == notSurvivorCount && (survivorCount > 0 || notSurvivorCount > 0)){
			result = rnd.nextBoolean()?SURVIVOR:"NotSurvivor";
		} else {
			result = survivorCount>notSurvivorCount?SURVIVOR:"NotSurvivor";
		}
		return result;
	}

}
