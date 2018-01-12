package agents.algorithms;

import agents.algorithms.parameters.AlgorithmParameters;
import agents.algorithms.parameters.PursuitParameters;
import agents.algorithms.utils.RandomCollection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;


public class PursuitAlgorithm extends AbstractBanditAlgorithm {

    private ArrayList<Double> probabilities;
    private PursuitParameters parameters;

    public PursuitAlgorithm(AlgorithmParameters parameters) {

        super(-1, EnumBanditAlgorithm.PURSUIT);

        this.parameters = (PursuitParameters)parameters;


        this.seed = parameters.getSeed();
        this.random = new Random(seed);
    }

    @Override
    public void setArmsCount(int armsCount) {
        super.setArmsCount(armsCount);
        Double[] initValues = new Double[armsCount];
        this.probabilities = new ArrayList<>(Arrays.asList(initValues));
        Collections.fill(this.probabilities, 1.0/armsCount);
    }

    @Override
    public Integer selectArm() {
        ArrayList<Double> empiricalMeans = getEmpiricalMeans();
        double maxEmpiricalMean = Collections.max(empiricalMeans);

        RandomCollection<Integer> weightedArms = new RandomCollection();

        for(int id = 0; id < armsCount; id++)
        {
            double currentProbability = probabilities.get(id);
            weightedArms.add(currentProbability, id);

            if(empiricalMeans.get(id) == maxEmpiricalMean)
            {
                probabilities.set(id, currentProbability + parameters.getBeta() * (1 - currentProbability));
            }
            else
            {
                probabilities.set(id, currentProbability + parameters.getBeta() * (0 - currentProbability));
            }
        }

        return weightedArms.next();

    }


}
