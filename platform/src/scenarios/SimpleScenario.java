package scenarios;

import agents.Agent;
import agents.AgentFactory;
import algorithms.EnumBanditAlgorithm;
import arms.ArmFactory;
import arms.EnumArm;
import helpers.CSVWriter;

import java.util.ArrayList;

public class SimpleScenario {

    private int agentCount;
    private CSVWriter writer;
    private EnumArm armType;
    private int armCount;
    private Object params;
    private EnumBanditAlgorithm banditType;
    private double[] hyperParam;

    private ArmFactory armFactory = new ArmFactory();
    private AgentFactory agentFactory = new AgentFactory();
    private ArrayList armsList;

    public SimpleScenario(int agentCount) {
        this.agentCount = agentCount;

        armType = EnumArm.GAUSSIAN;
        // params = new double[][]{{0.3, 0.7, 0.6}, {0.7, 0.9, 0.8}};  //Triangular
        params = new double[][]{{0.3, 0.2}, {0.7, 0.2}};
        armCount = 2;

        armsList = armFactory.generateArmsList(armCount, armType, params);

        hyperParam = new double[]{0.0, 0.1, 0.1, 0.1, 0.0}; // random, epsilon, softmax, pursuit, ucb1
    }

    public void play()
    {
        for(EnumBanditAlgorithm bandit : EnumBanditAlgorithm.values()) {

            CSVWriter writer = new CSVWriter("regret_" + bandit.name());

            for (int i = 0; i < agentCount; i++) {

                Agent agent = agentFactory.createSimpleAgent(bandit, hyperParam[bandit.ordinal()], armsList, 2000);

                agent.process();
                agent.outputRegret(writer);
                agent.finish();
            }

            writer.dispose();
        }

    }


}
