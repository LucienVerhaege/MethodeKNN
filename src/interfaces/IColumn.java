package interfaces;

import java.util.List;

public abstract class IColumn {
	
	public abstract double getNormalizedValue(IEntity entity);
	public abstract String getName();
	public abstract List<IEntity> getDataSet();
	
	public double computeMaxValue() {
		double max = getNormalizedValue(getDataSet().get(0));
		for(IEntity e : getDataSet()) {
			if(max < getNormalizedValue(e)) max = getNormalizedValue(e);
		}
		return max;
	}
	
	public double computeMinValue() {
		double min = getNormalizedValue(getDataSet().get(0));
		for(IEntity e : getDataSet()) {
			if(min > getNormalizedValue(e)) min = getNormalizedValue(e);
		}
		return min;
	}

}
