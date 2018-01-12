package arms.parameters;

import arms.EnumArm;

public class GaussianParameters extends AbstractArmParameters {

    private double mean;
    private double std;

    GaussianParameters(int seed, double mean, double std)
    {
        super(seed, EnumArm.GAUSSIAN);
        this.mean = mean;
        this.std = std;
    }

    public double getMean() {
        return mean;
    }

    public double getStd() {
        return std;
    }
}
