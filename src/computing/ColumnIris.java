package computing;

import java.util.List;

import interfaces.IColumn;
import interfaces.IEntity;
import model.Iris;
import model.MVCModel;

public class ColumnIris extends IColumn{

	protected int column;
	protected ColNormalizer normalizer;
	protected MVCModel origin;
	
	public ColumnIris(int col, MVCModel or) {
		this.column = col;
		this.normalizer = new IrisColumnNormalizer();
		this.origin = or;
	}
	
	public double getNormalizedValue(IEntity entity) {
		return normalizer.normalize(entity, column);
	}

	public String getName() {
		return Iris.column.get(this.column);
	}

	public List<IEntity> getDataSet() {
		return origin.getData();
	}
	
	
}
