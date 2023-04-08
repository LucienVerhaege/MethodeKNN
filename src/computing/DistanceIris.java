package computing;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import interfaces.IEntity;
import model.Iris;

public class DistanceIris implements IDistance{

    private double ampSepalLength;
    private double ampSepalWidth;
    private double ampPetalLength;
    private double ampPetalWidth;

    @Override
    public double computeEuclideanDistance(IEntity entity1, IEntity entity2) {
        Iris ent1 = (Iris) entity1;
        Iris ent2 = (Iris) entity2;
        double squaredDist = Math.pow(ent2.sLength-ent1.sLength, 2) + 
                    Math.pow(ent2.sWidth-ent1.sWidth, 2) + 
                    Math.pow(ent2.pLength-ent1.pLength, 2) + 
                    Math.pow(ent2.pWidth-ent1.pWidth, 2);
        return Math.sqrt(squaredDist);
    }

    @Override
    public double computeEuclideanBalancedDistance(IEntity entity1, IEntity entity2) {
        Iris ent1 = (Iris) entity1;
        Iris ent2 = (Iris) entity2;
        double squaredDist = Math.pow((ent2.sLength-ent1.sLength)/ampSepalLength, 2) + 
                    Math.pow((ent2.sWidth-ent1.sWidth)/ampSepalWidth, 2) + 
                    Math.pow((ent2.pLength-ent1.pLength)/ampPetalLength, 2) + 
                    Math.pow((ent2.pWidth-ent1.pWidth)/ampPetalWidth, 2);
        return Math.sqrt(squaredDist);
    }

    @Override
    public double computeManhattanDistance(IEntity entity1, IEntity entity2) {
        Iris ent1 = (Iris) entity1;
        Iris ent2 = (Iris) entity2;
        return Math.abs(ent2.sLength-ent1.sLength) +
               Math.abs(ent2.sWidth-ent1.sWidth) +
               Math.abs(ent2.pLength-ent1.pLength) +
               Math.abs(ent2.pWidth-ent1.pWidth);
    }
    
    public void setAmplifiers(List<IEntity> dataSet){
        List<Double> mins = getMinOrMax(dataSet, false);
		List<Double> maxs = getMinOrMax(dataSet, true);
		
		Double max1 = maxs.get(0);
		Double min1 = mins.get(0);
		
		Double max2 = maxs.get(1);
		Double min2 = mins.get(1);
		
		Double max3 = maxs.get(2);
		Double min3 = mins.get(2);
		
		Double max4 = maxs.get(3);
		Double min4 = mins.get(3);
		
		ampSepalLength = (double) max1.intValue() - min1.intValue();
		ampSepalWidth = max2.doubleValue() - min2.doubleValue();
		ampPetalLength = (double) max3.intValue() - min3.intValue();
		ampPetalWidth = max4.doubleValue() - min4.doubleValue();
    }

    public List<Double> getMinOrMax(List<IEntity> datas, boolean getMax) {
		Iterator<IEntity> i = datas.iterator();
		Iris iris;
		List<Double> l = null;
		if(i.hasNext()) {
			iris = (Iris) i.next();
			l = new ArrayList<>();
			l.add(iris.sLength);
			l.add(iris.sWidth);
			l.add(iris.pLength);
			l.add(iris.pWidth);
		}
		while(i.hasNext()) {
			iris = (Iris) i.next();
			l = compareAndSet(l, iris, getMax);
		}
		return l;
	}

    public List<Double> compareAndSet(List<Double> l, Iris iris, boolean greaterThan) {
		if(!greaterThan) {
			setMin(l, iris);
		}else {
			setMax(l, iris);
		}
		
		return l;
	}
    
    //Verify if the variety is the same or not by returning 1 if different or 0 if equal
    public double compareVariety(Iris ent1, Iris ent2){
        return ent1.variety.equals(ent2.variety)?0:1;
    }
    
    public void setMin(List<Double> l, Iris iris) {
    	if(iris.sLength < l.get(0)) {
			l.set(0, iris.sLength);
		}
		if(iris.sWidth < l.get(1)) {
			l.set(1, iris.sWidth);
		}
		if(iris.pLength < l.get(2)) {
			l.set(2, iris.pLength);
		}
		if(iris.pWidth < l.get(3)) {
			l.set(3, iris.pWidth);
		}
    }
    
    public void setMax(List<Double> l, Iris iris) {
    	if(iris.sLength > l.get(0)) {
			l.set(0, iris.sLength);
		}
		if(iris.sWidth > l.get(1)) {
			l.set(1, iris.sWidth);
		}
		if(iris.pLength > l.get(2)) {
			l.set(2, iris.pLength);
		}
		if(iris.pWidth > l.get(3)) {
			l.set(3, iris.pWidth);
		}
    }
}
