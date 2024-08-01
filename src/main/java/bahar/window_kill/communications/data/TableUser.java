package bahar.window_kill.communications.data;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class TableUser {
    private String username, password, state, squad;
    private ArrayList<UserMessage> messages;
    private Development development;
    public TableUser(String username, String password, String state, String squad, ArrayList<UserMessage> messages, Development development) {
        this.username = username;
        this.password = password;
        this.state = state;
        this.squad = squad;
        this.messages = messages;
        this.development = development;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSquad() {
        return squad;
    }

    public void setSquad(String squad) {
        this.squad = squad;
    }

    public ArrayList<UserMessage> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<UserMessage> messages) {
        this.messages = messages;
    }

    public Development getDevelopment() {
        return development;
    }

    public void setDevelopment(Development development) {
        this.development = development;
    }
    public static ArrayList<TableUser> fromJsonArray(String json) {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<TableUser>>(){}.getType();
        return gson.fromJson(json, type);
    }
}
