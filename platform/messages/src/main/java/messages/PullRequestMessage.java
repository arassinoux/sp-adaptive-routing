package messages;

import java.io.Serializable;
import java.util.ArrayList;

public class PullRequestMessage implements IMessage, Serializable {

    private int agentId;
    private int armId;
    private String command;
    private int requestsCount;
    private String agentHistory;

    public PullRequestMessage(int agentId, int armId)
    {
        this.command = "pull_request";
        this.agentId = agentId;
        this.armId = armId;
        this.requestsCount = 0;
    }

    @Override
    public String getMessage() {
        return command + " " + agentId + " " + armId;
    }

    public int getAgentId() {
        return agentId;
    }

    public int getArmId() {
        return armId;
    }

    public void setRequestsCount(int requestsCount) {
        this.requestsCount = requestsCount;
    }

    public void setAgentHistory(String agentHistory) {
        this.agentHistory = agentHistory;
    }

    public String getAgentHistory() {
        return agentHistory;
    }

    public int getRequestsCount() {
        return requestsCount;
    }
}
