package arms.parameters;

import arms.EnumArm;

public class AdversarialParameters extends AbstractArmParameters {

    public AdversarialParameters(int seed)
    {
        super(seed, EnumArm.ADVERSARIAL);
    }

}
