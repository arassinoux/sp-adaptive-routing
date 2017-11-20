package algorithms;

import arms.Arm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class EXP3Algorithm extends AbstractBanditAlgorithm {

    private ArrayList<Double> probabilities;
    private ArrayList<Double> weights;
    private Random random;
    private double gamma;

    public EXP3Algorithm(ArrayList<Arm> armsList, double gamma) {
        super(armsList);
        this.random = new Random();
        this.gamma = gamma;
        Double[] initValues = new Double[armsList.size()];
        this.probabilities = new ArrayList<>(Arrays.asList(initValues));
        this.weights = new ArrayList<>(Arrays.asList(initValues));
        Collections.fill(weights, 1.0);
        this.name = "EXP3";

    }

    @Override
    public Arm selectArm() {

        computeEXP3();

        double cumulativeProbability = 0.0;
        double probability = random.nextDouble();
        Arm chosenArm = null;

        for (Arm arm : armsList) {
            cumulativeProbability += probabilities.get(arm.getId());
            if (probability <= cumulativeProbability && probabilities.get(arm.getId()) != 0) {

                if (chosenArm == null)
                    chosenArm = arm;
            }
        }

       return chosenArm;
    }

    @Override
    public void update(Arm arm, double reward) {
        int armId = arm.getId();

        pulls[armId]++;
        wins[armId] += reward;

        //EXP3
        double estimatedReward = reward / probabilities.get(arm.getId());
        double newWeight = weights.get(arm.getId()) * Math.exp(gamma * estimatedReward / armsList.size());
        weights.set(arm.getId(), newWeight);

        rounds++;
    }

    private void computeEXP3()
    {
        double totalWeight = weights.stream().mapToDouble(Double::doubleValue).sum();

        for(int i = 0; i < armsList.size(); i++)
        {
            double EXP3 = (1 - gamma) * (weights.get(i) /  totalWeight) + (gamma / armsList.size());
            probabilities.set(i, EXP3);
        }

    }


    @Override
    public void reset() {

    }
}
