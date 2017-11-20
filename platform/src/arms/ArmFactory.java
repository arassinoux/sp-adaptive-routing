package arms;

import java.util.ArrayList;

public class ArmFactory {

    public Arm createBernouilliArm(int id, double p, int seed) {
        return new BernouilliArm(id,  p, seed);
    }

    public Arm createUniformArm(int id, int seed) {
        return new UniformArm(id, seed);
    }

    public Arm createGaussianArm(int id, double mean, double std, int seed) {
        return new GaussianArm(id, mean, std, seed);
    }

    public Arm createTriangularArm(int id, double a, double b, double c, int seed) {
        return new TriangularArm(id, a, b, c, seed);
    }

    public Arm createBiasBernouilliArm(int id, int seed) {
        return new BiasBernouilliArm(id, seed);
    }


    public ArrayList<Arm> generateArmsList(int number, EnumArm armType, Object hyperParams, int seed)
    {
        ArrayList<Arm> armsList = new ArrayList<>();

        switch(armType)
        {
            case BERNOUILLI:
            {
                double[] p = (double[])hyperParams;

                for(int i=0; i < number; i++)
                {
                    armsList.add(createBernouilliArm(i, p[i], seed));
                }
                break;
            }
            case UNIFORM:
            {
                for(int i=0; i < number; i++)
                {
                    armsList.add(createUniformArm(i, seed));
                }
                break;
            }
            case GAUSSIAN: {
                double[][] params = (double[][]) hyperParams;

                for (int i = 0; i < number; i++) {
                    armsList.add(createGaussianArm(i, params[i][0], params[i][1], seed));
                }
                break;
            }
            case TRIANGULAR:
            {
                double[][] params = (double[][])hyperParams;

                for(int i=0; i < number; i++)
                {
                    armsList.add(createTriangularArm(i, params[i][0], params[i][1], params[i][2], seed));
                }
                break;
            }
            case BIAS_BERNOUILLI:
            {
                for(int i=0; i < number; i++)
                {
                    armsList.add(createBiasBernouilliArm(i, seed));
                }
                break;
            }
        }
        return armsList;
    }
}
