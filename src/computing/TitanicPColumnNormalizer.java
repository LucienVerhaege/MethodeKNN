package computing;

import enums.Gender;
import interfaces.IEntity;
import model.TitanicP;

public class TitanicPColumnNormalizer implements ColNormalizer{

	@Override
	public double normalize(IEntity ent, int column) {
		double result = 0.0;
		if(column == 1) {
			Gender g = (Gender) ent.columnsValue().get(column);
			result = g.getAxisLocation();
		} else if (column == TitanicP.COLUMN_COUNT-1){
			result = ((int) ent.columnsValue().get(column)==0)?0:1;
		} else {
			result = Double.parseDouble(""+ent.columnsValue().get(column));
		}
		return result;
	}

}
