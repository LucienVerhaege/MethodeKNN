package sae;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import computing.DistancePokemon;
import enums.Category;
import model.KNN;
import model.LoadingUtilFactory;

class KNNTest {

	@Test
	void testCreateKNN() {
		KNN k = new KNN(LoadingUtilFactory.createLoadingUtil(Category.POKEMON), new DistancePokemon(), Category.POKEMON);
		assertEquals(Category.POKEMON, k.category);
	}

}
