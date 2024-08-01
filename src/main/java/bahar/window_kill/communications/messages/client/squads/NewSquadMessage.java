package bahar.window_kill.communications.messages.client.squads;

import bahar.window_kill.communications.messages.client.ClientMessage;
import bahar.window_kill.communications.messages.client.ClientMessageType;

public class NewSquadMessage extends ClientMessage {
    private String username, squadName;
    public NewSquadMessage(String username, String squadName) {
        super(ClientMessageType.NEW_SQUAD);
        this.username = username;
        this.squadName = squadName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSquadName() {
        return squadName;
    }

    public void setSquadName(String squadName) {
        this.squadName = squadName;
    }
}
