package algorithms;

import arms.Arm;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class AbstractBanditAlgorithm implements BanditAlgorithm {

    protected ArrayList<Arm> armsList;
    protected double[] empiricalMeans;
    protected int[] counts;

    public AbstractBanditAlgorithm(ArrayList<Arm> armsList)
    {
        this.armsList = armsList;
        this.empiricalMeans = new double[armsList.size()];
        this.counts = new int[armsList.size()];
    }

    @Override
    public ArrayList<Arm> getArmsList() {
        return armsList;
    }


    public void update(Arm arm, double reward) {
        int armId = arm.getId();

        counts[armId]++;
        int n = counts[armId];

        double oldValue = empiricalMeans[armId];
        double newValue = ((double)(n - 1) / (double)n) * oldValue + ((1 / (double)n) * reward);
        empiricalMeans[armId] = newValue;
    }
}
