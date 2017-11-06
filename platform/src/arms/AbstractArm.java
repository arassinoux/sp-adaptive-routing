package arms;

public abstract class AbstractArm implements Arm {

    protected final int id;
    protected EnumArm armType;

    public AbstractArm(int id, EnumArm armType)
    {
        this.id = id;
        this.armType = armType;
    }

    @Override
    public int getId() {
        return id;
    }
}
