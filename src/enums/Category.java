package enums;

import model.Iris;
import model.Pokemon;
import model.TitanicP;

public enum Category {
	POKEMON(Pokemon.class), IRIS(Iris.class), TITANICP(TitanicP.class);

	protected Class cls;

	private Category(Class c){
		this.cls = c;
	}

	public Class getStoredClass(){
		return this.cls;
	}
}
