package bahar.window_kill.communications.data;

import bahar.window_kill.client.model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class UserMessage {
    UserMessageType type;
    String messageData;
    public UserMessage(UserMessageType type, String messageData) {
        this.type = type;
        this.messageData = messageData;
    }
    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
    public static UserMessage fromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, UserMessage.class);
    }
    public static ArrayList<UserMessage> fromJsonArray(String json) {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<UserMessage>>(){}.getType();
        return gson.fromJson(json, type);
    }
    public String toString() {
        if (type == UserMessageType.MEMBERSHIP_ACCEPTED)
            return "You've been accepted to '" + messageData + "' squad!";
        if (type == UserMessageType.MEMBERSHIP_REQUEST)
            return "User '" + messageData + "' wants to join your squad!";
        if (type == UserMessageType.MONOMACHIA_REQUEST)
            return "User '" + messageData + "' has a 'Monomachia' request!";
        if (type == UserMessageType.COLLOSEUM_REQUEST)
            return "User '" + messageData + "' has a 'Colloseum' request!";
        return "Unknown message type!";
    }

}
