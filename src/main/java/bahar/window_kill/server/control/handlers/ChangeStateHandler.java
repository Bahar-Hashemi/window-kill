package bahar.window_kill.server.control.handlers;

import bahar.window_kill.communications.messages.client.ChangeStateMessage;
import bahar.window_kill.communications.messages.client.ClientMessage;
import bahar.window_kill.server.control.DataBaseManager;
import bahar.window_kill.communications.data.TableUser;

import java.io.DataOutputStream;

public class ChangeStateHandler extends MessageHandler {
    @Override
    public boolean handle(DataOutputStream sendBuffer, ClientMessage clientMessage) {
        if (!(clientMessage instanceof ChangeStateMessage changeStateMessage))
            return false;
        TableUser tableUser = DataBaseManager.getInstance().getUser(changeStateMessage.getUsername());
        tableUser.setState(changeStateMessage.getState());
        DataBaseManager.getInstance().updateUser(tableUser);
        return true;
    }
}
