package messages;

import java.io.Serializable;

public class RegisterResponseMessage implements IMessage, Serializable {

    private String command;
    private int processId;
    private int maxSteps;
    private int plays;
    private int requiredArms = -1;

    public RegisterResponseMessage(int processId, int maxSteps, int plays, int requiredArms)
    {
        this.command = "register";
        this.processId = processId;
        this.maxSteps = maxSteps;
        this.requiredArms = requiredArms;
        this.plays = plays;
    }

    @Override
    public String getMessage() {
        if(requiredArms > 0)
            return command + " " + processId + " " + requiredArms;
        else
            return command + " " + processId;
    }

    public int getProcessId() {
        return processId;
    }

    public int getMaxSteps() {
        return maxSteps;
    }

    public int getRequiredArms() {
        return requiredArms;
    }

    public int getPlays() {
        return plays;
    }
}
