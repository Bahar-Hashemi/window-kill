package bahar.window_kill.server.control.handlers;

import bahar.window_kill.communications.data.TableUser;
import bahar.window_kill.communications.messages.client.ClientMessage;
import bahar.window_kill.communications.messages.client.RequestDataMessage;
import bahar.window_kill.communications.messages.client.RequestedDataType;
import bahar.window_kill.server.control.DataBaseManager;

import java.io.DataOutputStream;

public class DataRequestHandler extends MessageHandler {
    @Override
    public boolean handle(DataOutputStream sendBuffer, ClientMessage clientMessage) {
        if (!(clientMessage instanceof RequestDataMessage requestDataMessage))
            return false;
        if (requestDataMessage.getRequestedDataType().equals(RequestedDataType.ME))
            returnMe(sendBuffer, requestDataMessage);
        return true;
    }
    private void returnMe(DataOutputStream sendBuffer, RequestDataMessage requestDataMessage) {
        TableUser tableUser = DataBaseManager.getInstance().getUser(requestDataMessage.getUsername());
        sendObject(sendBuffer, tableUser);
    }
}
