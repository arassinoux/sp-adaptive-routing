package arms;

import arms.parameters.AdversarialParameters;
import arms.parameters.ArmParameters;
import arms.parameters.GaussianParameters;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AdversarialArm extends AbstractArm {

    private final AdversarialParameters params;
    private boolean deamonMode;

    AdversarialArm(ArmParameters params)
    {
        super(EnumArm.ADVERSARIAL, params.getSeed());
        this.params = (AdversarialParameters) params;
        this.deamonMode = false;
    }

    @Override
    public double getReward() {
        return -1;
    }

    public double getReward(String agentHistoryString)
    {
        List<String> myList = new ArrayList<>(Arrays.asList(agentHistoryString.split(", ")));

        if(myList.size() <= 50)
            return 1.0 - (1/(double)myList.size());


        ArrayList<Integer> history = new ArrayList<>();

        for (int i = 0; i < myList.size(); i++) {
            history.add(Integer.parseInt(myList.get(i)));
        }

        double frequency = Collections.frequency(history, getArmId()) / (double)history.size();

        if(frequency >= 0.9 || frequency <= 0.1)
        {
            return 1.0 - frequency;
        } else {

           return frequency;
        }

    }

    @Override
    public double expectedValue() {
        return 1;
    }
}
