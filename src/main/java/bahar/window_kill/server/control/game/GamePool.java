package bahar.window_kill.server.control.game;

import bahar.window_kill.communications.data.TableSquad;
import bahar.window_kill.communications.data.TableUser;
import bahar.window_kill.communications.model.Game;
import bahar.window_kill.communications.model.User;
import bahar.window_kill.server.control.connection.DataBaseManager;

import java.util.ArrayList;

public class GamePool {
    static final ArrayList<Game> onlineGames = new ArrayList<>();
    public static void addGame(Game game) {
        synchronized (onlineGames) {
            onlineGames.add(game);
            onlineGames.notifyAll();
        }
    }
    public static Game getMyGame(String name) {
        synchronized (onlineGames) {
            for (int i = onlineGames.size() - 1; i >= 0; i--)
                for (int j = onlineGames.get(i).users.size() - 1; j >= 0; j--)
                    if (onlineGames.get(i).users.get(j).getUsername().equals(name))
                        return onlineGames.get(i);
            return null;
        }
    }
    public static Game getNotNullGame(String name) {
        try {
            synchronized (onlineGames) {
                Game game = getMyGame(name);
                while (game == null) {
                    onlineGames.wait();
                    game = getMyGame(name);
                }
                return game;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void removeGame(Game game) {
        synchronized (onlineGames) {
            updatePalioxis(game);
            updateUsersState(game);
            updateSquadHistory(game);
            onlineGames.remove(game);
        }
    }
    private static void updatePalioxis(Game game) {
        for (User user: game.users) {
            TableUser tableUser = DataBaseManager.getInstance().getUser(user.getUsername());
            TableSquad tableSquad = DataBaseManager.getInstance().getSquad(tableUser.getSquad());
            if (tableSquad.getPalioxisState() == 1)
                tableSquad.setVault(tableSquad.getVault() + user.getXp());
            DataBaseManager.getInstance().updateSquad(tableSquad);
        }
    }
    private static void updateSquadHistory(Game game) {
        if (game.users.size() <= 1)
            return;
        for (User user: game.users) {
            TableUser tableUser = DataBaseManager.getInstance().getUser(user.getUsername());
            TableSquad tableSquad = DataBaseManager.getInstance().getSquad(tableUser.getSquad());
            tableSquad.setHistory(tableSquad.getHistory() +
                    user.getUsername() + " played and gained" + user.getXp() + " xps\n");
            DataBaseManager.getInstance().updateUser(tableUser);
        }
    }
    private static void updateUsersState(Game game) {
        for (User user: game.users) {
            TableUser tableUser = DataBaseManager.getInstance().getUser(user.getUsername());
            tableUser.getDevelopment().setXp(tableUser.getDevelopment().getXp() + user.getXp());
            if (tableUser.getState().equals("busy"))
                tableUser.setState("online");
            DataBaseManager.getInstance().updateUser(tableUser);
        }
    }
}
