package computing;

import java.util.List;

import interfaces.IEntity;

public interface IDistance {

    public double computeEuclideanDistance(IEntity entity1, IEntity entity2);
    public double computeEuclideanBalancedDistance(IEntity entity1, IEntity entity2);

    public double computeManhattanDistance(IEntity entity1, IEntity entity2);
    public void setAmplifiers(List<IEntity> dataSet);
}
