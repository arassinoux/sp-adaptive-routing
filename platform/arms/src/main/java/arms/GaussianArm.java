package arms;

import arms.parameters.GaussianParameters;
import arms.parameters.ArmParameters;

public class GaussianArm extends AbstractArm {

    private final GaussianParameters params;

    GaussianArm(ArmParameters params)
    {
        super(EnumArm.GAUSSIAN, params.getSeed());
        this.params = (GaussianParameters) params;
    }

    @Override
    public double getReward() {
        double reward = random.nextGaussian() * params.getStd() +  params.getMean();
        if(reward < 0)
            return 0;
        else if(reward > 1)
            return 1;
        else return reward;
    }

    @Override
    public double expectedValue() {
        return params.getMean();
    }
}
