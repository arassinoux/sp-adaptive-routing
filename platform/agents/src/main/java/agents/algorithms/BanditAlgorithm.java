package agents.algorithms;

import java.util.ArrayList;

public interface BanditAlgorithm {

    ArrayList<Double> getEmpiricalMeans();

    Integer selectArm();

    void update(int arm, double reward);

    void setArmsCount(int armsCount);

    int getArmsCount();

    EnumBanditAlgorithm getType();

}
