package sae;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import enums.Category;
import model.MVCModel;

class MVCModelTest {

	private static final String POKEMON_TRAIN_CSV = "res/pokemon_train.csv";
	private static final String ATTACK = "attack";

	@Test
	void testMVCAllocateAttributes() {
		MVCModel pokemon = new MVCModel(Category.POKEMON);
		assertEquals(Category.POKEMON, pokemon.getCurrentCategory());
		
		MVCModel titanic = new MVCModel(Category.TITANICP);
		assertEquals(Category.TITANICP, titanic.getCurrentCategory());
		
		MVCModel iris = new MVCModel(Category.IRIS);
		assertEquals(Category.IRIS, iris.getCurrentCategory());
	}
	
	@Test
	void testMVCDataClass() {
		MVCModel pokemon = new MVCModel(Category.POKEMON);
		pokemon.loadFromFile(POKEMON_TRAIN_CSV);
		
		assertEquals("NotLegendary",pokemon.returnDataClass(pokemon.getData().get(0)));
		
		MVCModel iris = new MVCModel(Category.IRIS);
		iris.loadFromFile("res/iris.csv");
		
		assertEquals("Setosa",iris.returnDataClass(iris.getData().get(0)));
		
		MVCModel titanic = new MVCModel(Category.TITANICP);
		titanic.loadFromFile("res/titanic.csv");
		
		assertEquals("NotSurvivor",titanic.returnDataClass(titanic.getData().get(0)));
	}
	
	@Test
	void testDefaultColumnOccurrence() {
		MVCModel pokemon = new MVCModel(Category.POKEMON);
		pokemon.loadFromFile(POKEMON_TRAIN_CSV);
		
		assertEquals(0, pokemon.currentColumnXInt());
		assertEquals(1, pokemon.currentColumnYInt());
	}
	
	@Test
	void testColumnAxis() {
		MVCModel pokemon = new MVCModel(Category.POKEMON);
		pokemon.loadFromFile(POKEMON_TRAIN_CSV);
		
		assertEquals(185.0, pokemon.computeMaxAxis(ATTACK));
		assertEquals(5.0, pokemon.computeMinAxis(ATTACK));
	}
	
	@Test
	void testSetColumn() {
		MVCModel pokemon = new MVCModel(Category.POKEMON);
		pokemon.loadFromFile(POKEMON_TRAIN_CSV);
		
		pokemon.setDefaultXCol(ATTACK);
		pokemon.setDefaultYCol(ATTACK);
		
		assertEquals(ATTACK, pokemon.defaultX.getName());
		assertEquals(ATTACK, pokemon.defaultY.getName());
	}
}
