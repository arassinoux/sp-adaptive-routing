package arms;

import java.util.Random;

public class GaussianArm extends AbstractArm {

    private Random random;
    private double mean;
    private double std;


    public GaussianArm(int id, double mean, double std)
    {
        super(id, EnumArm.GAUSSIAN);
        this.random = new Random();
        this.mean = mean;
        this.std = std;
    }

    @Override
    public double getReward() {
        return random.nextGaussian() * std + mean;
    }

}
