package bahar.window_kill.server.control.connection.handlers.data;

import bahar.window_kill.communications.messages.client.data.ChangeStateMessage;
import bahar.window_kill.communications.messages.client.ClientMessage;
import bahar.window_kill.server.control.connection.DataBaseManager;
import bahar.window_kill.communications.data.TableUser;
import bahar.window_kill.server.control.connection.handlers.MessageHandler;

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
