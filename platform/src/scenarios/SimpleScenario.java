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
    private double[] hyperParam;

    private ArmFactory armFactory = new ArmFactory();
    private AgentFactory agentFactory = new AgentFactory();
    private ArrayList armsList;

    private final int initialSeed = 100;
    private int currentSeed = initialSeed;

    public SimpleScenario(int agentCount) {
        this.agentCount = agentCount;

        armType = EnumArm.BIAS_BERNOUILLI;
        // params = new double[][]{{0.3, 0.7, 0.6}, {0.7, 0.9, 0.8}};  //Triangular
        //params = new double[][]{{0.3, 0.2}, {0.7, 0.2}};
        //params = new double[]{0.3, 0.2, 0.1, 0.5, 0.4, 0.8, 0.8, 0.3, 0.2, 0.1, 0, 0, 0, 0, 0, 0.7, 0.9, 0.2, 0.1, 0.1};
        armCount = 20;

        hyperParam = new double[]{0.0, 0.1, 0.1, 0.1, 0.0, 0.1}; // random, epsilon, softmax, pursuit, ucb1, exp3
    }

    public void play()
    {
        for(EnumBanditAlgorithm bandit : EnumBanditAlgorithm.values()) {

            CSVWriter writer = new CSVWriter("data/regret_" + bandit.name());

            currentSeed = initialSeed;

            for (int i = 0; i < agentCount; i++) {

                armsList = armFactory.generateArmsList(armCount, armType, params, currentSeed);

                Agent agent = agentFactory.createSimpleAgent(bandit, hyperParam[bandit.ordinal()], armsList, 2000);

                agent.process();
                agent.outputRegret(writer);
                agent.finish();
                currentSeed++;
            }

            writer.dispose();
        }

    }


}
