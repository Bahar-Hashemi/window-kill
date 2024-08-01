package bahar.window_kill.server.control.handlers;

import bahar.window_kill.communications.data.TableSquad;
import bahar.window_kill.communications.data.TableUser;
import bahar.window_kill.communications.data.UserMessage;
import bahar.window_kill.communications.data.UserMessageType;
import bahar.window_kill.communications.messages.client.ClientMessage;
import bahar.window_kill.communications.messages.client.data.SendMessageMessage;
import bahar.window_kill.server.control.DataBaseManager;

import java.io.DataOutputStream;
import java.util.ArrayList;

public class SendMessageHandler extends MessageHandler {
    @Override
    public boolean handle(DataOutputStream sendBuffer, ClientMessage clientMessage) {
        if (!(clientMessage instanceof SendMessageMessage sendMessageMessage))
            return false;
        if (sendMessageMessage.getUserMessage().getType() == UserMessageType.MEMBERSHIP_REQUEST)
            handleMembershipRequest(sendBuffer, sendMessageMessage);
        return true;
    }
    private void handleMembershipRequest(DataOutputStream sendBuffer, SendMessageMessage sendMessageMessage) {
        UserMessage userMessage = sendMessageMessage.getUserMessage();
        TableSquad tableSquad = DataBaseManager.getInstance().getSquad(userMessage.getMessageData());
        TableUser tableUser = DataBaseManager.getInstance().getUser(tableSquad.getOwner());
        if (tableUser.getMessages() == null)
            tableUser.setMessages(new ArrayList<>());
        tableUser.getMessages().add(userMessage);
        DataBaseManager.getInstance().updateUser(tableUser);
    }
}
