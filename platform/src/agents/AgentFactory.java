package agents;

import algorithms.*;
import arms.Arm;

import java.util.ArrayList;

public class AgentFactory {

    public Agent createSimpleAgent(EnumBanditAlgorithm banditAlgorithm, Object hyperParam, ArrayList<Arm> armsList, int maxSteps) {

        Agent agent;

        switch(banditAlgorithm)
        {
            case EGREEDY: {
                double epsilon = (double) hyperParam;
                agent = new SimpleAgent(new EpsilonGreedyAlgorithm(armsList, epsilon), maxSteps);
                break;
            }
            case SOFTMAX: {
                double tau = (double) hyperParam;
                agent = new SimpleAgent(new SoftmaxAlgorithm(armsList, tau), maxSteps);
                break;
            }
            case PURSUIT: {
                double beta = (double) hyperParam;
                agent = new SimpleAgent(new PursuitAlgorithm(armsList, beta), maxSteps);
                break;
            }
            case UCB1: {
                agent = new SimpleAgent(new UCB1Algorithm(armsList), maxSteps);
                break;
            }
            default:
            {
                return null;
            }
        }
        return agent;
    }

}
