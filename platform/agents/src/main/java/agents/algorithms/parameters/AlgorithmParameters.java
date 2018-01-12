package agents.algorithms.parameters;

import agents.algorithms.EnumBanditAlgorithm;

public interface AlgorithmParameters {

    int getSeed();
    void incrementSeed();
    EnumBanditAlgorithm getType();

}
