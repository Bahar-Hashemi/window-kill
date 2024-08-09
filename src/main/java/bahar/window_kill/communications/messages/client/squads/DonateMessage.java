package bahar.window_kill.communications.messages.client.squads;

import bahar.window_kill.communications.messages.client.ClientMessage;
import bahar.window_kill.communications.messages.client.ClientMessageType;

public class DonateMessage extends ClientMessage {
    private String username;
    private int amount;
    public DonateMessage(String username, int amount) {
        super(ClientMessageType.DONATE);
        this.username = username;
        this.amount = amount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
