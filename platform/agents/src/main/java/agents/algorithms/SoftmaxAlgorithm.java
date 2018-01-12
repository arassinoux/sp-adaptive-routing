package agents.algorithms;

import agents.algorithms.utils.RandomCollection;
import agents.algorithms.parameters.AlgorithmParameters;
import agents.algorithms.parameters.SoftmaxParameters;

import java.util.ArrayList;
import java.util.Arrays;

public class SoftmaxAlgorithm extends AbstractBanditAlgorithm {

    private SoftmaxParameters parameters;

    public SoftmaxAlgorithm(AlgorithmParameters parameters)
    {
        super(-1, EnumBanditAlgorithm.SOFTMAX);

        this.parameters = (SoftmaxParameters)parameters;

    }

    @Override
    public void setArmsCount(int armsCount) {
        super.setArmsCount(armsCount);

        Arrays.fill(wins, 1);
        Arrays.fill(pulls, 1);
    }

    private ArrayList<Double> BoltzmannDistribution(ArrayList<Double> weights)
    {
        ArrayList<Double> dist = new ArrayList<>(weights.size());

        double totalWeight = weights.stream().mapToDouble(Double::doubleValue).sum();

        for(double weight : weights)
        {
            double result = Math.exp(weight / parameters.getTau()) / totalWeight;
            dist.add(result);
        }

        return dist;
    }

    @Override
        public Integer selectArm() {

        ArrayList<Double> computedWeights = BoltzmannDistribution(getEmpiricalMeans());

        RandomCollection<Integer> weightedArms = new RandomCollection();

        for(int id = 0; id < armsCount; id++)
        {
            weightedArms.add(computedWeights.get(id), id);
        }
        return weightedArms.next();
    }



}
