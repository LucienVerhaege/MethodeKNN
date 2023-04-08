package model;

import com.opencsv.bean.CsvBindByName;

public class Personnage {
	
	@CsvBindByName
	protected String nom;
	
	@CsvBindByName
	protected int force;
	
	@CsvBindByName
	protected int courage;
	
	@CsvBindByName
	protected String type;

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getForce() {
		return force;
	}

	public void setForce(int force) {
		this.force = force;
	}

	public int getCourage() {
		return courage;
	}

	public void setCourage(int courage) {
		this.courage = courage;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Personnage [nom=" + nom + ", force=" + force + ", courage=" + courage + ", type=" + type + "]";
	}
	
	

}
