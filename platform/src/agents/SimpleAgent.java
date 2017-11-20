package agents;

import algorithms.BanditAlgorithm;
import arms.Arm;
import helpers.CSVWriter;

import java.util.Arrays;
import java.util.Collections;

public class SimpleAgent extends AbstractAgent {

    public SimpleAgent(BanditAlgorithm banditAlgorithm, int maxSteps) {

        super(banditAlgorithm, maxSteps);
    }

    @Override
    public void process() {

        for(int step = 0; step < maxSteps; step++) {

            Arm selectedArm = banditAlgorithm.selectArm();

            setArmInHistory(selectedArm.getId());

            double reward = selectedArm.getReward();
            cumulativeReward += reward;
            banditAlgorithm.update(selectedArm, reward);

            double maxEmpiricalMean = Collections.max(banditAlgorithm.getEmpiricalMeans());

            regret[step] = maxEmpiricalMean - reward;
            cumulativeRegret[step] = (maxEmpiricalMean * (step)) - cumulativeReward;

        }

    }

    public void outputRegret(CSVWriter writer)
    {
        writer.writeArray(regret, banditAlgorithm);
    }

    public void finish() {

        System.out.println("Agent is finishing...");
        System.out.println("Cumulative Reward: " + this.cumulativeReward);
        //System.out.println("Regret per turn: " + Arrays.toString(regret));

        //System.out.println("Cumulative regret: " + Arrays.toString(cumulativeRegret));
        System.out.println("History " + Arrays.toString(armHistory.toArray()));
    }
}