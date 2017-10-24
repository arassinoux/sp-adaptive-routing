package agents;

import algorithms.BanditAlgorithm;
import arms.Arm;

public class SimpleAgent extends AbstractAgent {

    public SimpleAgent(BanditAlgorithm banditAlgorithm, int maxSteps) {
        super(banditAlgorithm, maxSteps);
    }

    @Override
    public void process() {

        for(int steps = 0; steps < maxSteps; steps++) {
            Arm selectedArm = banditAlgorithm.selectArm();

            setArmInHistory(selectedArm.getId());

            double reward = selectedArm.getReward();
            cumulativeReward += reward;
            banditAlgorithm.update(selectedArm, reward);
        }

    }
}
