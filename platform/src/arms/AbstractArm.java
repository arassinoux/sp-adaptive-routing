package arms;

public abstract class AbstractArm implements Arm {

    protected final int id;

    public AbstractArm(int id)
    {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }
}
