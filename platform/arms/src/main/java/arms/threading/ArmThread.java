package arms.threading;

import arms.AdversarialArm;
import arms.Arm;
import arms.EnumArm;
import arms.TrafficArm;


import java.io.*;
import java.net.Socket;

import messages.*;


class ArmThread {

    private final ObjectOutputStream writer;
    private final ObjectInputStream reader;
    private final Socket socket;
    private TypeConnection type = TypeConnection.ARM;
    private Arm arm;
    private boolean running;

    ArmThread(Socket socket, Arm arm) throws IOException {
        this.socket = socket;
        this.arm = arm;
        this.writer = new ObjectOutputStream(socket.getOutputStream());
        this.reader = new ObjectInputStream(socket.getInputStream());
        this.running = true;
        register();
    }

    private void register()
    {
        try {

            System.out.println("Connected to simulation !");

            boolean isTrafficArm = false;
            boolean isAdversarialArm = false;

            if(arm.getArmType() == EnumArm.TRAFFIC)
                isTrafficArm = true;
            else if (arm.getArmType() == EnumArm.ADVERSARIAL)
                isAdversarialArm = true;

            writer.writeObject(new RegisterRequestMessage(type, arm.expectedValue(), isTrafficArm, isAdversarialArm));

            RegisterResponseMessage registerResponse = (RegisterResponseMessage)reader.readObject();
            this.arm.setArmId(registerResponse.getProcessId());

            System.out.println("arms.Arm correctly registered to the simulation process with id : " + arm.getArmId());
            waiting();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void waiting() {

        try {

            System.out.println("Waiting for the start of the simulation...");
            reader.readObject();
            System.out.println("Let's go guy !");

            process();
            close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void process()
    {
        while (running) {
            try {
                Object message = reader.readObject();

                if (message instanceof PullRequestMessage) {

                    if(arm.getArmType() == EnumArm.TRAFFIC) {
                        TrafficArm trafficArm = (TrafficArm) arm;//casting to class
                        //if(trafficArm.getRequestsLeft() == 0)
                        trafficArm.setRequests(((PullRequestMessage) message).getRequestsCount());
                    }

                    double reward;
                    if(arm.getArmType() == EnumArm.ADVERSARIAL)
                    {
                        AdversarialArm adversarialArm = (AdversarialArm) arm;
                        reward = adversarialArm.getReward(((PullRequestMessage) message).getAgentHistory());
                    }
                    else
                        reward = arm.getReward();

                    PullResponseMessage response = new PullResponseMessage(((PullRequestMessage) message).getAgentId(), arm.getArmId(), reward);
                    writer.writeObject(response);
                    System.out.println("arms.Arm pulled the following reward " + reward);

                } else if (message instanceof StopMessage) {
                    close();
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void close(){
        try {
            writer.close();
            reader.close();
            socket.close();
            running = false;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
