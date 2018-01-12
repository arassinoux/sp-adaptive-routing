package simulation;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class Server {

    private static int clientCount = 0;
    private Simulation simulation;
    private static ArrayList<ConnectionClient> clients;
    public static boolean finish = false;
    private static ServerSocket serverSocket;

    public static void main(String[] args) {

        Server server = new Server();
        server.startServer(999);
    }

    public void startServer(int port) {

        simulation = new Simulation(10, 5, 5, 500);
        Thread simulationThread = new Thread(simulation);
        simulationThread.start();
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("simulation.Server started and ready for connection on port: " + port);

        clients = new ArrayList<>();

        while(!Server.finish) {

            try {

                Socket socket = serverSocket.accept();

                ConnectionClient client = new ConnectionClient(socket);
                clients.add(client);

                ServerThread gameServerThread = new ServerThread(client, simulation);
                gameServerThread.start();
                clientCount++;
                System.out.println("Client " + clientCount + " OK!");

            } catch (IOException ex) {
            }
        }
    }

    public static void stop()
    {
        finish = true;
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<ConnectionClient> getAllClients() {
        return clients;
    }

}
