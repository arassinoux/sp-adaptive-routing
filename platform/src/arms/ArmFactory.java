package arms;

import java.util.ArrayList;

public class ArmFactory {

    public Arm createBernouilliArm(int id, double p) {
        return new BernouilliArm(id,  p);
    }

    public Arm createUniformArm(int id) {
        return new UniformArm(id);
    }

    public Arm createGaussianArm(int id, double mean, double std) {
        return new GaussianArm(id, mean, std);
    }

    public Arm createTriangularArm(int id, double a, double b, double c) {
        return new TriangularArm(id, a, b, c);
    }


    public ArrayList<Arm> generateArmsList(int number, EnumArm armType, Object hyperParams)
    {
        ArrayList<Arm> armsList = new ArrayList<>();

        switch(armType)
        {
            case BERNOUILLI:
            {
                double[] p = (double[])hyperParams;

                for(int i=0; i < number; i++)
                {
                    armsList.add(createBernouilliArm(i, p[i]));
                }
                break;
            }
            case UNIFORM:
            {
                for(int i=0; i < number; i++)
                {
                    armsList.add(createUniformArm(i));
                }
                break;
            }
            case GAUSSIAN: {
                double[][] params = (double[][]) hyperParams;

                for (int i = 0; i < number; i++) {
                    armsList.add(createGaussianArm(i, params[i][0], params[i][1]));
                }
                break;
            }
            case TRIANGULAR:
            {
                double[][] params = (double[][])hyperParams;

                for(int i=0; i < number; i++)
                {
                    armsList.add(createTriangularArm(i, params[i][0], params[i][1], params[i][2]));
                }
                break;
            }
        }
        return armsList;
    }
}
