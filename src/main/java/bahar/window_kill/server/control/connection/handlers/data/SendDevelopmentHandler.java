package bahar.window_kill.server.control.connection.handlers.data;

import bahar.window_kill.communications.data.TableUser;
import bahar.window_kill.communications.messages.client.ClientMessage;
import bahar.window_kill.communications.messages.client.data.SendDevelopmentMessage;
import bahar.window_kill.server.control.connection.DataBaseManager;
import bahar.window_kill.server.control.connection.handlers.MessageHandler;

import java.io.DataOutputStream;

public class SendDevelopmentHandler extends MessageHandler {
    @Override
    public boolean handle(DataOutputStream sendBuffer, ClientMessage clientMessage) {
        if (!(clientMessage instanceof SendDevelopmentMessage sendDevelopmentMessage))
            return false;
        TableUser tableUser = DataBaseManager.getInstance().getUser(sendDevelopmentMessage.getUsername());
        tableUser.setDevelopment(sendDevelopmentMessage.getDevelopment());
        DataBaseManager.getInstance().updateUser(tableUser);
        return true;
    }
}
