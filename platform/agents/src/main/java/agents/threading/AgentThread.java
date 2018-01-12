package agents.threading;

import agents.Agent;
import messages.*;

import java.io.*;
import java.net.Socket;

class AgentThread {

    private ObjectOutputStream writer;
    private ObjectInputStream reader;
    private Socket socket;
    private TypeConnection type = TypeConnection.AGENT;
    private Agent agent;
    private boolean running;

    AgentThread(Socket socket, Agent agent) throws IOException {
        this.socket = socket;
        this.agent = agent;
        this.writer = new ObjectOutputStream(socket.getOutputStream());
        this.reader = new ObjectInputStream(socket.getInputStream());
        this.running = true;
        register();
    }

    private void register()
    {
        try {

            System.out.println("Connected to simulation !");

            writer.writeObject(new RegisterRequestMessage(type));

            RegisterResponseMessage registerResponse = (RegisterResponseMessage)reader.readObject();
            agent.setId(registerResponse.getProcessId());

            agent.getBanditAlgorithm().setArmsCount(registerResponse.getRequiredArms());

            System.out.println("agents.Agent correctly registered to the simulation process with id : " + agent.getId());

            agent.setMaxSteps(registerResponse.getMaxSteps());
            agent.setPlays(registerResponse.getPlays());

            waiting();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void waiting() {

        try {
            reader.readObject();
            System.out.println("Let's go guy !");
            process();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void process() {

        for(int p = 0; p < agent.getPlays(); p++)
        {

            for(int steps = 0; steps < agent.getMaxSteps(); steps++)
            {
                try {

                    int armId = agent.selectArm();
                    PullRequestMessage request = new PullRequestMessage(agent.getId(), armId);
                    System.out.println("Send: " + request.getMessage());
                    writer.writeObject(request);

                    PullResponseMessage response = (PullResponseMessage)reader.readObject();
                    System.out.println("Get reward" + response.getReward());
                    agent.process(armId, response.getReward());

                    Thread.sleep(100);

                } catch (IOException | InterruptedException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

            agent.reset();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }

        agent.finish();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        waitForClose();

    }

    private void waitForClose()
    {
        while(running)
        {
            try {
                Object message = reader.readObject();

                if(message instanceof StopMessage)
                {
                    close();
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

    private void close(){
        try {
            running = false;
            writer.close();
            reader.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}


