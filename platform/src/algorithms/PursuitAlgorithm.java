package algorithms;

import algorithms.helpers.RandomCollection;
import arms.Arm;

import java.util.*;
import java.util.function.Consumer;

public class PursuitAlgorithm extends AbstractBanditAlgorithm {

    private RandomCollection<Arm> weightedArms;
    private ArrayList<Double> probabilities;
    private double beta;
    private Random random;

    public PursuitAlgorithm(ArrayList<Arm> armsList, double beta) {
        super(armsList);

        Double[] initValues = new Double[armsList.size()];
        this.probabilities = new ArrayList<>(Arrays.asList(initValues));
        Collections.fill(this.probabilities, 1.0/armsList.size());

        this.beta = beta;
        this.random = new Random();
    }

    @Override
    public Arm selectArm() {
        ArrayList<Double> empiricalMeans = getEmpiricalMeans();
        double maxEmpiricalMean = Collections.max(empiricalMeans);

        this.weightedArms = new RandomCollection();
        for(Arm arm : armsList)
        {
            double currentProbability = probabilities.get(arm.getId());
            weightedArms.add(currentProbability, arm);

            if(empiricalMeans.get(arm.getId()) == maxEmpiricalMean)
            {
                probabilities.set(arm.getId(), currentProbability + beta * (1 - currentProbability));
            }
            else
            {
                probabilities.set(arm.getId(), currentProbability + beta * (0 - currentProbability));
            }
        }

        return weightedArms.next();
    }


    @Override
    public void reset() {

    }
}
