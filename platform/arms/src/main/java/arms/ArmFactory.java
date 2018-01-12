package arms;

import arms.parameters.ArmParameters;

public class ArmFactory {

    public static Arm createBernouilliArm(ArmParameters params) {
        return new BernouilliArm(params);
    }
    public static Arm createUniformArm(ArmParameters params) {
        return new UniformArm(params);
    }
    public static Arm createGaussianArm(ArmParameters params) {
        return new GaussianArm(params);
    }
    public static Arm createTrafficArm(ArmParameters params) { return new TrafficArm(params); }
    public static Arm createAdversarialArm(ArmParameters params) { return new AdversarialArm(params); }


    public static Arm createArm(EnumArm armType, ArmParameters params)
    {
        Arm arm = null;

        switch(armType)
        {
            case BERNOUILLI: {
                arm = createBernouilliArm(params);
                break;
            }
            case UNIFORM: {
                arm = createUniformArm(params);
                break;
            }
            case GAUSSIAN: {
                arm = createGaussianArm(params);
                break;
            }

            case TRAFFIC: {
                arm = createTrafficArm(params);
                break;
            }

            case ADVERSARIAL: {
                arm = createAdversarialArm(params);
                break;
            }
        }
        return arm;
    }
}
