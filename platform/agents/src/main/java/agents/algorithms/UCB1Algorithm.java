package agents.algorithms;

import agents.algorithms.parameters.AlgorithmParameters;
import agents.algorithms.parameters.UCB1Parameters;
import agents.algorithms.utils.RandomCollection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class UCB1Algorithm extends AbstractBanditAlgorithm {

    private UCB1Parameters parameters;

    public UCB1Algorithm(AlgorithmParameters parameters) {
        super(-1, EnumBanditAlgorithm.UCB1);

        this.parameters = (UCB1Parameters)parameters;
        this.seed = parameters.getSeed();
        this.random = new Random(seed);
    }

    @Override
    public Integer selectArm() {
        if(rounds < armsCount)
            return rounds;
        else
            return getArmWithHighestValue();
    }

    private Integer getArmWithHighestValue() {

        ArrayList<Double> values = computeUCB1();
        double maxValue = Collections.max(values);

        RandomCollection<Integer> otherMaxValues = new RandomCollection();

        for(int id = 0; id < armsCount; id++)
        {
            if(values.get(id).equals(maxValue))
                otherMaxValues.add(1, id);
        }

        return otherMaxValues.next();
    }

    private ArrayList<Double> computeUCB1() {

        ArrayList<Double> dist = new ArrayList<>();
        ArrayList<Double> empiricalMeans = getEmpiricalMeans();

        for(int id = 0; id < armsCount; id++)
        {
            double empiricalMean = empiricalMeans.get(id);
            double value = empiricalMean + Math.sqrt((2*Math.log((double)rounds))/pulls[id]);
            dist.add(id, value);
        }
        return dist;
    }
}
