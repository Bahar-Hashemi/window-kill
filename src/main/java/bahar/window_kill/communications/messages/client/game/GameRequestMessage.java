package bahar.window_kill.communications.messages.client.game;

import bahar.window_kill.communications.messages.client.ClientMessage;
import bahar.window_kill.communications.messages.client.ClientMessageType;

public class GameRequestMessage extends ClientMessage {
    String username, additionalData;
    boolean isSinglePlayer;
    public GameRequestMessage(boolean isSinglePlayer, String username, String additionalData) {
        super(ClientMessageType.GAME_REQUEST);
        this.isSinglePlayer = isSinglePlayer;
        this.username = username;
        this.additionalData = additionalData;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAdditionalData() {
        return additionalData;
    }

    public void setAdditionalData(String additionalData) {
        this.additionalData = additionalData;
    }

    public boolean isSinglePlayer() {
        return isSinglePlayer;
    }

    public void setSinglePlayer(boolean singlePlayer) {
        isSinglePlayer = singlePlayer;
    }
}
