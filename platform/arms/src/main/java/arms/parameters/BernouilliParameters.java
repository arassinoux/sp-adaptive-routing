package arms.parameters;

import arms.EnumArm;

public class BernouilliParameters extends AbstractArmParameters {

    private double p;

    public BernouilliParameters(int seed, double p)
    {
        super(seed, EnumArm.BERNOUILLI);
        this.p = p;
    }

    public double getP() {
        return p;
    }
}
