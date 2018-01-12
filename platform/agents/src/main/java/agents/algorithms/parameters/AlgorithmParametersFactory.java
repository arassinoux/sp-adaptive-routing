package agents.algorithms.parameters;

import agents.algorithms.EnumBanditAlgorithm;

public class AlgorithmParametersFactory {

    EnumBanditAlgorithm type;
    String[] args;

    public AlgorithmParametersFactory(String args[])
    {
        this.type = EnumBanditAlgorithm.valueOf(args[0]);
        this.args = args;
    }

    public AlgorithmParameters getParameters()
    {
        AlgorithmParameters params = null;

        switch(type)
        {
            case EGREEDY:
            {
                int seed = Integer.parseInt(args[1]);
                double epsilon = Double.parseDouble(args[2]);
                params = new EpsilonGreedyParameters(seed, epsilon);
                break;
            }

            case RANDOM:
            {
                int seed = Integer.parseInt(args[1]);
                params = new RandomParameters(seed);
                break;
            }

            case SOFTMAX:
            {
                int seed = Integer.parseInt(args[1]);
                double tau = Double.parseDouble(args[2]);
                params = new SoftmaxParameters(seed, tau);
                break;
            }

            case PURSUIT:
            {
                int seed = Integer.parseInt(args[1]);
                double beta = Double.parseDouble(args[2]);
                params = new PursuitParameters(seed, beta);
                break;
            }

            case UCB1:
            {
                int seed = Integer.parseInt(args[1]);
                params = new UCB1Parameters(seed);
                break;
            }

            case EXP3:
            {
                int seed = Integer.parseInt(args[1]);
                double gamma = Double.parseDouble(args[2]);
                params = new EXP3Parameters(seed, gamma);
                break;
            }


        }
        return params;
    }

}
