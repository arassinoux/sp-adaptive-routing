package agents.algorithms;

import agents.algorithms.parameters.AlgorithmParameters;

public class AlgorithmFactory {

    public static BanditAlgorithm createEGreedyAlgorithm(AlgorithmParameters params) {
        return new EpsilonGreedyAlgorithm(params);
    }

    public static BanditAlgorithm createRandomAlgorithm(AlgorithmParameters params) {
        return new RandomAlgorithm(params);
    }

    public static BanditAlgorithm createSoftmaxAlgorithm(AlgorithmParameters params) {
        return new SoftmaxAlgorithm(params);
    }

    public static BanditAlgorithm createPursuitAlgorithm(AlgorithmParameters params) {
        return new PursuitAlgorithm(params);
    }

    public static BanditAlgorithm createUCB1Algorithm(AlgorithmParameters params) {
        return new UCB1Algorithm(params);
    }

    public static BanditAlgorithm createEXP3Algorithm(AlgorithmParameters params) {
        return new EXP3Algorithm(params);
    }


    public static BanditAlgorithm createBanditAlgorithm(EnumBanditAlgorithm algorithmType, AlgorithmParameters params)
    {
        BanditAlgorithm banditAlgorithm = null;

        switch(algorithmType)
        {
            case EGREEDY: {
                banditAlgorithm = createEGreedyAlgorithm(params);
                break;
            }

            case RANDOM: {
                banditAlgorithm = createRandomAlgorithm(params);
                break;
            }

            case SOFTMAX: {
                banditAlgorithm = createSoftmaxAlgorithm(params);
                break;
            }

            case PURSUIT: {
                banditAlgorithm = createPursuitAlgorithm(params);
                break;
            }

            case UCB1: {
                banditAlgorithm = createUCB1Algorithm(params);
                break;
            }

            case EXP3: {
                banditAlgorithm = createEXP3Algorithm(params);
                break;
            }

        }
        return banditAlgorithm;
    }
}
