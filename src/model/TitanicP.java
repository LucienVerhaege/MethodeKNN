package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.opencsv.bean.CsvBindByName;

import enums.Gender;
import interfaces.IEntity;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;

public class TitanicP extends IEntity{

	public TitanicP() {
	}
	
	public TitanicP(List<TextField> texts, List<MenuButton> buttons) throws Exception {
		this.id = Integer.parseInt(texts.get(0).getText());
		this.pClass = Integer.parseInt(texts.get(1).getText());
		this.name = texts.get(2).getText();
		this.gender = Gender.valueOf(buttons.get(0).getText());
		this.age = Integer.parseInt(texts.get(3).getText());
		this.sibSp = Integer.parseInt(texts.get(4).getText());
		this.parch = Integer.parseInt(texts.get(5).getText());
		this.ticket = texts.get(6).getText();
		this.fare = !texts.get(7).getText().contains(".")?Double.parseDouble(texts.get(7).getText()+"."):Double.parseDouble(texts.get(7).getText());
		this.cabin = texts.get(8).getText();
		this.embarked = texts.get(9).getText().charAt(0);
	}
	
	public TitanicP(int id, int pClass, String name, Gender gender, int age, int sibSp, int parch, String ticket,
			double fare, String cabin, char embarked) {
		super();
		this.id = id;
		this.pClass = pClass;
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.sibSp = sibSp;
		this.parch = parch;
		this.ticket = ticket;
		this.fare = fare;
		this.cabin = cabin;
		this.embarked = embarked;
	}

	public static final int COLUMN_COUNT = 6;
	public static final HashMap<Integer, String> column = createColumnMap();
	
	@CsvBindByName(column = "PassengerId")
	protected int id;
	
	@CsvBindByName(column = "Survived")
	protected int survived;
	
	@CsvBindByName(column = "Pclass")
	protected int pClass;
	
	@CsvBindByName(column = "Name")
	protected String name;
	
	@CsvBindByName(column = "Sex")
	protected Gender gender;
	
	@CsvBindByName(column = "Age")
	protected int age;
	
	@CsvBindByName(column = "SibSp")
	protected int sibSp;
	
	@CsvBindByName(column = "Parch")
	protected int parch;

	@CsvBindByName(column = "Ticket")
	protected String ticket;
	
	@CsvBindByName(column = "Fare")
	protected double fare;
	
	@CsvBindByName(column = "Cabin")
	protected String cabin;

	@CsvBindByName(column = "Embarked")
	protected char embarked;

	@Override
	public String toString() {
		return "Id : " + id + " | Passenger Class : " + pClass + "\nName : " + name + " | Gender : "
				+ gender + " | Age : " + age + "\nSibling / Spouses : " + sibSp + " | Parents Childrens : " + parch + " | Ticket : " + ticket + "\nFare : "
				+ fare + " | Cabin : " + cabin + " | Embarked : " + embarked;
	}

	private static HashMap<Integer, String> createColumnMap() {
		HashMap<Integer, String> map = new HashMap<>();
		map.put(0,"pClass");
		map.put(1,"gender");
		map.put(2,"age");
		map.put(3,"sibSp");
		map.put(4,"parch");
		map.put(5, "Survived");
		return map;
	}
	
	public HashMap<Integer, String> getMap() {
		return TitanicP.column;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSurvived() {
		return survived;
	}

	public void setSurvived(int survived) {
		this.survived = survived;
	}

	public int getpClass() {
		return pClass;
	}

	public void setpClass(int pClass) {
		this.pClass = pClass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getSibSp() {
		return sibSp;
	}

	public void setSibSp(int sibSp) {
		this.sibSp = sibSp;
	}

	public int getParch() {
		return parch;
	}

	public void setParch(int parch) {
		this.parch = parch;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public double getFare() {
		return fare;
	}

	public void setFare(double fare) {
		this.fare = fare;
	}

	public String getCabin() {
		return cabin;
	}

	public void setCabin(String cabin) {
		this.cabin = cabin;
	}

	public char getEmbarked() {
		return embarked;
	}

	public void setEmbarked(char embarked) {
		this.embarked = embarked;
	}
	
	public List<Object> columnsValue(){
		List<Object> list = new ArrayList<>();
		list.add(this.pClass);
		list.add(this.gender);
		list.add(this.age);
		list.add(this.sibSp);
		list.add(this.parch);
		list.add(this.survived);
		return list;
	}
}
