package arms;

import arms.parameters.BernouilliParameters;
import arms.parameters.ArmParameters;

public class BernouilliArm extends AbstractArm {

    private final BernouilliParameters params;

    BernouilliArm(ArmParameters params)
    {
        super(EnumArm.BERNOUILLI, params.getSeed());
        this.params = (BernouilliParameters)params;
    }

    @Override
    public double getReward() {
        if(random.nextDouble() > params.getP())
            return 0;
        else return 1;
    }

    @Override
    public double expectedValue() {
        return params.getP();
    }
}
