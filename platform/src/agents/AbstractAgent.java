package agents;

import algorithms.BanditAlgorithm;

import java.util.ArrayList;

public abstract class AbstractAgent implements Agent {

    protected BanditAlgorithm banditAlgorithm;
    protected double cumulativeReward;
    protected ArrayList<Integer> armHistory;


    public AbstractAgent(BanditAlgorithm banditAlgorithm)
    {
        this.banditAlgorithm = banditAlgorithm;
        this.cumulativeReward = 0;
        this.armHistory = new ArrayList<>();
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
}
