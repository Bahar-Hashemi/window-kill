package bahar.window_kill.communications.messages.client.data;

import bahar.window_kill.communications.messages.client.ClientMessage;
import bahar.window_kill.communications.messages.client.ClientMessageType;
import bahar.window_kill.communications.model.User;

public class SendControlsMessage extends ClientMessage {
    String username;
    User user;
    public SendControlsMessage(String username, User user) {
        super(ClientMessageType.SEND_CONTROLS);
        this.username = username;
        this.user = user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
