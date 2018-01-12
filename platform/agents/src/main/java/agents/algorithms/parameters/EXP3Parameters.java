package agents.algorithms.parameters;

import agents.algorithms.EnumBanditAlgorithm;

public class EXP3Parameters extends AbstractAlgorithmParameters {

    private double gamma;

    public EXP3Parameters(int seed, double gamma)
    {
        super(seed, EnumBanditAlgorithm.EXP3);
        this.gamma = gamma;
    }

    public double getGamma() {
        return gamma;
    }
}
