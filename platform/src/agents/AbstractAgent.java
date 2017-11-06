package agents;

import algorithms.BanditAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class AbstractAgent implements Agent {

    protected BanditAlgorithm banditAlgorithm;
    protected double cumulativeReward;
    protected int maxSteps;
    protected ArrayList<Integer> armHistory;
    protected double[] regret;
    protected double[] cumulativeRegret;


    public AbstractAgent(BanditAlgorithm banditAlgorithm, int maxSteps)
    {
        this.banditAlgorithm = banditAlgorithm;
        this.maxSteps = maxSteps;
        this.cumulativeReward = 0;
        this.armHistory = new ArrayList<>();
        this.regret = new double[maxSteps];
        this.cumulativeRegret = new double[maxSteps];
        this.init();
    }

    public void init()
    {
        System.out.println("Agent is starting - Learning Algorithm: " + banditAlgorithm.getClass().getName());
    }

    @Override
    public double getCumulativeReward() {
        return cumulativeReward;
    }

    public void setArmInHistory(int armId)
    {
        armHistory.add(armId);
    }

    public ArrayList<Integer> getArmHistory()
    {
        return armHistory;
    }

    public BanditAlgorithm getBanditAlgorithm() {
        return banditAlgorithm;
    }

    public double[] getRegret() {
        return regret;
    }

    public void finish()
    {
    }
}
