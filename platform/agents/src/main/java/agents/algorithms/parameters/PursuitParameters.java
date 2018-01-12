package agents.algorithms.parameters;

import agents.algorithms.EnumBanditAlgorithm;

public class PursuitParameters extends AbstractAlgorithmParameters {

    private double beta;

    public PursuitParameters(int seed, double beta)
    {
        super(seed, EnumBanditAlgorithm.PURSUIT);
        this.beta = beta;
    }

    public double getBeta() {
        return beta;
    }
}
