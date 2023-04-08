package sae;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.opencsv.exceptions.CsvDataTypeMismatchException;

import enums.Type;
import model.Personnage;
import model.Pokemon;
import model.TitanicP;
import utils.GenericLoadingUtil;

class CsvLoadingTest {
	
	//Test class of GenericLoadingUtil using the file pokemon_test.csv

	private static final String POKEMON_TEST_CSV = "res/pokemon_test.csv";
	private static final String TROPIUS = "Tropius";

	@Test
	void csvReadTest() {
		boolean fileFound;
		try {
			GenericLoadingUtil load = new GenericLoadingUtil(POKEMON_TEST_CSV, Pokemon.class);
			assertNotEquals(null, load);
			GenericLoadingUtil loadNotAFile = new GenericLoadingUtil("res/pokemon_tesutu.csv", Pokemon.class);
			fileFound = true;
		} catch(Exception e) {
			fileFound = false;
		}
		assertFalse(fileFound);
	}

	@Test
	void csvGetTest() throws IOException {
		GenericLoadingUtil load = new GenericLoadingUtil(POKEMON_TEST_CSV, Pokemon.class);
		assertEquals(TROPIUS, ( (Pokemon) load.getData().get(0)).getName());
		assertEquals(Type.WATER, ((Pokemon) load.getData().get(31)).getType1());
		assertEquals(164, ((Pokemon) load.getData().get(8)).getAttack());
		assertEquals(64, ((Pokemon) load.getData().get(29)).getDefense());
	}
	
	@Test
	void loadingDataTest() {
			GenericLoadingUtil load = new GenericLoadingUtil(Pokemon.class);
			GenericLoadingUtil load2 = new GenericLoadingUtil(Pokemon.class);
			boolean fileFound;
			try {
				load.loadData(POKEMON_TEST_CSV, Pokemon.class);
				assertEquals(TROPIUS, ( (Pokemon) load.getData().get(0)).getName());
				assertEquals(Type.WATER, ((Pokemon) load.getData().get(31)).getType1());
				assertEquals(164, ((Pokemon) load.getData().get(8)).getAttack());
				assertEquals(64, ((Pokemon) load.getData().get(29)).getDefense());
				
				load2.loadDataFromString("name,attack,base_egg_steps,capture_rate,defense,experience_growth,hp,sp_attack,sp_defense,type1,type2,speed,is_legendary\n" + 
						"Tropius,68,6400,200.0,83,1250000,99,87,51,grass,flying,100.0,False\n"+
						"Furret,76,3840,90.0,64,1000000,85,55,90,normal,,32.5,False\n"+
						"Metang,75,10240,3.0,100,1250000,60,80,50,steel,psychic,202.5,False\n"+
						"Wurmple,45,3840,255.0,35,1000000,45,30,20,bug,,3.6,False\n"
						, Pokemon.class);
				assertEquals(TROPIUS, ( (Pokemon) load2.getData().get(0)).getName());
				assertEquals(Type.NORMAL, ((Pokemon) load2.getData().get(1)).getType1());
				assertEquals(75, ((Pokemon) load2.getData().get(2)).getAttack());
				assertEquals(35, ((Pokemon) load2.getData().get(3)).getDefense());
				
				load.loadData("res/pokemon_tesutu.csv", Pokemon.class);
				fileFound = true;
			} catch (Exception e) {
				fileFound = false;
			}
			assertFalse(fileFound);
	}
	
	@Test
	void csvGetlinesTest() throws IOException {
		GenericLoadingUtil load = new GenericLoadingUtil(POKEMON_TEST_CSV, Personnage.class);
		assertEquals(273, load.getLinesN());
	}
	
	@Test
	void csvErrorsTest() throws IOException {
		GenericLoadingUtil loadTitanic = new GenericLoadingUtil("res/titanic.csv", TitanicP.class);
		GenericLoadingUtil loadPersonnages = new GenericLoadingUtil(POKEMON_TEST_CSV, Pokemon.class);
		assertEquals(0, loadPersonnages.getErrors().size());
		assertEquals(CsvDataTypeMismatchException.class, loadTitanic.getErrors().get(0).getClass());
	}
	
	@Test
	void csvNewGenericLoading() throws IOException {
		GenericLoadingUtil loader = new GenericLoadingUtil(POKEMON_TEST_CSV, Pokemon.class);
		assertNotEquals(null, loader.getData());
	}

}
