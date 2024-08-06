package bahar.window_kill.server.control.game;

import bahar.window_kill.server.control.game.processors.*;
import bahar.window_kill.server.model.User;
import bahar.window_kill.server.model.boards.MainBoard;
import bahar.window_kill.client.model.entities.generators.shooters.Epsilon;
import bahar.window_kill.communications.data.TableUser;
import bahar.window_kill.server.control.connection.DataBaseManager;
import bahar.window_kill.server.model.Game;

public class GameController {
    public static void newSinglePlayerGame(String username) {
        Game game = new Game();
        TableUser tableUser = DataBaseManager.getInstance().getUser(username);
        User user = new User(new Epsilon(8), new MainBoard());
        user.setUsername(tableUser.getUsername());
        user.setDevelopment(tableUser.getDevelopment());
        game.addUser(user);
        Game.addGame(game);
        addProcessors(game);
    }
    private static void addProcessors(Game game) {
//        GamesManager.addProcessor(new RequestProcessor(game));
//        GamesManager.addProcessor(new AbilityProcessor(game));
//        GamesManager.addProcessor(new SpawnProcessor(game));
//        GamesManager.addProcessor(new BoardsProcessor(game));
//        GamesManager.addProcessor(new AggressionProcessor(game));
//        GamesManager.addProcessor(new MovementProcessor(game)); //important: must be after Aggression processor
//        GamesManager.addProcessor(new DeathProcessor(game));
    }
}
