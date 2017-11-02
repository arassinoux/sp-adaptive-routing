import agents.Agent;
import agents.AgentFactory;
import algorithms.*;
import arms.ArmFactory;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        ArmFactory armFactory = new ArmFactory();

        double[] paramsBernouilli = new double[]{0.1, 0.5, 0.1, 0.2};
        ArrayList armsList = armFactory.generateArmsList(4, "Bernouilli", paramsBernouilli);

        AgentFactory agentFactory = new AgentFactory();
        //Agent agent = agentFactory.createSimpleAgent(new EpsilonGreedyAlgorithm(armsList,0.5), 50);
        //Agent agent = agentFactory.createSimpleAgent(new SoftmaxAlgorithm(armsList, 0.1), 50);
        //Agent agent = agentFactory.createSimpleAgent(new PursuitAlgorithm(armsList, 0.05), 50);
        Agent agent = agentFactory.createSimpleAgent(new UCB1Algorithm(armsList), 500);


        agent.process();
        agent.finish();
    }

}