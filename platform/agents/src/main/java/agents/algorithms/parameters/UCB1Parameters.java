package agents.algorithms.parameters;

import agents.algorithms.EnumBanditAlgorithm;

public class UCB1Parameters extends AbstractAlgorithmParameters {

    public UCB1Parameters(int seed)
    {
        super(seed, EnumBanditAlgorithm.UCB1);
    }

}
