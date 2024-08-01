package bahar.window_kill.communications.messages.client.data;

import bahar.window_kill.communications.data.TableSquad;
import bahar.window_kill.communications.messages.client.ClientMessage;
import bahar.window_kill.communications.messages.client.ClientMessageType;

public class SendSquadMessage extends ClientMessage {
    private String username;
    private TableSquad squad;
    public SendSquadMessage(String username, TableSquad squad) {
        super(ClientMessageType.SEND_SQUAD);
        this.username = username;
        this.squad = squad;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public TableSquad getSquad() {
        return squad;
    }

    public void setSquad(TableSquad squad) {
        this.squad = squad;
    }
}
