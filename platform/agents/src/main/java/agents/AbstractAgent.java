package agents;

import agents.algorithms.AlgorithmFactory;
import agents.algorithms.BanditAlgorithm;
import agents.algorithms.parameters.AlgorithmParameters;

public abstract class AbstractAgent implements Agent {

    BanditAlgorithm banditAlgorithm;
    AlgorithmParameters algorithmParameters;
    int agentId;

    private int maxSteps;

    int plays;


    AbstractAgent(BanditAlgorithm banditAlgorithm, int agentId)
    {
        this.agentId = agentId;
        this.banditAlgorithm = banditAlgorithm;
        this.maxSteps = 0;

        this.init();
    }

    public void init()
    {
        System.out.println("agents.Agent is starting - Learning AlgorithmFactory: " + banditAlgorithm.getClass().getName());
    }


    public BanditAlgorithm getBanditAlgorithm() {
        return banditAlgorithm;
    }

    public int getMaxSteps() {
        return maxSteps;
    }

    public void setMaxSteps(int maxSteps) {
        this.maxSteps = maxSteps;

    }

    public void reset()
    {

        this.init();
        this.algorithmParameters.incrementSeed(); //change seed for algorithm
        int lastArmsCount = banditAlgorithm.getArmsCount();
        this.banditAlgorithm = null;
        this.banditAlgorithm = AlgorithmFactory.createBanditAlgorithm(algorithmParameters.getType(), algorithmParameters);
        banditAlgorithm.setArmsCount(lastArmsCount);
    }

    public int getId() {
        return agentId;
    }

    public void setId(int id) {
        agentId = id;
    }

    public void setPlays(int plays)
    {
        this.plays = plays;
    }

    @Override
    public int getPlays() {
        return plays;
    }

    public void setAlgorithmParameters(AlgorithmParameters algorithmParameters) {
        this.algorithmParameters = algorithmParameters;
    }
}
