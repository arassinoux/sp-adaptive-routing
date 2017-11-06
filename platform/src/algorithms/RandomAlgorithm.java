package algorithms;

import arms.Arm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;


public class RandomAlgorithm extends AbstractBanditAlgorithm {

    private Random random;

    public RandomAlgorithm(ArrayList<Arm> armsList) {
        super(armsList);

        this.random = new Random();
        this.name = "random";
    }

    @Override
    public Arm selectArm() {
        int index = random.nextInt(armsList.size());
        return armsList.get(index);
    }


    @Override
    public void reset() {

    }
}
