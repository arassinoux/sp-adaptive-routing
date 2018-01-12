package messages;

import java.io.Serializable;

public class RegisterRequestMessage implements IMessage, Serializable {

    private String command;
    private TypeConnection type;
    private boolean isTrafficArm;
    private boolean isAdversarialArm;
    private double expectedMean;

    public RegisterRequestMessage(TypeConnection type)
    {
        this.command = "register";
        this.type = type;
    }

    public RegisterRequestMessage(TypeConnection type, double expectedMean, boolean isTrafficArm, boolean isAdversarialArm)
    {
        this.command = "register";
        this.type = type;
        this.expectedMean = expectedMean;
        this.isTrafficArm = isTrafficArm;
        this.isAdversarialArm = isAdversarialArm;
    }

    @Override
    public String getMessage() {
        return command;
    }

    public TypeConnection getType() {
        return type;
    }

    public boolean isTrafficArm() {
        return isTrafficArm;
    }

    public boolean isAdversarialArm() {
        return isAdversarialArm;
    }

    public double getExpectedMean() {
        return expectedMean;
    }
}
