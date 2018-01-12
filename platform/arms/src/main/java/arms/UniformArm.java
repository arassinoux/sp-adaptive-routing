package arms;

import arms.parameters.ArmParameters;

public class UniformArm extends AbstractArm {

    UniformArm(ArmParameters params)
    {
        super(EnumArm.UNIFORM, params.getSeed());
    }

    @Override
    public double getReward() {
        return random.nextDouble();
    }

    @Override
    public double expectedValue() {
        return 1/2;
    }
}
