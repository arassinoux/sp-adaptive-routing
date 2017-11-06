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
        this.name = "egreedy";
    }

    @Override
    public Arm selectArm() {
        if (random.nextDouble() > epsilon) {
            return getArmWithHighestValue();
        }

        return armsList.get(random.nextInt(armsList.size()));
    }

    private Arm getArmWithHighestValue() {
        ArrayList<Arm> bestArms = new ArrayList<>(armsList.size());

        double maxValue = -1;

        for (Arm arm: armsList) {
            double value = getEmpiricalMeans().get(arm.getId());
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
    public void reset() {

    }
}
