package bahar.window_kill.server.control.connection.handlers.squads;

import bahar.window_kill.communications.data.TableSquad;
import bahar.window_kill.communications.data.TableUser;
import bahar.window_kill.communications.messages.client.ClientMessage;
import bahar.window_kill.communications.messages.client.squads.DonateMessage;
import bahar.window_kill.server.control.connection.DataBaseManager;
import bahar.window_kill.server.control.connection.handlers.MessageHandler;

import java.io.DataOutputStream;

public class DonateHandler extends MessageHandler {
    @Override
    public boolean handle(DataOutputStream sendBuffer, ClientMessage clientMessage) {
        if (!(clientMessage instanceof DonateMessage donateMessage))
            return false;
        TableUser tableUser = DataBaseManager.getInstance().getUser(donateMessage.getUsername());
        tableUser.getDevelopment().setXp(tableUser.getDevelopment().getXp() - donateMessage.getAmount());
        DataBaseManager.getInstance().updateUser(tableUser);
        TableSquad tableSquad = DataBaseManager.getInstance().getSquad(tableUser.getSquad());
        tableSquad.setVault(tableSquad.getVault() + donateMessage.getAmount());
        DataBaseManager.getInstance().updateSquad(tableSquad);
        return true;
    }
}
