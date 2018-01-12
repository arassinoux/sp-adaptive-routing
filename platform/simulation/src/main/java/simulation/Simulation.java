package simulation;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

import messages.*;
import simulation.utils.CSVWriter;

public class Simulation implements Runnable {

    private ConcurrentHashMap<Integer, ConnectionClient> arms;
    private ConcurrentHashMap<Integer, ConnectionClient> agents;

    private HashMap<Integer, ArrayList<Integer>> agentsHistory;
    private HashMap<Integer, ArrayList<Double>> rewardsHistory;
    private HashMap<Integer, ArrayList<Double>> regret;
    private ArrayList<Double> expectedRewardArms;
    private double maxExpectedReward;

    private CSVWriter regretWriter = new CSVWriter("data/regret/regret");
    private CSVWriter rewardWriter = new CSVWriter("data/reward/reward");

    private HashMap<Integer, Integer> trafficArmsRequests;
    private ArrayList<Integer> adversarialArms;

    private int armsCount;
    private int agentsCount;

    private int plays;

    private int maxSteps;

    private final int requiredAgents;
    private final int requiredArms;

    private boolean waitingForClients;
    private ConcurrentLinkedQueue<PullRequestMessage> pullRequests;
    private ConcurrentLinkedQueue<PullResponseMessage> armResponses;

    public Simulation(int plays, int requiredAgents, int requiredArms, int maxSteps)
    {
        this.arms = new ConcurrentHashMap<>();
        this.agents = new ConcurrentHashMap<>();

        this.trafficArmsRequests = new HashMap<>();
        this.adversarialArms = new ArrayList<>();
        this.expectedRewardArms = new ArrayList<>();

        this.plays = plays;

        this.agentsHistory = new HashMap<>();
        this.rewardsHistory = new HashMap<>();
        this.regret = new HashMap<>();

        this.armsCount = 0;
        this.agentsCount = 0;
        this.waitingForClients = true;

        this.maxSteps = maxSteps;

        this.requiredAgents = requiredAgents;
        this.requiredArms = requiredArms;

        this.pullRequests = new ConcurrentLinkedQueue<>();
        this.armResponses = new ConcurrentLinkedQueue<>();
    }

