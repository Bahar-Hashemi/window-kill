package bahar.window_kill.server.control;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class TCPWorkSpace extends Thread {
    public static ArrayList<Socket> connections;
    private static int WORKERS;
    private static ServerSocket server;
    private static TCPWorkSpace instance;
    private TCPWorkSpace(int portNumber, int workerNum) {
        try {
            server = new ServerSocket(portNumber);
            connections = new ArrayList<Socket>();
            WORKERS = workerNum;
            for (int i = 0; i < WORKERS; i++)
                new TCPWorker().start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static TCPWorkSpace getInstance() {
        if (instance == null)
            instance = new TCPWorkSpace(8080, 10);
        return instance;
    }
    public void run() {
        try {
            listen();
        } catch (Exception e) {
            System.out.println("Server encountered a problem!");
            e.printStackTrace();
        }
    }
    public void listen() throws IOException {
        Socket socket;
        while (true) {
            socket = server.accept();
            synchronized (connections) {
                connections.add(socket);
                connections.notify();
            }
        }
    }
}
