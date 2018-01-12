package agents.algorithms.parameters;

import agents.algorithms.EnumBanditAlgorithm;

public class SoftmaxParameters extends AbstractAlgorithmParameters {

    private double tau;

    public SoftmaxParameters(int seed, double tau)
    {
        super(seed, EnumBanditAlgorithm.SOFTMAX);
        this.tau = tau;
    }

    public double getTau() {
        return tau;
    }
}
