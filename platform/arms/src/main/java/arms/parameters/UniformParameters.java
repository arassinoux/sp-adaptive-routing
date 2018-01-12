package arms.parameters;

import arms.EnumArm;

public class UniformParameters extends AbstractArmParameters {

    UniformParameters(int seed)
    {
        super(seed, EnumArm.UNIFORM);
    }

}
