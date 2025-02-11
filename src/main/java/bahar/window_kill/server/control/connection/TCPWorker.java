package bahar.window_kill.server.control.connection;

import bahar.window_kill.communications.messages.client.*;
import bahar.window_kill.communications.messages.client.data.*;
import bahar.window_kill.communications.messages.client.game.GameRequestMessage;
import bahar.window_kill.communications.messages.client.register.LoginMessage;
import bahar.window_kill.communications.messages.client.register.SignupMessage;
import bahar.window_kill.communications.messages.client.squads.DonateMessage;
import bahar.window_kill.communications.messages.client.squads.NewSquadMessage;
import bahar.window_kill.server.control.connection.handlers.MessageHandler;
import bahar.window_kill.server.control.connection.handlers.data.ChangeStateHandler;
import bahar.window_kill.server.control.connection.handlers.data.DataRequestHandler;
import bahar.window_kill.server.control.connection.handlers.data.SendControlsHandler;
import bahar.window_kill.server.control.connection.handlers.data.SendDevelopmentHandler;
import bahar.window_kill.server.control.connection.handlers.game.GameRequestHandler;
import bahar.window_kill.server.control.connection.handlers.register.LoginHandler;
import bahar.window_kill.server.control.connection.handlers.register.SignupHandler;
import bahar.window_kill.server.control.connection.handlers.squads.DonateHandler;
import bahar.window_kill.server.control.connection.handlers.squads.NewSquadHandler;
import bahar.window_kill.server.control.connection.handlers.squads.SendMessageHandler;
import bahar.window_kill.server.control.connection.handlers.squads.SendSquadHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

import static bahar.window_kill.server.control.connection.TCPWorkSpace.connections;

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
        result.add(new DataRequestHandler());
        result.add(new NewSquadHandler());
        result.add(new SendDevelopmentHandler());
        result.add(new SendSquadHandler());
        result.add(new SendMessageHandler());
        result.add(new GameRequestHandler());
        result.add(new SendControlsHandler());
        result.add(new DonateHandler());
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
                case REQUEST_DATA -> gsonAgent.fromJson(clientStr, RequestDataMessage.class);
                case NEW_SQUAD -> gsonAgent.fromJson(clientStr, NewSquadMessage.class);
                case SEND_DEVELOPMENT -> gsonAgent.fromJson(clientStr, SendDevelopmentMessage.class);
                case SEND_SQUAD -> gsonAgent.fromJson(clientStr, SendSquadMessage.class);
                case SEND_MESSAGE -> gsonAgent.fromJson(clientStr, SendMessageMessage.class);
                case GAME_REQUEST -> gsonAgent.fromJson(clientStr, GameRequestMessage.class);
                case SEND_CONTROLS -> gsonAgent.fromJson(clientStr, SendControlsMessage.class);
                case DONATE -> gsonAgent.fromJson(clientStr, DonateMessage.class);
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