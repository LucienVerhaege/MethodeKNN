package computing;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import interfaces.IEntity;
import model.Pokemon;

public class DistancePokemon implements IDistance{

    private double ampCaptureRate;
    private double ampSpeed;
    private double ampBaseEggSteps;
    private double ampExperienceGrowth;

    public double computeEuclideanDistance(IEntity entity1, IEntity entity2) {
        Pokemon ent1 = (Pokemon) entity1;
        Pokemon ent2 = (Pokemon) entity2;
        double squaredDist = Math.pow((ent2.capture_rate - ent1.capture_rate), 2) +
				Math.pow((ent2.speed - ent1.speed), 2) +
				Math.pow((double)ent2.attack - ent1.attack, 2) +
				Math.pow(((double)ent2.base_egg_steps - ent1.base_egg_steps), 2) +
				Math.pow((double)ent2.defense - ent1.defense, 2) +
				Math.pow(((double)ent2.experience_growth - ent1.experience_growth), 2) +
				Math.pow((double)ent2.hp - ent1.hp, 2) +
				Math.pow((double)ent2.sp_attack - ent1.sp_attack, 2) +
				Math.pow((double)ent2.sp_defense - ent1.sp_defense, 2);
        return Math.sqrt(squaredDist);
    }

    public double computeEuclideanBalancedDistance(IEntity entity1, IEntity entity2) {
        Pokemon ent1 = (Pokemon) entity1;
        Pokemon ent2 = (Pokemon) entity2;
        double squaredDist = Math.pow((ent2.capture_rate - ent1.capture_rate)/ampCaptureRate, 2) +
				Math.pow((ent2.speed - ent1.speed)/ampSpeed, 2) +
				Math.pow((double)ent2.attack - ent1.attack, 2) +
				Math.pow(((double)ent2.base_egg_steps - ent1.base_egg_steps)/ampBaseEggSteps, 2) +
				Math.pow((double)ent2.defense - ent1.defense, 2) +
				Math.pow(((double)ent2.experience_growth - ent1.experience_growth)/ampExperienceGrowth, 2) +
				Math.pow((double)ent2.hp - ent1.hp, 2) +
				Math.pow((double)ent2.sp_attack - ent1.sp_attack, 2) +
				Math.pow((double)ent2.sp_defense - ent1.sp_defense, 2);
        return Math.sqrt(squaredDist);
    }
    
    public double computeManhattanDistance(IEntity entity1, IEntity entity2){
        Pokemon ent1 = (Pokemon) entity1;
        Pokemon ent2 = (Pokemon) entity2;
        return Math.abs(ent2.capture_rate - ent1.capture_rate) +
				Math.abs(ent2.speed - ent1.speed) +
				Math.abs(ent2.attack - ent1.attack) +
				Math.abs(ent2.base_egg_steps - ent1.base_egg_steps) +
				Math.abs(ent2.defense - ent1.defense) +
				Math.abs(ent2.experience_growth - ent1.experience_growth) +
				Math.abs(ent2.hp - ent1.hp) +
				Math.abs(ent2.sp_attack - ent1.sp_attack) +
				Math.abs(ent2.sp_defense - ent1.sp_defense);
    }

    public void setAmplifiers(List<IEntity> dataSet){
        List<Double> mins = getMinOrMax(dataSet, false);
		List<Double> maxs = getMinOrMax(dataSet, true);
		
		ampBaseEggSteps = (double)maxs.get(0).intValue() - mins.get(0).intValue();
		ampCaptureRate = maxs.get(1).doubleValue() - mins.get(1).doubleValue();
		ampExperienceGrowth = (double)maxs.get(2).intValue() - mins.get(2).intValue();
		ampSpeed = maxs.get(3).doubleValue() - mins.get(3).doubleValue();
    }

    public List<Double> getMinOrMax(List<IEntity> datas, boolean getMax) {
		Iterator<IEntity> i = datas.iterator();
		Pokemon pkm;
		List<Double> l = null;
		if(i.hasNext()) {
			pkm = (Pokemon) i.next();
			l = new ArrayList<>();
			l.add((double) pkm.base_egg_steps);
			l.add(pkm.capture_rate);
			l.add((double) pkm.experience_growth);
			l.add(pkm.speed);
		}
		while(i.hasNext()) {
			pkm = (Pokemon) i.next();
			l = compareAndSet(l, pkm, getMax);
		}
		return l;
	}

    public List<Double> compareAndSet(List<Double> l, Pokemon pkm, boolean greaterThan) {
		if(!greaterThan) {
			setMin(l, pkm);
		}else {
			setMax(l, pkm);
		}
		
		return l;
	}
    
    public void setMin(List<Double> l, Pokemon pkm) {
    	if(pkm.base_egg_steps < l.get(0)) {
			l.set(0, (double) pkm.base_egg_steps);
		}
		if(pkm.capture_rate < l.get(1)) {
			l.set(1, pkm.capture_rate);
		}
		if(pkm.experience_growth < l.get(2)) {
			l.set(2, (double) pkm.experience_growth);
		}
		if(pkm.speed < l.get(3)) {
			l.set(3, pkm.speed);
		}
    }
    
    public void setMax(List<Double> l, Pokemon pkm) {
    	if(pkm.base_egg_steps > l.get(0)) {
			l.set(0, (double) pkm.base_egg_steps);
		}
		if(pkm.capture_rate > l.get(1)) {
			l.set(1, pkm.capture_rate);
		}
		if(pkm.experience_growth > l.get(2)) {
			l.set(2, (double) pkm.experience_growth);
		}
		if(pkm.speed > l.get(3)) {
			l.set(3, pkm.speed);
		}
    }
}
