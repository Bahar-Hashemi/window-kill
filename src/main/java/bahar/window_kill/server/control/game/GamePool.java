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
            for (Game game: onlineGames)
                for (User user: game.users)
                    if (user.getUsername().equals(name))
                        return game;
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
