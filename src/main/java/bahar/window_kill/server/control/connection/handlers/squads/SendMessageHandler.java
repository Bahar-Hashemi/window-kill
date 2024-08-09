package bahar.window_kill.server.control.connection.handlers.squads;

import bahar.window_kill.communications.data.TableSquad;
import bahar.window_kill.communications.data.TableUser;
import bahar.window_kill.communications.data.UserMessage;
import bahar.window_kill.communications.data.UserMessageType;
import bahar.window_kill.communications.messages.client.ClientMessage;
import bahar.window_kill.communications.messages.client.data.SendMessageMessage;
import bahar.window_kill.communications.model.Game;
import bahar.window_kill.server.control.connection.DataBaseManager;
import bahar.window_kill.server.control.connection.handlers.MessageHandler;
import bahar.window_kill.server.control.game.GameController;
import bahar.window_kill.server.control.game.GamePool;
import javafx.scene.control.Tab;

import java.io.DataOutputStream;
import java.util.ArrayList;

public class SendMessageHandler extends MessageHandler {
    @Override
    public boolean handle(DataOutputStream sendBuffer, ClientMessage clientMessage) {
        if (!(clientMessage instanceof SendMessageMessage sendMessageMessage))
            return false;
        if (sendMessageMessage.getUserMessage().getType() == UserMessageType.MEMBERSHIP_REQUEST)
            handleMembershipRequest(sendBuffer, sendMessageMessage);
        if (sendMessageMessage.getUserMessage().getType() == UserMessageType.MEMBERSHIP_ACCEPTED)
            handleMembershipAccepted(sendBuffer, sendMessageMessage);
        if (sendMessageMessage.getUserMessage().getType() == UserMessageType.LEFT_SQUAD)
            handleLeftSquad(sendBuffer, sendMessageMessage);
        if (sendMessageMessage.getUserMessage().getType() == UserMessageType.COLOSSEUM_REQUEST)
            handleBattleRequest(sendBuffer, sendMessageMessage);
        if (sendMessageMessage.getUserMessage().getType() == UserMessageType.MONOMACHIA_REQUEST)
            handleBattleRequest(sendBuffer, sendMessageMessage);
        if (sendMessageMessage.getUserMessage().getType() == UserMessageType.ACCEPT_BATTLE)
            handleAcceptBattle(sendBuffer, sendMessageMessage);
        if (sendMessageMessage.getUserMessage().getType() == UserMessageType.ACCEPT_SUMMON)
            handleAcceptSummon(sendBuffer, sendMessageMessage);
        return true;
    }
    private void handleAcceptBattle(DataOutputStream sendBuffer, SendMessageMessage sendMessageMessage) {
        GameController.newMultiplayerGame(sendMessageMessage.getUserMessage().getSenderName(), sendMessageMessage.getUserMessage().getMessageData());
    }
    private void handleAcceptSummon(DataOutputStream sendBuffer, SendMessageMessage sendMessageMessage) {
        TableUser tableUser = DataBaseManager.getInstance().getUser(sendMessageMessage.getUserMessage().getMessageData());
        Game game = GamePool.getNotNullGame(tableUser.getUsername());
        GameController.addUserToGame(sendMessageMessage.getUserMessage().getSenderName(), game);
    }
    private void handleBattleRequest(DataOutputStream sendBuffer, SendMessageMessage sendMessageMessage) {
        UserMessage userMessage = sendMessageMessage.getUserMessage();
        TableUser tableUser = DataBaseManager.getInstance().getUser(userMessage.getMessageData());
        if (tableUser.getMessages() == null)
            tableUser.setMessages(new ArrayList<>());
        tableUser.getMessages().add(userMessage);
        DataBaseManager.getInstance().updateUser(tableUser);
    }
    private void handleMembershipAccepted(DataOutputStream sendBuffer, SendMessageMessage sendMessageMessage) {
        UserMessage userMessage = sendMessageMessage.getUserMessage();
        TableUser tableUser = DataBaseManager.getInstance().getUser(userMessage.getMessageData());
        if (tableUser.getSquad() != null)
            return;
        TableUser owner = DataBaseManager.getInstance().getUser(userMessage.getSenderName());
        tableUser.setSquad(owner.getSquad());
        DataBaseManager.getInstance().updateUser(tableUser);
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
    private void handleLeftSquad(DataOutputStream sendBuffer, SendMessageMessage sendMessageMessage) {
        UserMessage userMessage = sendMessageMessage.getUserMessage();
        TableUser tableUser = DataBaseManager.getInstance().getUser(userMessage.getSenderName());
        tableUser.setSquad(null);
        DataBaseManager.getInstance().updateUser(tableUser);
        ArrayList<TableUser> users = DataBaseManager.getInstance().getSquadMembers(userMessage.getMessageData());
        if (users == null || users.isEmpty())
            DataBaseManager.getInstance().removeSquad(userMessage.getMessageData());
    }
}
