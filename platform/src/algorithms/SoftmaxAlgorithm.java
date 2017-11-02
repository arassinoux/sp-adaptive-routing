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
        Arrays.fill(wins, 1);
        Arrays.fill(pulls, 1);
        this.tau = tau;
    }

    private ArrayList<Double> BoltzmannDistribution(ArrayList<Double> weights)
    {
        ArrayList<Double> dist = new ArrayList<>(weights.size());

        double totalWeight = weights.stream().mapToDouble(Double::doubleValue).sum();

        for(double weight : weights)
        {
            double result = Math.exp(weight / tau) / totalWeight;
            dist.add(result);
        }

        return dist;
    }

    @Override
    public Arm selectArm() {

        ArrayList<Double> computedWeights = BoltzmannDistribution(getEmpiricalMeans());

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