    public void run()
    {
        if(waitingForClients)
            waitingRegister();

        for(int p = 0; p < plays; p++)
        {

            cleanBeforeSimulation();

            for(int step = 0; step < maxSteps; step++) {

                System.out.println("[play " + p + "] Step " + step);

                cleanTrafficRequests();

                waitingPullRequests();

                if(!trafficArmsRequests.isEmpty())
                {
                    countRequestsForTrafficArms();
                }

                while (!pullRequests.isEmpty()) {

                    PullRequestMessage request = pullRequests.poll();


                    if(!trafficArmsRequests.isEmpty())
                        request.setRequestsCount(trafficArmsRequests.get(request.getArmId()));


                    if(adversarialArms.contains(request.getArmId()))
                    {
                        String agentHistoryString = agentsHistory.get(request.getAgentId()).stream().map(Object::toString)
                                .collect(Collectors.joining(", "));
                        request.setAgentHistory(agentHistoryString);
                    }


                    ConnectionClient arm = arms.get(request.getArmId());

                    try {
                        arm.getWriter().writeObject(request);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("pull request " + request.getAgentId() + " sended to arm " + request.getArmId());

                    //Add into history
                    agentsHistory.get(request.getAgentId()).add(request.getArmId());


                }

                waitingArmResponses();

                while(!armResponses.isEmpty())
                {
                    PullResponseMessage response = armResponses.poll();

                    ConnectionClient agent = agents.get(response.getAgentId());
                    try {
                        agent.getWriter().writeObject(response);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("pull response sended to agent " + response.getAgentId());

                    //Add into history
                    rewardsHistory.get(response.getAgentId()).add(response.getReward());
                    computeExpectedRegret(response.getAgentId(), response.getReward());

                }
            }

            //compute expected regret for each agent
            writeIntoCSV();

        }

        stop();
    }


    void computeExpectedRegret(int agentId, double reward)
    {
        double regretValue = maxExpectedReward - reward;
        regret.get(agentId).add(regretValue);

    }


    void writeIntoCSV()
    {
        for(Integer agentId : regret.keySet())
        {
            regretWriter.writeArray(agentId, regret.get(agentId));
            rewardWriter.writeArray(agentId, rewardsHistory.get(agentId));
        }
    }

    void cleanTrafficRequests()
    {
        for(Integer armId: arms.keySet()){
            trafficArmsRequests.put(armId, 0);
        }

    }

    void cleanBeforeSimulation()
    {
        for(Integer agentId: agents.keySet()){
            agentsHistory.put(agentId, new ArrayList<>());
            rewardsHistory.put(agentId, new ArrayList<>());
            regret.put(agentId, new ArrayList<>());
        }
    }

    private void stop()
    {
        List<ConnectionClient> clients = Server.getAllClients();

        for(ConnectionClient client : clients)
        {
            try {
                client.getWriter().writeObject(new StopMessage());
                client.getReader().close();
                client.getWriter().close();
                client.getSocket().close();
                regretWriter.dispose();
                rewardWriter.dispose();
                client.stopped = true;
                Server.stop();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    void countRequestsForTrafficArms() {
        for(PullRequestMessage msg : pullRequests)
        {
            if(trafficArmsRequests.containsKey(msg.getArmId()))
            {
                int lastCount = trafficArmsRequests.get(msg.getArmId());
                trafficArmsRequests.put(msg.getArmId(), lastCount + 1);

            }

        }
    }

    private void waitingRegister()
    {
        while(waitingForClients)
        {
            System.out.println("Waiting for register...");

            if(requiredArms == armsCount && requiredAgents == agentsCount)
            {
                System.out.println("We gonna start the simulation...");

                List<ConnectionClient> clients = Server.getAllClients();

                GoMessage goMessage = new GoMessage();
                for(ConnectionClient client : clients)
                {
                    try {
                        client.getWriter().writeObject(goMessage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                waitingForClients = false;
                maxExpectedReward = Collections.max(expectedRewardArms);

            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void waitingPullRequests()
    {
        while(true)
        {
           if(agentsCount == pullRequests.size())
               break;
        }
    }

    private void waitingArmResponses()
    {
        while(true)
        {
            if(agentsCount == armResponses.size())
                break;
        }
    }

    public ConcurrentLinkedQueue<PullRequestMessage> getPullRequests() {
        return pullRequests;
    }

    public ConcurrentLinkedQueue<PullResponseMessage> getArmResponses() {
        return armResponses;
    }

    public int registerArm(ConnectionClient arm, RegisterRequestMessage request)
    {
        int currentArmId = armsCount;

        arms.put(currentArmId, arm);

        if(request.isTrafficArm()) {
            trafficArmsRequests.put(currentArmId, 0);
        }
        else if (request.isAdversarialArm())
        {
            adversarialArms.add(currentArmId);
        }

            expectedRewardArms.add(currentArmId, request.getExpectedMean());

        armsCount++;

        return currentArmId;
    }

    public int getMaxSteps() {
        return maxSteps;
    }

    public int registerAgent(ConnectionClient agent)
    {
        int currentAgentId = agentsCount;

        agents.put(agentsCount, agent);
        agentsHistory.put(currentAgentId, new ArrayList<>());
        rewardsHistory.put(currentAgentId, new ArrayList<>());
        regret.put(currentAgentId, new ArrayList<>());
        agentsCount++;

        return currentAgentId;
    }

    public void unregisterArm(ConnectionClient arm)
    {
        arms.remove(arm);
        armsCount--;
    }

    public void unregisterAgent(ConnectionClient agent)
    {
        arms.remove(agent);
        agentsCount--;
    }

    public int getRequiredArms() {
        return requiredArms;
    }

    public int getPlays() {
        return plays;
    }
}
