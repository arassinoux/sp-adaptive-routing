package arms.parameters;

import arms.EnumArm;

public class ArmParametersFactory {

    EnumArm type;
    String[] args;

    public ArmParametersFactory(String args[])
    {
        this.type = EnumArm.valueOf(args[0]);
        this.args = args;
    }

    public ArmParameters getParameters()
    {
        ArmParameters params = null;

        switch(type)
        {
            case BERNOUILLI:
            {
                int seed = Integer.parseInt(args[1]);
                double p = Double.parseDouble(args[2]);
                params = new BernouilliParameters(seed, p);
                break;
            }

            case GAUSSIAN:
            {
                int seed = Integer.parseInt(args[1]);
                double mean = Double.parseDouble(args[2]);
                double std = Double.parseDouble(args[3]);
                params = new GaussianParameters(seed, mean, std);
                break;
            }

            case UNIFORM:
            {
                int seed = Integer.parseInt(args[1]);
                params = new UniformParameters(seed);
                break;
            }

            case TRAFFIC:
            {
                int seed = Integer.parseInt(args[1]);
                params = new TrafficParameters(seed);
                break;
            }

            case ADVERSARIAL:
            {
                int seed = Integer.parseInt(args[1]);
                params = new AdversarialParameters(seed);
                break;
            }
        }
        return params;
    }

}
