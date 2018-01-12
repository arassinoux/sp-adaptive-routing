package messages;

import java.io.Serializable;

public class GoMessage implements IMessage, Serializable {

    private String command;

    public GoMessage()
    {
        this.command = "go";
    }

    @Override
    public String getMessage() {
        return command;
    }
}
