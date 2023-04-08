package computing;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import interfaces.IEntity;
import model.TitanicP;

public class DistanceTitanic implements IDistance {

    //Attributes that will not affect the surviving question :
    //Name, Embarked, Id, Fare, Ticket
    //We include family because the more family members they have, 
    //the less likely they are to survive: they would try to save them and die with them
    //We will normalize data in order to transform different objects into double
	//We will also use the computed average age of the data, as age for the people that did not enter their age
	
	private double ampAge;
	private double avgAge;
	
    @Override
    public double computeEuclideanDistance(IEntity entity1, IEntity entity2) {
    	TitanicP ent1 = (TitanicP) entity1;
    	double age1 = (ent1.getAge()==0)?avgAge:ent1.getAge();
    	TitanicP ent2 = (TitanicP) entity2;
    	double age2 = (ent2.getAge()==0)?avgAge:ent2.getAge();
    	double squaredDist = Math.pow((ent2.getpClass() - ent1.getpClass()), 2) +
    			Math.pow(compareGender(ent1, ent2), 2) + 
    			Math.pow(age2 - age1, 2) +
    			Math.pow((ent2.getSibSp() - ent1.getSibSp()), 2) +
    			Math.pow((ent2.getParch() - ent1.getParch()), 2) +
    			Math.pow(compareCabin(ent1,ent2), 2);
    	return Math.sqrt(squaredDist);
    }

	@Override
    public double computeEuclideanBalancedDistance(IEntity entity1, IEntity entity2) {
		TitanicP ent1 = (TitanicP) entity1;
    	double age1 = (ent1.getAge()==0)?avgAge:ent1.getAge();
    	TitanicP ent2 = (TitanicP) entity2;
    	double age2 = (ent2.getAge()==0)?avgAge:ent2.getAge();
    	double squaredDist = Math.pow((ent2.getpClass() - ent1.getpClass()), 2) +
    			Math.pow(compareGender(ent1, ent2), 2) + 
    			Math.pow((age2 - age1)/ampAge, 2) +
    			Math.pow( (ent2.getSibSp() - ent1.getSibSp()), 2) +
    			Math.pow( (ent2.getParch() - ent1.getParch()), 2) +
    			Math.pow(compareCabin(ent1,ent2), 2);
    	return Math.sqrt(squaredDist);
    }

    @Override
    public double computeManhattanDistance(IEntity entity1, IEntity entity2) {
    	TitanicP ent1 = (TitanicP) entity1;
    	double age1 = (ent1.getAge()==0)?avgAge:ent1.getAge();
    	TitanicP ent2 = (TitanicP) entity2;
    	double age2 = (ent2.getAge()==0)?avgAge:ent2.getAge();
    	return Math.abs((double)(ent2.getpClass() - ent1.getpClass())) +
    			Math.abs(compareGender(ent1, ent2)) + 
    			Math.abs((age2 - age1)) +
    			Math.abs((double) (ent2.getSibSp() - ent1.getSibSp())) +
    			Math.abs((double) (ent2.getParch() - ent1.getParch())) +
    			Math.abs(compareCabin(ent1,ent2));
    }
    
    private double compareGender(TitanicP ent1, TitanicP ent2) {
    	return (ent2.getGender().equals(ent1.getGender())?0:1);
    }
    
    private double compareCabin(TitanicP ent1, TitanicP ent2) {
		return (ent2.getCabin().equals(ent1.getCabin())?0:1);
	}
    
    public void setAmplifiers(List<IEntity> dataSet){
        double min = getMinOrMax(dataSet, false);
        double max = getMinOrMax(dataSet, true);
        computeAverageAge(dataSet);
		
		ampAge = max - min;
    }

    private double getMinOrMax(List<IEntity> datas, boolean getMax) {
		Iterator<IEntity> i = datas.iterator();
		TitanicP titanicP;
		double current = 0;
		if(i.hasNext()) {
			titanicP = (TitanicP) i.next();
			current = titanicP.getAge();
		}
		while(i.hasNext()) {
			titanicP = (TitanicP) i.next();
			current = compareAndSet(current, titanicP, getMax);
		}
		return current;
	}

    private double compareAndSet(double current, TitanicP titanicP, boolean greaterThan) {
		if(!greaterThan) {
			if(titanicP.getAge() < current) {
				current = titanicP.getAge();
			}
		}else {
			if(titanicP.getAge() > current) {
				current = titanicP.getAge();
			}
		}
		
		return current;
	}
    
    private void computeAverageAge(List<IEntity> ls) {
    	HashMap <Integer, Integer> hm = new HashMap<>();
    	int sumAge = 0;
    	int sumCoeff = 0;
    	for(IEntity tI : ls) {
    		TitanicP t = (TitanicP) tI;
    		Integer age = t.getAge();
    		hm.putIfAbsent(age, 1);
    		hm.computeIfPresent(age, (key, val) -> val + 1);
    		sumAge += age;
    	}
    	for(Entry<Integer, Integer> es : hm.entrySet()) {
    		sumCoeff += es.getValue();
    	}
    	
    		if(sumCoeff>0) avgAge = (double) sumAge/sumCoeff;		
    }
}
