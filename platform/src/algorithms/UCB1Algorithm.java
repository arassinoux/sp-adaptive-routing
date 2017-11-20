package algorithms;

import arms.Arm;

import java.util.ArrayList;
import java.util.Random;


public class UCB1Algorithm extends AbstractBanditAlgorithm {

    private Random random;

    public UCB1Algorithm(ArrayList<Arm> armsList) {
        super(armsList);
        this.random = new Random();
        this.name = "UCB1";

    }

    @Override
    public Arm selectArm() {
        if(rounds < armsList.size())
            return armsList.get(rounds);
        else
            return getArmWithHighestValue();
    }

    private Arm getArmWithHighestValue() {
        ArrayList<Arm> bestArms = new ArrayList<>(armsList.size());

        double maxValue = -1;

        for (Arm arm: armsList) {
            double value = computeUCB1().get(arm.getId());
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

    private ArrayList<Double> computeUCB1() {

        ArrayList<Double> dist = new ArrayList<>();
        ArrayList<Double> empiricalMeans = getEmpiricalMeans();

        for(Arm arm : armsList)
        {
            double empiricalMean = empiricalMeans.get(arm.getId());
            double value = empiricalMean + Math.sqrt((2*Math.log(rounds))/pulls[arm.getId()]);
            dist.add(arm.getId(), value);
        }
        return dist;
    }

    @Override
    public void reset() {

    }
}
