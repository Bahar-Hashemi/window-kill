package bahar.window_kill.server.control.connection.handlers.data;

import bahar.window_kill.communications.data.TableSquad;
import bahar.window_kill.communications.data.TableUser;
import bahar.window_kill.communications.messages.client.ClientMessage;
import bahar.window_kill.communications.messages.client.data.RequestDataMessage;
import bahar.window_kill.communications.messages.client.data.RequestedDataType;
import bahar.window_kill.communications.model.Game;
import bahar.window_kill.server.control.connection.DataBaseManager;
import bahar.window_kill.server.control.connection.handlers.MessageHandler;
import bahar.window_kill.server.control.game.GamePool;

import java.io.DataOutputStream;
import java.util.ArrayList;

public class DataRequestHandler extends MessageHandler {
    @Override
    public boolean handle(DataOutputStream sendBuffer, ClientMessage clientMessage) {
        if (!(clientMessage instanceof RequestDataMessage requestDataMessage))
            return false;
        if (requestDataMessage.getRequestedDataType() == RequestedDataType.ME)
            returnMe(sendBuffer, requestDataMessage);
        if (requestDataMessage.getRequestedDataType() == RequestedDataType.GLOBAL_SQUADS)
            returnGlobe(sendBuffer, requestDataMessage);
        if (requestDataMessage.getRequestedDataType() == RequestedDataType.MY_SQUAD)
            returnMySquad(sendBuffer, requestDataMessage);
        if (requestDataMessage.getRequestedDataType() == RequestedDataType.MY_SQUAD_MEMBERS)
            returnMyTeamMates(sendBuffer, requestDataMessage);
        if (requestDataMessage.getRequestedDataType() == RequestedDataType.GAME_DATA)
            returnGame(sendBuffer, requestDataMessage);
        if (requestDataMessage.getRequestedDataType() == RequestedDataType.ENEMY_SQUAD_MEMBERS)
            returnEnemySquadMembers(sendBuffer, requestDataMessage);
        return true;
    }
    private void returnEnemySquadMembers(DataOutputStream sendBuffer, RequestDataMessage requestDataMessage) {
        TableUser tableUser = DataBaseManager.getInstance().getUser(requestDataMessage.getUsername());
        TableSquad squad = DataBaseManager.getInstance().getSquad(tableUser.getSquad());
        if (squad == null) {
            sendObject(sendBuffer, new ArrayList<>());
            return;
        }
        TableSquad enemySquad = DataBaseManager.getInstance().getSquad(squad.getEnemy());
        if (enemySquad == null) {
            sendObject(sendBuffer, new ArrayList<>());
            return;
        }
        ArrayList<TableUser> users = DataBaseManager.getInstance().getSquadMembers(enemySquad.getName());
        sendObject(sendBuffer, users);
    }
    private void returnGame(DataOutputStream sendBuffer, RequestDataMessage requestDataMessage) {
        Game game = GamePool.getNotNullGame(requestDataMessage.getUsername());
        synchronized (game) {
            sendObject(sendBuffer, game);
        }
    }
    private void returnMe(DataOutputStream sendBuffer, RequestDataMessage requestDataMessage) {
        TableUser tableUser = DataBaseManager.getInstance().getUser(requestDataMessage.getUsername());
        sendObject(sendBuffer, tableUser);
        tableUser.setMessages(null);
        DataBaseManager.getInstance().updateUser(tableUser);
    }
    private void returnGlobe(DataOutputStream sendBuffer, RequestDataMessage requestDataMessage) {
        ArrayList<String> squads = DataBaseManager.getInstance().getSquadNames();
        for (int i = 0; i < squads.size(); i++) {
            ArrayList<TableUser> users = DataBaseManager.getInstance().getSquadMembers(squads.get(i));
            squads.set(i, squads.get(i) + " (" + users.size() + ")");
        }
        sendObject(sendBuffer, squads);
    }
    private void returnMySquad(DataOutputStream sendBuffer, RequestDataMessage requestDataMessage) {
        TableUser tableUser = DataBaseManager.getInstance().getUser(requestDataMessage.getUsername());
        TableSquad squad = DataBaseManager.getInstance().getSquad(tableUser.getSquad());
        sendObject(sendBuffer, squad);
    }
    private void returnMyTeamMates(DataOutputStream sendBuffer, RequestDataMessage requestDataMessage) {
        TableUser tableUser = DataBaseManager.getInstance().getUser(requestDataMessage.getUsername());
        ArrayList<TableUser> users = DataBaseManager.getInstance().getSquadMembers(tableUser.getSquad());
        sendObject(sendBuffer, users);
    }
}
