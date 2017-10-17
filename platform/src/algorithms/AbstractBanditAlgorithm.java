package algorithms;

import arms.Arm;
import java.util.ArrayList;

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
}
