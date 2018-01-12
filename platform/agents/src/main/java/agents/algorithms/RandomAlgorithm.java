package agents.algorithms;

import agents.algorithms.parameters.AlgorithmParameters;
import agents.algorithms.parameters.RandomParameters;
import java.util.Random;


public class RandomAlgorithm extends AbstractBanditAlgorithm {

    private final RandomParameters parameters;

    public RandomAlgorithm(AlgorithmParameters parameters) {

        super(-1, EnumBanditAlgorithm.RANDOM);
        this.parameters = (RandomParameters)parameters;
        this.seed = parameters.getSeed();
        this.random = new Random(seed);
    }

    @Override
    public Integer selectArm() {
        return random.nextInt(armsCount);
    }

}
