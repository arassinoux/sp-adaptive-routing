package messages;

import java.io.Serializable;

public class StopMessage implements IMessage, Serializable {

    private String command;

    public StopMessage()
    {
        this.command = "stop";
    }

    @Override
    public String getMessage() {
        return command;
    }
}
