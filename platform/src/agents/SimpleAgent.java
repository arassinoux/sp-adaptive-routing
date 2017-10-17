package agents;

import algorithms.BanditAlgorithm;
import arms.Arm;

public class SimpleAgent extends AbstractAgent {

    public SimpleAgent(BanditAlgorithm banditAlgorithm) {
        super(banditAlgorithm);

    }

    @Override
    public void process() {
        Arm selectedArm = banditAlgorithm.selectArm();

        setArmInHistory(selectedArm.getId());

        double reward = selectedArm.getReward();
        cumulativeReward += reward;
        banditAlgorithm.update(selectedArm, reward);

    }
}
