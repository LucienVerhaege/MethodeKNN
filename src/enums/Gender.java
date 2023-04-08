package enums;

public enum Gender {

	MALE("M",0.0,"MALE"), FEMALE("F",1.0,"FEMALE");

	protected String abr;
	protected double location;
	protected String name;
	
	private Gender(String abr, double i, String name) {
		this.abr = abr;
		this.location = i;
		this.name = name;
	}
	
	public double getAxisLocation() {
		return this.location;
	}
	
	public String getName() {
		return this.name;
	}
}
