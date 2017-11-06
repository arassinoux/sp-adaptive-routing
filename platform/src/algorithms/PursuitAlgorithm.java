package algorithms;

import algorithms.helpers.RandomCollection;
import arms.Arm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;


public class PursuitAlgorithm extends AbstractBanditAlgorithm {

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
        this.name = "pursuit";
    }

    @Override
    public Arm selectArm() {
        ArrayList<Double> empiricalMeans = getEmpiricalMeans();
        double maxEmpiricalMean = Collections.max(empiricalMeans);


        double p = Math.random();
        Arm chosenArm = null;
        double cumulativeProbability = 0.0;
        for (Arm arm : armsList) {
            cumulativeProbability += probabilities.get(arm.getId());
            if (p <= cumulativeProbability && probabilities.get(arm.getId()) != 0) {

                if(chosenArm == null)
                    chosenArm = arm;
            }

            if(empiricalMeans.get(arm.getId()) == maxEmpiricalMean)
            {
                probabilities.set(arm.getId(), probabilities.get(arm.getId()) + beta * (1 - probabilities.get(arm.getId())));
            }
            else
            {
                probabilities.set(arm.getId(), probabilities.get(arm.getId()) + beta * (0 - probabilities.get(arm.getId())));
            }
        }
        return chosenArm;
    }


    @Override
    public void reset() {

    }
}
