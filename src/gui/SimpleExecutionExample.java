package gui;

import java.io.IOException;

import enums.Category;
import interfaces.IEntity;
import model.Iris;
import model.LoadingUtilFactory;
import model.MVCModel;
import model.Pokemon;
import model.TitanicP;
import utils.GenericLoadingUtil;

public class SimpleExecutionExample {

	private static final String URLP = "res/pokemon_train.csv";
	private static final String URLI = "res/iris.csv";
	private static final String URLT = "res/titanic.csv";

	public static void main(String[] args) throws InterruptedException {
		//INIT DATA
		MVCModel model = new MVCModel(Category.POKEMON);
		model.loadFromFile(URLP);
		MVCModel model2 = new MVCModel(Category.IRIS);
		model2.loadFromFile(URLI);
		MVCModel model3 = new MVCModel(Category.TITANICP);
		model3.loadFromFile(URLT);

		//DISPLAY DATA
		for(IEntity e : model.getData()) {
			System.out.println(e + "\n");
		}
		Thread.sleep(5000);
		for(IEntity e : model2.getData()) {
			System.out.println(e + "\n");
		}
		Thread.sleep(5000);
		for(IEntity e : model3.getData()) {
			System.out.println(e + "\n");
		}
		Thread.sleep(5000);
		
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

		System.out.println("Robustness for Pokemons using k = 3 : "+model.computeRobustness("res/pokemon_test.csv", 3)+"%");
		Thread.sleep(2000);

		System.out.println("Robustness for Iris using k = 5 : "+model2.computeRobustness("res/iris_test.csv", 5)+"%");
		Thread.sleep(2000);
		
		System.out.println("Robustness for Iris using k = 7 : "+model3.computeRobustness("res/titanic_test.csv", 7)+"%");
		Thread.sleep(2000);
	}
}
