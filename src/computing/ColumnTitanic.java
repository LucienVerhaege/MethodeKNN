package computing;

import java.util.List;

import interfaces.IColumn;
import interfaces.IEntity;
import model.MVCModel;
import model.TitanicP;

public class ColumnTitanic extends IColumn{

	protected int column;
	protected ColNormalizer normalizer;
	protected MVCModel origin;
	
	public ColumnTitanic(int col, MVCModel or) {
		this.column = col;
		this.normalizer = new TitanicPColumnNormalizer();
		this.origin = or;
	}
	
	public double getNormalizedValue(IEntity entity) {
		return normalizer.normalize(entity, column);
	}

	public String getName() {
		return TitanicP.column.get(this.column);
	}

	public List<IEntity> getDataSet() {
		return origin.getData();
	}
	
	
}
