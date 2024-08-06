package bahar.window_kill.server.control.connection.handlers.squads;

import bahar.window_kill.communications.data.TableUser;
import bahar.window_kill.communications.messages.client.ClientMessage;
import bahar.window_kill.communications.messages.client.squads.NewSquadMessage;
import bahar.window_kill.communications.messages.server.GeneralAnswer;
import bahar.window_kill.server.control.connection.DataBaseManager;
import bahar.window_kill.server.control.connection.handlers.MessageHandler;

import java.io.DataOutputStream;

public class NewSquadHandler extends MessageHandler {
    @Override
    public boolean handle(DataOutputStream sendBuffer, ClientMessage clientMessage) {
        if (!(clientMessage instanceof NewSquadMessage newSquadMessage))
            return false;
        if (newSquadMessage.getSquadName().isEmpty()) {
            sendMessage(sendBuffer, new GeneralAnswer(false, "Squad name cannot be empty"));
            return true;
        }
        if (DataBaseManager.getInstance().hasSquad(newSquadMessage.getSquadName())) {
            sendMessage(sendBuffer, new GeneralAnswer(false, "Squad name already exists"));
            return true;
        }
        TableUser tableUser = DataBaseManager.getInstance().getUser(newSquadMessage.getUsername());
        if (!(tableUser.getSquad() == null)) {
            sendMessage(sendBuffer, new GeneralAnswer(false, "You are already in a squad"));
            return true;
        }
        tableUser.setSquad(newSquadMessage.getSquadName());
        DataBaseManager.getInstance().updateUser(tableUser);
        DataBaseManager.getInstance().newSquad(newSquadMessage.getSquadName(), newSquadMessage.getUsername());
        sendMessage(sendBuffer, new GeneralAnswer(true, "Squad created successfully"));
        return true;
    }
}
