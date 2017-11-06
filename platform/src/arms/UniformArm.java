package arms;

import java.util.Random;

public class UniformArm extends AbstractArm {

    private Random random;


    public UniformArm(int id)
    {
        super(id, EnumArm.UNIFORM);
        this.random = new Random();
    }

    @Override
    public double getReward() {
        return random.nextDouble();
    }

}
