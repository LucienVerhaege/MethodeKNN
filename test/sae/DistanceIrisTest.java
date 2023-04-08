package sae;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import computing.DistanceIris;
import enums.Category;
import model.Iris;
import model.LoadingUtilFactory;
import utils.GenericLoadingUtil;

class DistanceIrisTest {

	private static final String IRIS_TEST_CSV = "res/iris_test.csv";

	@Test
	void euclideanTest() {
		DistanceIris di = new DistanceIris();
		GenericLoadingUtil load = LoadingUtilFactory.createLoadingUtil(Category.IRIS);
		try {
			load.loadData(IRIS_TEST_CSV, Iris.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertEquals(5.301886456724625, di.computeEuclideanDistance(load.getData().get(0), load.getData().get(10)));
		assertEquals(5.472659317004851, di.computeEuclideanDistance(load.getData().get(2), load.getData().get(8)));
	}
	
	@Test
	void manhattanTest() {
		DistanceIris di = new DistanceIris();
		GenericLoadingUtil load = LoadingUtilFactory.createLoadingUtil(Category.IRIS);
		try {
			load.loadData(IRIS_TEST_CSV, Iris.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertEquals(8.9, di.computeManhattanDistance(load.getData().get(0), load.getData().get(10)));
		assertEquals(8.7, di.computeManhattanDistance(load.getData().get(2), load.getData().get(8)));
	}
	
	@Test
	void setAmplifiersTest() {
		DistanceIris di = new DistanceIris();
		GenericLoadingUtil load = LoadingUtilFactory.createLoadingUtil(Category.IRIS);
		try {
			load.loadData(IRIS_TEST_CSV, Iris.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertNotEquals(1.4527474712732784, di.computeEuclideanBalancedDistance(load.getData().get(0), load.getData().get(10)));
		di.setAmplifiers(load.getData());
		assertEquals(1.4527474712732784, di.computeEuclideanBalancedDistance(load.getData().get(0), load.getData().get(10)));
	}
}
