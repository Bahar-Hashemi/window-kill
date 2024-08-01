package bahar.window_kill.communications.messages.client.squads;

import bahar.window_kill.communications.messages.client.ClientMessage;
import bahar.window_kill.communications.messages.client.ClientMessageType;

public class JoinRequestMessage extends ClientMessage {
    private String username, squadName;
    private JoinRequestType joinRequestType;
    public JoinRequestMessage(String username, String squadName, JoinRequestType joinRequestType) {
        super(ClientMessageType.JOIN_REQUEST);
        this.username = username;
        this.squadName = squadName;
        this.joinRequestType = joinRequestType;
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

    public JoinRequestType getJoinRequestType() {
        return joinRequestType;
    }

    public void setJoinRequestType(JoinRequestType joinRequestType) {
        this.joinRequestType = joinRequestType;
    }
}
