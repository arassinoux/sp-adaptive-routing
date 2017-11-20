package arms;

public class UniformArm extends AbstractArm {


    public UniformArm(int id, int seed)
    {
        super(id, EnumArm.UNIFORM, seed);
    }

    @Override
    public double getReward() {
        return random.nextDouble();
    }

}
