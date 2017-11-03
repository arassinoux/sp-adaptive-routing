package arms;

import java.util.ArrayList;

public class ArmFactory {

    public Arm createBernouilliArm(int id, double p) {
        return new BernouilliArm(id, p);
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
        }
        return armsList;
    }
}
