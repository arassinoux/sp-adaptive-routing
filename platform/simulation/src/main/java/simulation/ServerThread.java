package simulation;

import java.io.IOException;
import messages.*;

public class ServerThread extends Thread {

    private ConnectionClient client;
    private Simulation simulation;

    ServerThread(ConnectionClient client, Simulation simulation) throws IOException {
        this.client = client;
        this.simulation = simulation;
    }

    public void run() {

            register();

            while(!client.stopped)
            {
                if(client.getType() == TypeConnection.AGENT)
                    processAgent();
                else if(client.getType() == TypeConnection.ARM)
                    processArm();

            }
    }

    private void processAgent()
    {
        PullRequestMessage request;
        try {
            //WAIT FOR PULL REQUEST
            request = (PullRequestMessage)client.getReader().readObject();
            simulation.getPullRequests().add(request);

        } catch (IOException e) {
            close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void close()
    {
        try {
            client.stopped = true;

            if(client.getType() == TypeConnection.ARM)
                simulation.unregisterArm(client);
            else simulation.unregisterAgent(client);

            client.getWriter().close();
            client.getReader().close();
            client.getSocket().close();

            if(!Server.finish)
                Server.finish = true;

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private void processArm()
    {
        PullResponseMessage response;
        try {
            response = (PullResponseMessage) client.getReader().readObject();
            simulation.getArmResponses().add(response);

        } catch (IOException e) {
            close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void register()
    {
        try {
            Object message = client.getReader().readObject();

            if(message instanceof RegisterRequestMessage) {

                RegisterRequestMessage request = (RegisterRequestMessage) message;

                client.setType(request.getType());

                int processId = -1;
                RegisterResponseMessage response;

                if (request.getType() == TypeConnection.ARM) {
                    processId = simulation.registerArm(client, request);

                } else if(request.getType() == TypeConnection.AGENT) {
                    processId = simulation.registerAgent(client);
                }

                response = new RegisterResponseMessage(processId, simulation.getMaxSteps(), simulation.getPlays(), simulation.getRequiredArms());
                client.getWriter().writeObject(response);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
