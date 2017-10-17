package arms;

import java.util.Random;

public class BernouilliArm extends AbstractArm {

    private final double p;
    private Random random;

    public BernouilliArm(int id, double p)
    {
        super(id);
        this.p = p;
        this.random = new Random();
    }


    @Override
    public double getReward() {
        if(random.nextDouble() > p)
            return 0;
        else return 1;
    }

}
