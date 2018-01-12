package arms.threading;

import arms.Arm;
import arms.ArmFactory;
import arms.parameters.ArmParameters;
import arms.parameters.ArmParametersFactory;

import java.io.IOException;
import java.net.Socket;

public class ClientArm {

    private final String server;
    private final int port;
    private Arm arm;

    public static void main(String[] args) {

        ArmParametersFactory factory = new ArmParametersFactory(args);
        ArmParameters armParameters = factory.getParameters();

        ClientArm client = new ClientArm("127.0.0.1", 999, armParameters);
        client.startAgent();
    }

    private ClientArm(String server, int port, ArmParameters armParameters)
    {
        this.server = server;
        this.port = port;

        this.arm = ArmFactory.createArm(armParameters.getType(), armParameters);

    }

    private void startAgent() {
        try (Socket socket = new Socket(server, port)) {

            new ArmThread(socket, arm);

        } catch (IOException ex) {
            System.out.println("Error");
        }
    }
}
