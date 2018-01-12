package agents.algorithms;

import agents.algorithms.parameters.AlgorithmParameters;
import agents.algorithms.parameters.EpsilonGreedyParameters;
import agents.algorithms.utils.RandomCollection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class EpsilonGreedyAlgorithm extends AbstractBanditAlgorithm {

    private final EpsilonGreedyParameters parameters;

    public EpsilonGreedyAlgorithm(AlgorithmParameters parameters)
    {
        super(-1, EnumBanditAlgorithm.EGREEDY);
        this.parameters = (EpsilonGreedyParameters)parameters;
        this.seed = parameters.getSeed();
        this.random = new Random(seed);
    }

    @Override
    public Integer selectArm() {
        if (random.nextDouble() > parameters.getEpsilon()) {
            return getArmWithHighestValue();
        }

        return random.nextInt(armsCount);
    }

    private Integer getArmWithHighestValue() {
        ArrayList<Double> values = getEmpiricalMeans();

        double maxValue = Collections.max(values);

        RandomCollection<Integer> otherMaxValues = new RandomCollection();

        for(int id = 0; id < armsCount; id++)
        {
            if(values.get(id).equals(maxValue)){
                otherMaxValues.add(1, id);
            }

        }

        return otherMaxValues.next();
    }

}
