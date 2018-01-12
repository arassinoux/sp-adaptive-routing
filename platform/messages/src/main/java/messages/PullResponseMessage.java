package messages;

import java.io.Serializable;

public class PullResponseMessage implements IMessage, Serializable {

    private int agentId;
    private int armId;
    private String command;
    private double reward;

    public PullResponseMessage(int agentId, int armId, double reward)
    {
        this.command = "pull_response";
        this.agentId = agentId;
        this.armId = armId;
        this.reward = reward;
    }

    @Override
    public String getMessage() {
        return command + " " + armId + " " + agentId + " " + reward;
    }

    public int getArmId() {
        return armId;
    }

    public int getAgentId() {
        return agentId;
    }

    public double getReward() {
        return reward;
    }
}
