import agents.Agent;
import agents.AgentFactory;
import algorithms.BanditAlgorithm;
import algorithms.EpsilonGreedyAlgorithm;
import arms.Arm;
import arms.ArmFactory;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        ArmFactory armFactory = new ArmFactory();

        Arm arm1 = armFactory.createBernouilliArm(0, 0.9);
        Arm arm2 = armFactory.createBernouilliArm(1, 0.1);
        ArrayList<Arm> armsList = new ArrayList<>();
        armsList.add(arm1.getId(), arm1);
        armsList.add(arm2.getId(), arm2);


        AgentFactory agentFactory = new AgentFactory();
        Agent agent = agentFactory.createSimpleAgent(new EpsilonGreedyAlgorithm(armsList, 0.2));

        for(int steps=0; steps < 50; steps++)
            agent.process();

        System.out.println("Cumulative Reward: " + agent.getCumulativeReward());
        System.out.println(Arrays.toString(agent.getArmHistory().toArray()));
    }

}