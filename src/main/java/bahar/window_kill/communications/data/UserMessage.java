package bahar.window_kill.communications.data;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class UserMessage {
    UserMessageType type;
    String messageData;
    String senderName;
    public UserMessage(UserMessageType type, String senderName, String messageData) {
        this.type = type;
        this.messageData = messageData;
        this.senderName = senderName;
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
            return "User '" + senderName + "' wants to join your squad!";
        if (type == UserMessageType.MONOMACHIA_REQUEST)
            return "User '" + senderName + "' has a 'Monomachia' request!";
        if (type == UserMessageType.COLOSSEUM_REQUEST)
            return "User '" + senderName + "' has a 'Colloseum' request!";
        return "Unknown message type!";
    }

    public UserMessageType getType() {
        return type;
    }

    public void setType(UserMessageType type) {
        this.type = type;
    }

    public String getMessageData() {
        return messageData;
    }

    public void setMessageData(String messageData) {
        this.messageData = messageData;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }
}
