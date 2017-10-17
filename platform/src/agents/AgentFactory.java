package agents;

import algorithms.BanditAlgorithm;

public class AgentFactory {

    public Agent createSimpleAgent(BanditAlgorithm banditAlgorithm) {
        return new SimpleAgent(banditAlgorithm);
    }
}
