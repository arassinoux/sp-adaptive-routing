package algorithms;

import arms.Arm;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class AbstractBanditAlgorithm implements BanditAlgorithm {

    protected ArrayList<Arm> armsList;
    protected double[] wins;
    protected double[] pulls;
    protected int rounds;
    protected String name;

    public AbstractBanditAlgorithm(ArrayList<Arm> armsList)
    {
        this.armsList = armsList;
        this.pulls = new double[armsList.size()];
        this.wins = new double[armsList.size()];
        this.rounds = 0;
    }

    @Override
    public ArrayList<Arm> getArmsList() {
        return armsList;
    }

    public String getName()
    { return name; }


    public void update(Arm arm, double reward) {
        int armId = arm.getId();

        pulls[armId]++;
        wins[armId] += reward;
        //System.out.println(reward);
        rounds++;
    }

    public ArrayList<Double> getEmpiricalMeans()
    {
        ArrayList<Double> empiricalMeans = new ArrayList<>(armsList.size());

        for(Arm arm : armsList)
        {
            if(pulls[arm.getId()] == 0.0) {
                empiricalMeans.add(arm.getId(), 0.0);
            } else {
                empiricalMeans.add(arm.getId(), wins[arm.getId()] / pulls[arm.getId()]);
            }
        }

        //System.out.println(empiricalMeans);
       return empiricalMeans;
    }
}
