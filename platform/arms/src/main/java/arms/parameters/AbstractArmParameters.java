package arms.parameters;


import arms.EnumArm;

public class AbstractArmParameters implements ArmParameters {

    int seed;
    EnumArm type;

    public AbstractArmParameters(int seed, EnumArm type)
    {
        this.seed = seed;
        this.type = type;
    }

    public int getSeed() {
        return seed;
    }

    @Override
    public EnumArm getType() {
        return type;
    }
}
