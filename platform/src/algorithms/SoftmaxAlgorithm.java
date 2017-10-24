package algorithms;

import algorithms.helpers.RandomCollection;
import arms.Arm;

import java.util.ArrayList;
import java.util.Arrays;

public class SoftmaxAlgorithm extends AbstractBanditAlgorithm {

    private RandomCollection<Arm> weightedArms;
    private double tau;

    public SoftmaxAlgorithm(ArrayList<Arm> armsList, double tau)
    {
        super(armsList);
        Arrays.fill(empiricalMeans, 1);
        Arrays.fill(counts, 1);
        this.tau = tau;
    }

    private ArrayList<Double> BoltzmannDistribution(double[] weights)
    {
        ArrayList<Double> dist = new ArrayList<>(weights.length);

        double totalWeight = Arrays.stream(weights).sum();
        for(double weight : weights)
        {
            double result = Math.exp(weight / tau) / totalWeight;
            dist.add(result);
        }

        return dist;
    }

    @Override
    public Arm selectArm() {
        ArrayList<Double> computedWeights = BoltzmannDistribution(empiricalMeans);

        this.weightedArms = new RandomCollection();
        for(Arm arm : armsList)
        {
            weightedArms.add(computedWeights.get(arm.getId()), arm);
        }
        return weightedArms.next();
    }


    @Override
    public void reset() {

    }
}