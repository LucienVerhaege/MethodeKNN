package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import enums.Category;
import utils.GenericLoadingUtil;

public class ExecutionSample {
	
	private static final String URLP = "res/pokemon_train.csv";
	private static final String URLI = "res/iris.csv";
	private static final String URLT = "res/titanic.csv";
	
	
	public static List<Double> main() {
		//INIT DATA
		MVCModel model = new MVCModel(Category.POKEMON);
		model.loadFromFile(URLP);
		MVCModel model2 = new MVCModel(Category.IRIS);
		model2.loadFromFile(URLI);
		MVCModel model3 = new MVCModel(Category.TITANICP);
		model3.loadFromFile(URLT);
		
		//TEST DATA
		GenericLoadingUtil loader = LoadingUtilFactory.createLoadingUtil(Category.POKEMON);
		try {
			loader.loadData("res/pokemon_test.csv", Pokemon.class);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		GenericLoadingUtil loader2 = LoadingUtilFactory.createLoadingUtil(Category.IRIS);
		try {
			loader2.loadData("res/iris_test.csv", Iris.class);
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		
		GenericLoadingUtil loader3 = LoadingUtilFactory.createLoadingUtil(Category.TITANICP);
		try {
			loader3.loadData("res/titanic_test.csv", TitanicP.class);
		} catch (IOException e3) {
			e3.printStackTrace();
		}
		
		List<Double> lRob = new ArrayList<>();
		
		lRob.add(model.computeRobustness("res/pokemon_test.csv", 5));
		
		lRob.add(model2.computeRobustness("res/iris_test.csv", 3));
		
		lRob.add(model3.computeRobustness("res/titanic_test.csv", 7));
		
		return lRob;
	}
}