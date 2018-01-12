package agents;

import agents.algorithms.BanditAlgorithm;
import agents.algorithms.parameters.AlgorithmParameters;

public interface Agent {

    void process(int selectedArm, double reward);
    int selectArm();
    void init();
    void finish();


    BanditAlgorithm getBanditAlgorithm();

    int getMaxSteps();
    void setMaxSteps(int maxSteps);
    int getId();
    void setId(int id);
    void reset();
    void setPlays(int plays);
    int getPlays();
    void setAlgorithmParameters(AlgorithmParameters algorithmParameters);
}
