package sae;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.jupiter.api.Test;

import enums.Type;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import model.Pokemon;

class PokemonTest {

	@Test
	void testCreateToString() {
		Pokemon p = new Pokemon("Tropius",68,6400,200.0,83,1250000,99,87,51,Type.GRASS,Type.FLYING,100.0);
		
		assertEquals("Name : Tropius | Attack : 68 | Base Egg Steps : 6400\n"
				+ "Capture Rate : 200.0 | Defense : 83 | Experience Growth : 1250000\n"
				+ "HP : 99 | sp_attack : 87 | sp_defense : 51\n"
				+ "Type n°1 : GRASS | Type n°2 : FLYING | speed : 100.0", p.toString());
		
		assertEquals("Tropius", p.getName());
		assertEquals(68, p.getAttack());
		assertEquals(6400, p.getBaseEggSteps());
		assertEquals(200.0, p.getCaptureRate());
		assertEquals(83, p.getDefense());
		assertEquals(1250000, p.getExperienceGrowth());
		assertEquals(99, p.getHp());
		assertEquals(87, p.getSpAttack());
		assertEquals(51, p.getSpDefense());
		assertEquals(Type.GRASS, p.getType1());
		assertEquals(Type.FLYING, p.getType2());
		assertEquals(100.0, p.getSpeed());
	}
	
	@Test
	void test_createNull_set_get() {
		Pokemon p = new Pokemon();
		
		p.setName("Tropius");
		assertEquals("Tropius", p.getName());
		p.setAttack(68);
		assertEquals(68, p.getAttack());
		p.setBaseEggSteps(6400);
		assertEquals(6400, p.getBaseEggSteps());
		p.setCaptureRate(200.0);
		assertEquals(200.0, p.getCaptureRate());
		p.setDefense(83);
		assertEquals(83, p.getDefense());
		p.setExperienceGrowth(1250000);
		assertEquals(1250000, p.getExperienceGrowth());
		p.setHp(99);
		assertEquals(99, p.getHp());
		p.setSpAttack(87);
		assertEquals(87, p.getSpAttack());
		p.setSpDefense(51);
		assertEquals(51, p.getSpDefense());
		p.setType1(Type.GRASS);
		assertEquals(Type.GRASS, p.getType1());
		p.setType2(Type.FLYING);
		assertEquals(Type.FLYING, p.getType2());
		p.setSpeed(100.0);
		assertEquals(100.0, p.getSpeed());
		p.setIsLegendary(false);
		assertEquals(false, p.getIsLegendary());
	}
	
	@Test
	void testColumnsValue() {
		Pokemon p = new Pokemon("Tropius",68,6400,200.0,83,1250000,99,87,51,Type.GRASS,Type.FLYING,100.0);
		p.setIsLegendary(false);
		List<Object> list = p.columnsValue();
		assertEquals(68, list.get(0));
		assertEquals(6400, list.get(1));
		assertEquals(200.0, list.get(2));
		assertEquals(83, list.get(3));
		assertEquals(1250000, list.get(4));
		assertEquals(99, list.get(5));
		assertEquals(87, list.get(6));
		assertEquals(51, list.get(7));
		assertEquals(Type.GRASS, list.get(8));
		assertEquals(Type.FLYING, list.get(9));
		assertEquals(100.0, list.get(10));
	}
	
	@Test
	void testConstructorTextField() {
		javafx.application.Platform.startup(()->{});
		List<TextField> texts = new ArrayList<>();
		texts.add(new TextField("Tropius"));
		texts.add(new TextField("68"));
		texts.add(new TextField("6400"));
		texts.add(new TextField("200.0"));
		texts.add(new TextField("83"));
		texts.add(new TextField("1250000"));
		texts.add(new TextField("99"));
		texts.add(new TextField("87"));
		texts.add(new TextField("51"));
		texts.add(new TextField("100.0"));
		
		List<MenuButton> buttons = new ArrayList<>();
		buttons.add(new MenuButton("GRASS"));
		buttons.add(new MenuButton("FLYING"));
		try {
			Pokemon p = new Pokemon(texts, buttons);
			assertEquals("Tropius", p.getName());
			assertEquals(68, p.getAttack());
			assertEquals(6400, p.getBaseEggSteps());
			assertEquals(200.0, p.getCaptureRate());
			assertEquals(83, p.getDefense());
			assertEquals(1250000, p.getExperienceGrowth());
			assertEquals(99, p.getHp());
			assertEquals(87, p.getSpAttack());
			assertEquals(51, p.getSpDefense());
			assertEquals(Type.GRASS, p.getType1());
			assertEquals(Type.FLYING, p.getType2());
			assertEquals(100.0, p.getSpeed());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testConstructorTextFieldWithoutComma() {
		
		List<TextField> texts = new ArrayList<>();
		texts.add(new TextField("Tropius"));
		texts.add(new TextField("68"));
		texts.add(new TextField("6400"));
		texts.add(new TextField("200"));
		texts.add(new TextField("83"));
		texts.add(new TextField("1250000"));
		texts.add(new TextField("99"));
		texts.add(new TextField("87"));
		texts.add(new TextField("51"));
		texts.add(new TextField("100"));
		
		List<MenuButton> buttons = new ArrayList<>();
		buttons.add(new MenuButton("GRASS"));
		buttons.add(new MenuButton("FLYING"));
		try {
			Pokemon p = new Pokemon(texts, buttons);
			assertEquals("Tropius", p.getName());
			assertEquals(68, p.getAttack());
			assertEquals(6400, p.getBaseEggSteps());
			assertEquals(200.0, p.getCaptureRate());
			assertEquals(83, p.getDefense());
			assertEquals(1250000, p.getExperienceGrowth());
			assertEquals(99, p.getHp());
			assertEquals(87, p.getSpAttack());
			assertEquals(51, p.getSpDefense());
			assertEquals(Type.GRASS, p.getType1());
			assertEquals(Type.FLYING, p.getType2());
			assertEquals(100.0, p.getSpeed());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@After
	void endSession() {
		javafx.application.Platform.exit();
	}

}
