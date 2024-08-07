package bahar.window_kill.server.control.connection.handlers.game;

import bahar.window_kill.communications.messages.client.ClientMessage;
import bahar.window_kill.communications.messages.client.game.GameRequestMessage;
import bahar.window_kill.server.control.connection.handlers.MessageHandler;
import bahar.window_kill.server.control.game.GameController;

import java.io.DataOutputStream;

public class GameRequestHandler extends MessageHandler {
    @Override
    public boolean handle(DataOutputStream sendBuffer, ClientMessage clientMessage) {
        if (!(clientMessage instanceof GameRequestMessage gameRequestMessage))
            return false;
        if (gameRequestMessage.isSinglePlayer())
            handleSinglePlayer(gameRequestMessage);
        return true;
    }
    private void handleSinglePlayer(GameRequestMessage gameRequestMessage) {
        GameController.newSinglePlayerGame(gameRequestMessage.getUsername());
    }
}
