import agents.Agent;
import agents.AgentFactory;
import algorithms.*;
import arms.Arm;
import arms.ArmFactory;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        ArmFactory armFactory = new ArmFactory();

        Arm arm1 = armFactory.createBernouilliArm(0, 0.1);
        Arm arm2 = armFactory.createBernouilliArm(1, 0.5);
        Arm arm3 = armFactory.createBernouilliArm(2, 0.5);
        ArrayList<Arm> armsList = new ArrayList<>();
        armsList.add(arm1.getId(), arm1);
        armsList.add(arm2.getId(), arm2);
        armsList.add(arm3.getId(), arm3);


        AgentFactory agentFactory = new AgentFactory();
        //Agent agent = agentFactory.createSimpleAgent(new EpsilonGreedyAlgorithm(armsList,0.5), 50);
        //Agent agent = agentFactory.createSimpleAgent(new SoftmaxAlgorithm(armsList, 0.1), 50);
        //Agent agent = agentFactory.createSimpleAgent(new PursuitAlgorithm(armsList, 0.05), 50);
        Agent agent = agentFactory.createSimpleAgent(new UCB1Algorithm(armsList), 500);


        agent.process();
        agent.finish();
    }

}