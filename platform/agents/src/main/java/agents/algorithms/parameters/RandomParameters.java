package agents.algorithms.parameters;

import agents.algorithms.EnumBanditAlgorithm;

public class RandomParameters extends AbstractAlgorithmParameters {

    public RandomParameters(int seed)
    {
        super(seed, EnumBanditAlgorithm.RANDOM);
    }

}
