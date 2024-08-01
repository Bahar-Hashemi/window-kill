package bahar.window_kill.communications.messages.client.data;

import bahar.window_kill.communications.data.Development;
import bahar.window_kill.communications.messages.client.ClientMessage;
import bahar.window_kill.communications.messages.client.ClientMessageType;

public class SendDevelopmentMessage extends ClientMessage {
    private String username;
    private Development development;

    public SendDevelopmentMessage(String username, Development development) {
        super(ClientMessageType.SEND_DEVELOPMENT);
        this.username = username;
        this.development = development;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Development getDevelopment() {
        return development;
    }

    public void setDevelopment(Development development) {
        this.development = development;
    }
}
