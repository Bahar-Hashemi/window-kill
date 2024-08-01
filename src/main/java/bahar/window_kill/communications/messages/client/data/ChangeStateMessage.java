package bahar.window_kill.communications.messages.client.data;

import bahar.window_kill.communications.messages.client.ClientMessage;
import bahar.window_kill.communications.messages.client.ClientMessageType;

public class ChangeStateMessage extends ClientMessage {
    private final String username, password, state;
    public ChangeStateMessage(String username, String password, String state) {
        super(ClientMessageType.STATE_CHANGE);
        this.username = username;
        this.password = password;
        this.state = state;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getState() {
        return state;
    }
}
