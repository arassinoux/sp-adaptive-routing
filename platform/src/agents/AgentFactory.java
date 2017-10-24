package agents;

import algorithms.BanditAlgorithm;

public class AgentFactory {

    public Agent createSimpleAgent(BanditAlgorithm banditAlgorithm, int maxSteps) {
        return new SimpleAgent(banditAlgorithm, maxSteps);
    }
}
