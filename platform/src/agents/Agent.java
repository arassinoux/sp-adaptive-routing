package agents;

import java.util.ArrayList;

public interface Agent {

    void process();
    void init();
    void finish();
    double getCumulativeReward();
    void setArmInHistory(int armId);
    ArrayList<Integer> getArmHistory();
}
