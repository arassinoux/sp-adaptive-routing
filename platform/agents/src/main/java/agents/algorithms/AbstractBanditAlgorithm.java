package agents.algorithms;

import java.util.ArrayList;
import java.util.Random;

abstract class AbstractBanditAlgorithm implements BanditAlgorithm {

    protected double[] wins;
    protected double[] pulls;
    protected int rounds;
    int armsCount;
    EnumBanditAlgorithm type;
    int seed;
    Random random;

    AbstractBanditAlgorithm(int armsCount, EnumBanditAlgorithm type)
    {
        this.armsCount = armsCount;
        this.type = type;
        this.rounds = 0;
    }


    public void update(int armId, double reward) {

        pulls[armId]++;
        wins[armId] += reward;
        rounds++;
    }

    public ArrayList<Double> getEmpiricalMeans()
    {
        ArrayList<Double> empiricalMeans = new ArrayList<>(this.armsCount);

        for(int i = 0; i < armsCount; i++)
        {
            if(pulls[i] == 0.0) {
                empiricalMeans.add(i, 0.0);
            } else {
                empiricalMeans.add(i, wins[i] / pulls[i]);
            }
        }

       return empiricalMeans;
    }

    public void setArmsCount(int armsCount) {
        this.armsCount = armsCount;
        this.pulls = new double[armsCount];
        this.wins = new double[armsCount];
    }

    public int getArmsCount() {
        return armsCount;
    }

    public EnumBanditAlgorithm getType() {
        return type;
    }



}
