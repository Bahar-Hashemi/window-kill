package bahar.window_kill.server.control.connection.handlers.data;

import bahar.window_kill.communications.messages.client.ClientMessage;
import bahar.window_kill.communications.messages.client.data.SendControlsMessage;
import bahar.window_kill.communications.model.Game;
import bahar.window_kill.communications.model.User;
import bahar.window_kill.communications.processors.GameProcessor;
import bahar.window_kill.communications.processors.util.abilities.AbilityType;
import bahar.window_kill.server.control.connection.handlers.MessageHandler;
import bahar.window_kill.server.control.game.GamePool;

import java.io.DataOutputStream;

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
}
