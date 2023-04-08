package sae;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import computing.DistancePokemon;
import enums.Category;
import model.LoadingUtilFactory;
import model.Pokemon;
import utils.GenericLoadingUtil;

class DistancePokemonTest {

	private static final String POKEMON_TEST_CSV = "res/pokemon_test.csv";

	@Test
	void testEuclidean() {
		DistancePokemon dp = new DistancePokemon();
		GenericLoadingUtil load = LoadingUtilFactory.createLoadingUtil(Category.POKEMON);
		try {
			load.loadData(POKEMON_TEST_CSV, Pokemon.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertEquals(250013.13767208313, dp.computeEuclideanDistance(load.getData().get(0), load.getData().get(100)));
		assertEquals(190157.25557022536, dp.computeEuclideanDistance(load.getData().get(2), load.getData().get(23)));
	}
	
	@Test
	void testSetAmplifiersBalancedEuclidean() {
		DistancePokemon dp = new DistancePokemon();
		GenericLoadingUtil load = LoadingUtilFactory.createLoadingUtil(Category.POKEMON);
		try {
			load.loadData(POKEMON_TEST_CSV, Pokemon.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertNotEquals(84.04805691905628, dp.computeEuclideanBalancedDistance(load.getData().get(0), load.getData().get(100)));
		dp.setAmplifiers(load.getData());
		assertEquals(84.04805691905628, dp.computeEuclideanBalancedDistance(load.getData().get(0), load.getData().get(100)));
	}
	
	@Test
	void testManhattan() {
		DistancePokemon dp = new DistancePokemon();
		GenericLoadingUtil load = LoadingUtilFactory.createLoadingUtil(Category.POKEMON);
		try {
			load.loadData(POKEMON_TEST_CSV, Pokemon.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertEquals(252816.8, dp.computeManhattanDistance(load.getData().get(0), load.getData().get(100)));
		assertEquals(192886.0, dp.computeManhattanDistance(load.getData().get(2), load.getData().get(23)));
	}
}
 