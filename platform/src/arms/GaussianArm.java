package arms;

import java.util.Random;

public class GaussianArm extends AbstractArm {

    private double mean;
    private double std;


    public GaussianArm(int id, double mean, double std, int seed)
    {
        super(id, EnumArm.GAUSSIAN, seed);
        this.mean = mean;
        this.std = std;
    }

    @Override
    public double getReward() {
        return random.nextGaussian() * std + mean;
    }

}
