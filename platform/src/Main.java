import agents.Agent;
import agents.AgentFactory;
import algorithms.BanditAlgorithm;
import algorithms.EpsilonGreedyAlgorithm;
import algorithms.PursuitAlgorithm;
import algorithms.SoftmaxAlgorithm;
import arms.Arm;
import arms.ArmFactory;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        ArmFactory armFactory = new ArmFactory();

        Arm arm1 = armFactory.createBernouilliArm(0, 0.3);
        Arm arm2 = armFactory.createBernouilliArm(1, 0.9);
        ArrayList<Arm> armsList = new ArrayList<>();
        armsList.add(arm1.getId(), arm1);
        armsList.add(arm2.getId(), arm2);


        AgentFactory agentFactory = new AgentFactory();
        //Agent agent = agentFactory.createSimpleAgent(new EpsilonGreedyAlgorithm(armsList,0.5), 50);
        //Agent agent = agentFactory.createSimpleAgent(new SoftmaxAlgorithm(armsList, 0.1), 50);
        Agent agent = agentFactory.createSimpleAgent(new PursuitAlgorithm(armsList, 0.05), 50);

        agent.process();
        agent.finish();
    }

}