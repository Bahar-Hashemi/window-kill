package bahar.window_kill.server.control.game;

import bahar.window_kill.communications.data.TableUser;
import bahar.window_kill.communications.model.Game;
import bahar.window_kill.communications.model.User;
import bahar.window_kill.communications.processors.GameProcessor;
import bahar.window_kill.server.control.connection.DataBaseManager;

import java.util.ArrayList;

public class GamesManager extends Thread {
    public GamesManager() {
        super();
    }
    @Override
    public void run() {
        try {
            while (true) {
                super.run();
                    for (int i = GamePool.onlineGames.size() - 1; i >= 0; i--)
                        checkGame(GamePool.onlineGames.get(i));
                for (Game game: GamePool.onlineGames) {
                    for (GameProcessor gameProcessor: game.gameProcessors)
                        gameProcessor.run();
                }
                this.sleep(30);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private void checkGame(Game game) {
        if (game.users.isEmpty()) {
            GamePool.removeGame(game);
            return;
        }
        for (User user: game.users)
            if (user.epsilon.getHP() <= -10) {
                GamePool.removeGame(game);
                return;
            }
        for (User user: game.users) {
            TableUser tableUser = DataBaseManager.getInstance().getUser(user.getUsername());
            if (tableUser.getState().equals("offline")) {
                user.epsilon.setHP(-1E9 - 7);
                return;
            }
        }
    }
}
