package algorithms;

import arms.Arm;

import java.util.ArrayList;

public interface BanditAlgorithm {

    ArrayList<Arm> getArmsList();
    ArrayList<Double> getEmpiricalMeans();
    Arm selectArm();
    void update(Arm arm, double reward);
    void reset();
}
