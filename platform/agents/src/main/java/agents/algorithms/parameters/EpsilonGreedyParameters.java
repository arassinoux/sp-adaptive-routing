package agents.algorithms.parameters;

import agents.algorithms.EnumBanditAlgorithm;

public class EpsilonGreedyParameters extends AbstractAlgorithmParameters {

    private double epsilon;

    public EpsilonGreedyParameters(int seed, double epsilon)
    {
        super(seed, EnumBanditAlgorithm.EGREEDY);
        this.epsilon = epsilon;
    }

    public double getEpsilon() {
        return epsilon;
    }
}
