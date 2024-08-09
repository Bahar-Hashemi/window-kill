package bahar.window_kill.server.control.connection.handlers.data;

import bahar.window_kill.communications.data.TableSquad;
import bahar.window_kill.communications.data.TableUser;
import bahar.window_kill.communications.data.UserMessage;
import bahar.window_kill.communications.data.UserMessageType;
import bahar.window_kill.communications.messages.client.ClientMessage;
import bahar.window_kill.communications.messages.client.data.SendControlsMessage;
import bahar.window_kill.communications.model.Game;
import bahar.window_kill.communications.model.User;
import bahar.window_kill.communications.processors.GameProcessor;
import bahar.window_kill.communications.processors.util.abilities.AbilityType;
import bahar.window_kill.server.control.connection.DataBaseManager;
import bahar.window_kill.server.control.connection.handlers.MessageHandler;
import bahar.window_kill.server.control.game.GamePool;

import java.io.DataOutputStream;
import java.util.ArrayList;

public class SendControlsHandler extends MessageHandler {
    @Override
    public boolean handle(DataOutputStream sendBuffer, ClientMessage clientMessage) {
        if (!(clientMessage instanceof SendControlsMessage sendControlsMessage))
            return false;
        Game game = GamePool.getNotNullGame(sendControlsMessage.getUsername());
        User user = null;
        for (User init: game.users)
            if (init.getUsername().equals(sendControlsMessage.getUsername()))
                user = init;
        User goalUser = sendControlsMessage.getUser();
        if (goalUser.hasSummonRequest()) {
            summonTeamMate(goalUser);
        }
        user.setShooting(goalUser.isShooting());
        user.setDownRequest(goalUser.hasDownRequest());
        user.setUpRequest(goalUser.hasUpRequest());
        user.setLeftRequest(goalUser.hasLeftRequest());
        user.setRightRequest(goalUser.hasRightRequest());
        user.setUpRequest(goalUser.hasUpRequest());
        user.setMouseX(goalUser.getMouseX());
        user.setMouseY(goalUser.getMouseY());
        user.abilityRequests = goalUser.abilityRequests;
        return true;
    }
    private void summonTeamMate(User user) {
        TableUser tableUser = DataBaseManager.getInstance().getUser(user.getUsername());
        TableSquad tableSquad = DataBaseManager.getInstance().getSquad(tableUser.getSquad());
        if (tableSquad.getAdonisState() != 1)
            return;
        ArrayList<TableUser> users = DataBaseManager.getInstance().getSquadMembers(tableSquad.getName());
        for (TableUser newUser: users)
            if (newUser.getState().equals("online")) {
                if (newUser.getMessages() == null)
                    newUser.setMessages(new ArrayList<>());
                newUser.getMessages().add(new UserMessage(UserMessageType.REQUEST_SUMMON, tableUser.getUsername(), ""));
                DataBaseManager.getInstance().updateUser(newUser);
                return;
            }
    }
}
