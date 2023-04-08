package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.opencsv.bean.CsvBindByName;

import enums.Type;
import interfaces.IEntity;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;

public class Pokemon extends IEntity{
	
	public Pokemon() {
	}
	
	public Pokemon(String name, int attack, int base_egg_steps, double capture_rate, int defense, int experience_growth,
			int hp, int sp_attack, int sp_defense, Type type1, Type type2, double speed) {
		super();
		this.name = name;
		this.attack = attack;
		this.base_egg_steps = base_egg_steps;
		this.capture_rate = capture_rate;
		this.defense = defense;
		this.experience_growth = experience_growth;
		this.hp = hp;
		this.sp_attack = sp_attack;
		this.sp_defense = sp_defense;
		this.type1 = type1;
		this.type2 = type2;
		this.speed = speed;
	}

	public Pokemon(List<TextField> texts, List<MenuButton> buttons) throws Exception {
		this.name = texts.get(0).getText();
		this.attack = Integer.parseInt(texts.get(1).getText());
		this.base_egg_steps = Integer.parseInt(texts.get(2).getText());
		this.capture_rate = !texts.get(3).getText().contains(".")?Double.parseDouble(texts.get(3).getText()+".0"):Double.parseDouble(texts.get(3).getText());
		this.defense = Integer.parseInt(texts.get(4).getText());
		this.experience_growth = Integer.parseInt(texts.get(5).getText());
		this.hp = Integer.parseInt(texts.get(6).getText());
		this.sp_attack = Integer.parseInt(texts.get(7).getText());
		this.sp_defense = Integer.parseInt(texts.get(8).getText());
		this.type1 = Type.valueOf(buttons.get(0).getText());
		this.type2 = buttons.get(1).getText().equals("Null")?null:Type.valueOf(buttons.get(1).getText());
		this.speed = !texts.get(9).getText().contains(".")?Double.parseDouble(texts.get(9).getText()+".0"):Double.parseDouble(texts.get(9).getText());
	}
	
	public static final int COLUMN_COUNT = 12;
	
	// DO NOT CHANGE ATTRIBUTE NAMES, THEY BELONG TO THE CSV FILE
	
	@CsvBindByName
	public String name;
	@CsvBindByName
	public int attack;
	@CsvBindByName
	public int base_egg_steps;
	@CsvBindByName
	public double capture_rate;
	@CsvBindByName
	public int defense;
	@CsvBindByName
	public int experience_growth;
	@CsvBindByName
	public int hp;
	@CsvBindByName
	public int sp_attack;
	@CsvBindByName
	public int sp_defense;
	@CsvBindByName
	public Type type1;
	@CsvBindByName
	public Type type2;
	@CsvBindByName
	public double speed;
	@CsvBindByName
	public boolean is_legendary;
	
	public static final HashMap<Integer, String> column = createColumnMap();

	private static HashMap<Integer, String> createColumnMap() {
		HashMap<Integer, String> map = new HashMap<>();
		map.put(0, "attack");
		map.put(1, "base_egg_steps");
		map.put(2, "capture_rate");
		map.put(3, "defense");
		map.put(4, "experience_growth");
		map.put(5, "hp");
		map.put(6, "sp_attack");
		map.put(7, "sp_defense");
		map.put(8, "type1");
		map.put(9, "type2");
		map.put(10, "speed");
		map.put(11, "is_legendary");
		return map;
	}
	
	public HashMap<Integer, String> getMap() {
		return Pokemon.column;
	}
	
	@Override
	public String toString() {
		return "Name : " + name + " | Attack : " + attack + " | Base Egg Steps : " + base_egg_steps + "\nCapture Rate : "
				+ capture_rate + " | Defense : " + defense + " | Experience Growth : " + experience_growth + "\nHP : " + hp
				+ " | sp_attack : " + sp_attack + " | sp_defense : " + sp_defense + "\nType n°1 : " + type1 + " | Type n°2 : " + type2
				+ " | speed : " + speed;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAttack() {
		return attack;
	}
	public void setAttack(int attack) {
		this.attack = attack;
	}
	public int getBaseEggSteps() {
		return base_egg_steps;
	}
	public void setBaseEggSteps(int baseEggSteps) {
		this.base_egg_steps = baseEggSteps;
	}
	public double getCaptureRate() {
		return capture_rate;
	}
	public void setCaptureRate(double captureRate) {
		this.capture_rate = captureRate;
	}
	public int getDefense() {
		return defense;
	}
	public void setDefense(int defense) {
		this.defense = defense;
	}
	public int getExperienceGrowth() {
		return experience_growth;
	}
	public void setExperienceGrowth(int experienceGrowth) {
		this.experience_growth = experienceGrowth;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public int getSpAttack() {
		return sp_attack;
	}
	public void setSpAttack(int spAttack) {
		this.sp_attack = spAttack;
	}
	public int getSpDefense() {
		return sp_defense;
	}
	public void setSpDefense(int spDefense) {
		this.sp_defense = spDefense;
	}
	public Type getType1() {
		return type1;
	}
	public void setType1(Type type1) {
		this.type1 = type1;
	}
	public Type getType2() {
		return type2;
	}
	public void setType2(Type type2) {
		this.type2 = type2;
	}
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	public boolean getIsLegendary() {
		return is_legendary;
	}
	public void setIsLegendary(boolean isLegendary) {
		this.is_legendary = isLegendary;
	}
	
	public List<Object> columnsValue(){
		List<Object> list = new ArrayList<>();
		list.add(this.attack);
		list.add(this.base_egg_steps);
		list.add(this.capture_rate);
		list.add(this.defense);
		list.add(this.experience_growth);
		list.add(this.hp);
		list.add(this.sp_attack);
		list.add(this.sp_defense);
		list.add(this.type1);
		list.add(this.type2);
		list.add(this.speed);
		list.add(this.is_legendary);
		return list;
	}
}
