package arms;

import java.util.Random;

abstract class AbstractArm implements Arm {

    protected EnumArm armType;
    protected Random random;
    protected int seed;
    protected int armId;

    AbstractArm(EnumArm armType, int seed)
    {
        this.armType = armType;
        this.seed = seed;
        this.random = new Random(this.seed);
        this.armId = -1;
    }

    public EnumArm getArmType() {
        return armType;
    }

    @Override
    public void setArmId(int armId) {
        this.armId = armId;
    }

    @Override
    public int getArmId() {
        return armId;
    }
}
