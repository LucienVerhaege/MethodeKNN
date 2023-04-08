package sae;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import computing.DistanceTitanic;
import enums.Category;
import model.LoadingUtilFactory;
import model.TitanicP;
import utils.GenericLoadingUtil;

class DistanceTitanicTest {

	private static final String TITANIC_TEST_CSV = "res/titanic_test.csv";

	@Test
	void testEuclidean() {
		DistanceTitanic dt = new DistanceTitanic();
		GenericLoadingUtil load = LoadingUtilFactory.createLoadingUtil(Category.TITANICP);
		try {
			load.loadData(TITANIC_TEST_CSV, TitanicP.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertEquals(22.02271554554524, dt.computeEuclideanDistance(load.getData().get(0), load.getData().get(48)));
		assertEquals(3.1622776601683795, dt.computeEuclideanDistance(load.getData().get(2), load.getData().get(23)));
	}
	
	@Test
	void testSetAmplifiersEuclideanBalanced() {
		DistanceTitanic dt = new DistanceTitanic();
		GenericLoadingUtil load = LoadingUtilFactory.createLoadingUtil(Category.TITANICP);
		try {
			load.loadData(TITANIC_TEST_CSV, TitanicP.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertNotEquals(1.009227778849593, dt.computeEuclideanBalancedDistance(load.getData().get(0), load.getData().get(48)));
		dt.setAmplifiers(load.getData());
		assertEquals(1.009227778849593, dt.computeEuclideanBalancedDistance(load.getData().get(0), load.getData().get(48)));
	}
	
	@Test
	void testManhattan() {
		DistanceTitanic dt = new DistanceTitanic();
		GenericLoadingUtil load = LoadingUtilFactory.createLoadingUtil(Category.TITANICP);
		try {
			load.loadData(TITANIC_TEST_CSV, TitanicP.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertEquals(23.0, dt.computeManhattanDistance(load.getData().get(0), load.getData().get(48)));
		assertEquals(6.0, dt.computeManhattanDistance(load.getData().get(2), load.getData().get(23)));
	}

}
