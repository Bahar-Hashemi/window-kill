package bahar.window_kill.communications.data;

import bahar.window_kill.communications.data.Development;

public class TableUser {
    private String username, password, state, squad;
    private String messages;
    private Development development;
    public TableUser(String username, String password, String state, String squad, String messages, Development development) {
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

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public Development getDevelopment() {
        return development;
    }

    public void setDevelopment(Development development) {
        this.development = development;
    }
}
