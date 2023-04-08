package enums;

public enum Type {
	BUG(0,"BUG"), DARK(1.0/17.0,"DARK"), DRAGON(2.0/17.0,"DRAGON"), ELECTRIC(3.0/17.0,"ELECTRIC"), FAIRY(4.0/17.0,"FAIRY"), FIGHTING(5.0/17.0,"FIGHTING"), FIRE(6.0/17.0,"FIRE"), FLYING(7.0/17.0,"FLYING"), GHOST(8.0/17.0,"GHOST"), GRASS(9.0/17.0,"GRASS"), GROUND(10.0/17.0,"GROUND"), ICE(11.0/17.0,"ICE"), NORMAL(12.0/17.0,"NORMAL"),
	POISON(13.0/17.0,"POISON"), PSYCHIC(14.0/17.0,"PSYCHIC"), ROCK(15.0/17.0,"ROCK"), STEEL(16.0/17.0,"STEEL"), WATER(1,"WATER");
	
	protected double location;
	protected String name;
	
	private Type(double loc, String name) {
		this.location = loc;
		this.name = name;
	}
	
	public double getAxisLocation() {
		return this.location;
	}
	
	public String getName() {
		return this.name;
	}
}
