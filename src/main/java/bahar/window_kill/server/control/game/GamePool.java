package bahar.window_kill.server.control.game;

import bahar.window_kill.communications.model.Game;
import bahar.window_kill.communications.model.User;

import java.util.ArrayList;

public class GamePool {
    private static final ArrayList<Game> onlineGames = new ArrayList<>();
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
}
