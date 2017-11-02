package agents;

import algorithms.BanditAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class AbstractAgent implements Agent {

    protected BanditAlgorithm banditAlgorithm;
    protected double cumulativeReward;
    protected int maxSteps;
    protected ArrayList<Integer> armHistory;


    public AbstractAgent(BanditAlgorithm banditAlgorithm, int maxSteps)
    {
        this.banditAlgorithm = banditAlgorithm;
        this.maxSteps = maxSteps;
        this.cumulativeReward = 0;
        this.armHistory = new ArrayList<>();
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

    public void finish()
    {
        System.out.println("Agent is finishing...");
        System.out.println("Cumulative Reward: " + this.cumulativeReward);
        System.out.println("History " + Arrays.toString(armHistory.toArray()));
    }
}
