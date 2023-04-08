package computing;

import enums.Type;
import interfaces.IEntity;
import model.Pokemon;

public class PokemonColumnNormalizer implements ColNormalizer{

	@Override
	public double normalize(IEntity ent, int column) {
		double result = 0.0;
		if(column == 8 || column == 9) {
			Type t = (Type) ent.columnsValue().get(column);
			result = t==null?0:t.getAxisLocation();
		} else if(column == Pokemon.COLUMN_COUNT-1){
			result = ((boolean) ent.columnsValue().get(column))?1:0;
		} else {
			result = Double.parseDouble(""+ent.columnsValue().get(column));
		}
		return result;
	}

}
