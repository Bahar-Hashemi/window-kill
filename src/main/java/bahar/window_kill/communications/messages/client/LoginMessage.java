package bahar.window_kill.communications.messages.client;

public class LoginMessage extends ClientMessage {
    private String username;
    private String password;
    public LoginMessage(String username, String password) {
        super(ClientMessageType.LOGIN);
        this.username = username;
        this.password = password;
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
}
