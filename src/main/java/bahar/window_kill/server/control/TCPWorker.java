package bahar.window_kill.server.control;

import bahar.window_kill.communications.messages.client.ChangeStateMessage;
import bahar.window_kill.communications.messages.client.ClientMessage;
import bahar.window_kill.communications.messages.client.LoginMessage;
import bahar.window_kill.communications.messages.client.SignupMessage;
import bahar.window_kill.server.control.handlers.ChangeStateHandler;
import bahar.window_kill.server.control.handlers.LoginHandler;
import bahar.window_kill.server.control.handlers.MessageHandler;
import bahar.window_kill.server.control.handlers.SignupHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

import static bahar.window_kill.server.control.TCPWorkSpace.connections;

public class TCPWorker extends Thread {
    private static Gson gsonAgent;

    private DataOutputStream sendBuffer;
    private DataInputStream recieveBuffer;
    private final ArrayList<MessageHandler> handlers;


    public TCPWorker() {
        GsonBuilder builder = new GsonBuilder();
        gsonAgent = builder.create();
        handlers = getHandlers();
    }
    private static ArrayList<MessageHandler> getHandlers() {
        ArrayList<MessageHandler> result = new ArrayList<>();
        result.add(new LoginHandler());
        result.add(new SignupHandler());
        result.add(new ChangeStateHandler());
        return result;
    }

    @Override
    public void run() {
        Socket socket;
        while (true) {
            socket = null;
            synchronized (connections) {
                while (connections.isEmpty()) {
                    try {
                        connections.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                socket = connections.get(0);
                connections.remove(0);
            }
            if (socket != null) {
                handleConnection(socket);
            }
        }
    }

    private ClientMessage extractClientMessage(String clientStr) {
        try {
            ClientMessage clientMessage = gsonAgent.fromJson(clientStr, ClientMessage.class);
            return switch (clientMessage.getType()) {
                case LOGIN -> gsonAgent.fromJson(clientStr, LoginMessage.class);
                case SIGNUP -> gsonAgent.fromJson(clientStr, SignupMessage.class);
                case STATE_CHANGE -> gsonAgent.fromJson(clientStr, ChangeStateMessage.class);
                default -> null;
            };
        }
        catch (Exception e) {
            return null;
        }
    }

    private void handleConnection(Socket socket) {
        String clientRequest;

        try {
            recieveBuffer = new DataInputStream(
                    new BufferedInputStream(socket.getInputStream())
            );
            sendBuffer = new DataOutputStream(
                    new BufferedOutputStream(socket.getOutputStream())
            );
            clientRequest = recieveBuffer.readUTF();
            ClientMessage msg = extractClientMessage(clientRequest);
            for (MessageHandler handler : handlers)
                if (handler.handle(sendBuffer, msg))
                    break;
            sendBuffer.close();
            recieveBuffer.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}