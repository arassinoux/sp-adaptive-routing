package agents.algorithms;

import agents.algorithms.parameters.AlgorithmParameters;
import agents.algorithms.parameters.EXP3Parameters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class EXP3Algorithm extends AbstractBanditAlgorithm {

    private ArrayList<Double> probabilities;
    private ArrayList<Double> weights;
    private EXP3Parameters parameters;

    public EXP3Algorithm(AlgorithmParameters parameters) {

        super(-1, EnumBanditAlgorithm.EXP3);

        this.parameters = (EXP3Parameters)parameters;
        this.seed = parameters.getSeed();
        this.random = new Random(seed);

    }

    @Override
    public void setArmsCount(int armsCount) {
        super.setArmsCount(armsCount);
        Double[] initValues = new Double[armsCount];
        this.probabilities = new ArrayList<>(Arrays.asList(initValues));
        this.weights = new ArrayList<>(Arrays.asList(initValues));
        Collections.fill(weights, 1.0);
    }

    @Override
    public Integer selectArm() {

        computeEXP3();

        double cumulativeProbability = 0.0;
        double probability = random.nextDouble();
        Integer chosenArm = -1;

        for(int id = 0; id < armsCount; id++)
        {
            cumulativeProbability += probabilities.get(id);
            if (probability <= cumulativeProbability && probabilities.get(id) != 0) {

                if (chosenArm == -1)
                    chosenArm = id;
            }
        }

       return chosenArm;
    }

    @Override
    public void update(int armId, double reward) {

        pulls[armId]++;
        wins[armId] += reward;

        //EXP3
        double estimatedReward = reward / probabilities.get(armId);
        double newWeight = weights.get(armId) * Math.exp(parameters.getGamma() * estimatedReward / armsCount);
        weights.set(armId, newWeight);

        rounds++;
    }

    private void computeEXP3()
    {
        double totalWeight = weights.stream().mapToDouble(Double::doubleValue).sum();

        for(int i = 0; i < armsCount; i++)
        {
            double EXP3 = (1 - parameters.getGamma()) * (weights.get(i) /  totalWeight) + (parameters.getGamma() / armsCount);
            probabilities.set(i, EXP3);
        }

    }


}
