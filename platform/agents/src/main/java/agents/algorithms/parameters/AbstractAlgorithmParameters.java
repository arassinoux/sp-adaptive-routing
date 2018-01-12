package agents.algorithms.parameters;

import agents.algorithms.EnumBanditAlgorithm;

public class AbstractAlgorithmParameters implements AlgorithmParameters {

    int seed;
    EnumBanditAlgorithm type;

    public AbstractAlgorithmParameters(int seed, EnumBanditAlgorithm type)
    {
        this.seed = seed;
        this.type = type;
    }

    public int getSeed() {
        return seed;
    }

    public void incrementSeed() {
        this.seed =seed + 1;
    }

    @Override
    public EnumBanditAlgorithm getType() {
        return type;
    }
}
