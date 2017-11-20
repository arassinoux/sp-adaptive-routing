package arms;

import java.util.Random;

public class BernouilliArm extends AbstractArm {

    private final double p;

    public BernouilliArm(int id, double p, int seed)
    {
        super(id, EnumArm.BERNOUILLI, seed);
        this.p = p;
    }

    @Override
    public double getReward() {
        if(random.nextDouble() > p)
            return 0;
        else return 1;
    }

}
