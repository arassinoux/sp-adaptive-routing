import agents.Agent;
import agents.AgentFactory;
import algorithms.*;
import arms.ArmFactory;
import arms.EnumArm;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        ArmFactory armFactory = new ArmFactory();

        double[] paramsBernouilli = new double[]{0.1, 0.5, 0.1, 0.2};
        ArrayList armsList = armFactory.generateArmsList(4, EnumArm.BERNOUILLI, paramsBernouilli);

        AgentFactory agentFactory = new AgentFactory();

        Agent agent = agentFactory.createSimpleAgent(EnumBanditAlgorithm.PURSUIT, 0.05, armsList, 500);

        agent.process();
        agent.finish();
    }

}