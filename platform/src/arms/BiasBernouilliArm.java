package arms;

import java.util.Random;

public class BiasBernouilliArm extends AbstractArm {

    private int plays;
    private int activeMin = 50;
    private int activeMax = 130;

    public BiasBernouilliArm(int id, int seed)
    {
        super(id, EnumArm.BIAS_BERNOUILLI, seed);
        this.plays = 1;

    }

    @Override
    public double getReward() {
        double reward;

        if(plays > activeMin && plays < activeMax)
            reward = 1;
        else reward = 0;

        plays++;
        return reward;
    }

}
