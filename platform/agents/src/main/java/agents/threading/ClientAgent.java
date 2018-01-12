package agents.threading;

import agents.Agent;
import agents.SimpleAgent;
import agents.algorithms.AlgorithmFactory;
import agents.algorithms.BanditAlgorithm;
import agents.algorithms.parameters.AlgorithmParameters;
import agents.algorithms.parameters.AlgorithmParametersFactory;

import java.io.IOException;
import java.net.Socket;

public class ClientAgent {

    private String server;
    private int port;
    private Agent agent;

    public static void main(String[] args) {

        AlgorithmParametersFactory factory = new AlgorithmParametersFactory(args);
        AlgorithmParameters algorithmParameters = factory.getParameters();

        ClientAgent client = new ClientAgent("127.0.0.1", 999, algorithmParameters);

        client.startAgent();
    }

    private ClientAgent(String server, int port, AlgorithmParameters algorithmParameters)
    {
        this.server = server;
        this.port = port;

        BanditAlgorithm algorithm = AlgorithmFactory.createBanditAlgorithm(algorithmParameters.getType(), algorithmParameters);
        this.agent = new SimpleAgent(algorithm);
        agent.setAlgorithmParameters(algorithmParameters);
    }

    private void startAgent() {
        try (Socket socket = new Socket(server, port)) {

            new AgentThread(socket, agent);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
