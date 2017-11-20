package arms;

import java.util.Random;

public abstract class AbstractArm implements Arm {

    protected final int id;
    protected EnumArm armType;
    protected Random random;
    protected int seed;

    public AbstractArm(int id, EnumArm armType, int seed)
    {
        this.id = id;
        this.armType = armType;
        this.seed = seed;
        this.random = new Random(this.seed);
    }

    @Override
    public int getId() {
        return id;
    }

}
