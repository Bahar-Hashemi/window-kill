package bahar.window_kill.server.control.handlers;

import bahar.window_kill.communications.messages.client.ClientMessage;
import bahar.window_kill.communications.messages.server.ServerAnswer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.DataOutputStream;

abstract public class MessageHandler {
    private final Gson gsonAgent;
    public MessageHandler() {
        GsonBuilder builder = new GsonBuilder();
        gsonAgent = builder.create();
    }
    protected boolean sendMessage(DataOutputStream sendBuffer, ServerAnswer serverAnswer) {
        String messageString = gsonAgent.toJson(serverAnswer);
        try {
            sendBuffer.writeUTF(messageString);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    protected boolean sendObject(DataOutputStream sendBuffer, Object object) {
        String messageString = gsonAgent.toJson(object);
        try {
            sendBuffer.writeUTF(messageString);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    abstract public boolean handle(DataOutputStream sendBuffer, ClientMessage clientMessage);
}
