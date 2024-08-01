package bahar.window_kill.server.control.handlers;

import bahar.window_kill.communications.messages.client.ClientMessage;
import bahar.window_kill.communications.messages.client.data.SendSquadMessage;
import bahar.window_kill.server.control.DataBaseManager;

import java.io.DataOutputStream;

public class SendSquadHandler extends MessageHandler {
    @Override
    public boolean handle(DataOutputStream sendBuffer, ClientMessage clientMessage) {
        if (!(clientMessage instanceof SendSquadMessage sendSquadMessage))
            return false;
        DataBaseManager.getInstance().updateSquad(sendSquadMessage.getSquad());
        return true;
    }
}
