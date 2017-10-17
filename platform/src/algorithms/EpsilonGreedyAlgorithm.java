package algorithms;

import arms.Arm;

import java.util.ArrayList;
import java.util.Random;

public class EpsilonGreedyAlgorithm extends AbstractBanditAlgorithm {

    private double epsilon;
    private Random random;

    public EpsilonGreedyAlgorithm(ArrayList<Arm> armsList, double epsilon)
    {
        super(armsList);
        this.epsilon = epsilon;
        this.random = new Random();
    }

    @Override
    public Arm selectArm() {
        if (random.nextDouble() > epsilon) {
            return getArmWithHigherValue();
        }

        return armsList.get(random.nextInt(armsList.size()));
    }

    private Arm getArmWithHigherValue() {
        ArrayList<Arm> bestArms = new ArrayList<>(armsList.size());

        double maxValue = -1;

        for (Arm arm: armsList) {
            double value = empiricalMeans[armsList.indexOf(arm)];
            if (value > maxValue) {
                bestArms.clear();
                bestArms.add(arm);
                maxValue = value;
            } else if (value == maxValue) {
                bestArms.add(arm);
            }
        }

        return bestArms.get(random.nextInt(bestArms.size()));
    }

    @Override
    public void update(Arm arm, double reward) {
        int armIndex = armsList.indexOf(arm);

        counts[armIndex]++;
        int n = counts[armIndex];

        double value = empiricalMeans[armIndex];
        double new_value = (((double)n - 1) / (double)n * value) + ((1 / (double)n) * reward);
        empiricalMeans[armIndex] = new_value;

    }

    @Override
    public void reset() {

    }
}
