package bahar.window_kill.communications.messages.client;

public class ClientMessage {
    protected final ClientMessageType type;

    public ClientMessage(ClientMessageType type) {
        this.type = type;
    }

    public ClientMessageType getType() {
        return type;
    }
}
