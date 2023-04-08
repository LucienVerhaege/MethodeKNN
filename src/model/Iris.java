package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.opencsv.bean.CsvBindByName;

import interfaces.IEntity;
import javafx.scene.control.TextField;

public class Iris extends IEntity{

	public Iris() {
	}
	
	public Iris(List<TextField> texts) throws Exception {
		this.sLength = !texts.get(0).getText().contains(".")?Double.parseDouble(texts.get(0).getText()+"."):Double.parseDouble(texts.get(0).getText());
		this.sWidth = !texts.get(1).getText().contains(".")?Double.parseDouble(texts.get(1).getText()+"."):Double.parseDouble(texts.get(1).getText());
		this.pLength = !texts.get(2).getText().contains(".")?Double.parseDouble(texts.get(2).getText()+"."):Double.parseDouble(texts.get(2).getText());
		this.pWidth = !texts.get(3).getText().contains(".")?Double.parseDouble(texts.get(3).getText()+"."):Double.parseDouble(texts.get(3).getText());
	}
	
	public Iris(double sLength, double sWidth, double pLength, double pWidth) {
		super();
		this.sLength = sLength;
		this.sWidth = sWidth;
		this.pLength = pLength;
		this.pWidth = pWidth;
	}

	public static final int COLUMN_COUNT = 5;
	
	@CsvBindByName(column = "sepal.length")
	public double sLength;
	
	@CsvBindByName(column = "sepal.width")
	public double sWidth;
	
	@CsvBindByName(column = "petal.length")
	public double pLength;
	
	@CsvBindByName(column = "petal.width")
	public double pWidth;

	@CsvBindByName(column = "variety")
	public String variety;
	
	public static final HashMap<Integer, String> column = createColumnMap();

	private static HashMap<Integer, String> createColumnMap() {
		HashMap<Integer, String> map = new HashMap<>();
		map.put(0,"sLength");
		map.put(1,"sWidth");
		map.put(2,"pLength");
		map.put(3,"pWidth");
		map.put(4, "variety");
		return map;
	}
	
	public HashMap<Integer, String> getMap() {
		return Iris.column;
	}
	
	@Override
	public String toString() {
		return "Sepal Length : " + sLength + " | Sepal Width : " + sWidth + "\nPetal Length : " + pLength + " | Petal Width : " + pWidth;
	}

	public double getsLength() {
		return sLength;
	}

	public void setsLength(double sLength) {
		this.sLength = sLength;
	}

	public double getsWidth() {
		return sWidth;
	}

	public void setsWidth(double sWidth) {
		this.sWidth = sWidth;
	}

	public double getpLength() {
		return pLength;
	}

	public void setpLength(double pLength) {
		this.pLength = pLength;
	}

	public double getpWidth() {
		return pWidth;
	}

	public void setpWidth(double pWidth) {
		this.pWidth = pWidth;
	}

	public String getVariety() {
		return variety;
	}

	public void setVariety(String variety) {
		this.variety = variety;
	}
	
	public List<Object> columnsValue(){
		List<Object> list = new ArrayList<>();
		list.add(this.sLength);
		list.add(this.sWidth);
		list.add(this.pLength);
		list.add(this.pWidth);
		list.add(this.variety);
		return list;
	}
}
