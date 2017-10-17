package algorithms;

import arms.Arm;

import java.util.ArrayList;

public interface BanditAlgorithm {

    ArrayList<Arm> getArmsList();
    Arm selectArm();
    void update(Arm arm, double reward);
    void reset();
}
