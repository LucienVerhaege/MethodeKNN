package computing;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import interfaces.IEntity;
import model.Iris;

public class IrisColumnNormalizer implements ColNormalizer{

	HashMap<String, Double> variety;
	
	public void initializeCategories(List<IEntity> data) {
		this.variety = new HashMap<>();
		Iris iris;
		double counter = 0;
		for(IEntity e : data) {
			iris = (Iris) e;
			variety.putIfAbsent(iris.getVariety(), counter+=1);
		}
	}
	
	public Set<Entry<String, Double>> getVariety(){
		return this.variety.entrySet();
	}
	
	@Override
	public double normalize(IEntity ent, int i) {
		double result = 0;
		if(i==Iris.COLUMN_COUNT-1) {
			result = variety.get(ent.columnsValue().get(i));
		} else {
			result = Double.parseDouble(""+ent.columnsValue().get(i));
		}
		return result;
	}

}
