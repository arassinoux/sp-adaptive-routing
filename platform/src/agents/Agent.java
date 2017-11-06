package agents;

import algorithms.BanditAlgorithm;
import helpers.CSVWriter;

import java.util.ArrayList;

public interface Agent {

    void process();
    void init();
    void finish();
    double getCumulativeReward();
    void setArmInHistory(int armId);
    ArrayList<Integer> getArmHistory();
    BanditAlgorithm getBanditAlgorithm();
    void outputRegret(CSVWriter writer);
}
