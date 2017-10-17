package agents;

import java.util.ArrayList;

public interface Agent {

    void process();
    double getCumulativeReward();
    void setArmInHistory(int armId);
    ArrayList<Integer> getArmHistory();
}
