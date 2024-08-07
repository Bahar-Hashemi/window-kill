package bahar.window_kill.server.control.game;

import bahar.window_kill.communications.data.TableUser;
import bahar.window_kill.communications.model.Game;
import bahar.window_kill.communications.model.User;
import bahar.window_kill.communications.model.boards.MainBoard;
import bahar.window_kill.communications.model.entities.generators.shooters.Epsilon;
import bahar.window_kill.communications.processors.*;
import bahar.window_kill.communications.util.Constants;
import bahar.window_kill.communications.util.GameUtil;
import bahar.window_kill.server.control.connection.DataBaseManager;
import javafx.stage.Screen;

public class GameController {
    public static void newSinglePlayerGame(String username) {
        Game game = new Game(false);
        TableUser tableUser = DataBaseManager.getInstance().getUser(username);
        User user = new User(new Epsilon(false, GameUtil.generateID(), 8), new MainBoard(false, GameUtil.generateID()));
        user.mainBoard.setLocation(384, 237.5);
        user.mainBoard.setSize(768, 475);
        user.epsilon.setLocation(768, 475);
        user.setUsername(tableUser.getUsername());
        user.setDevelopment(tableUser.getDevelopment());
        game.addUser(user);
        GamePool.addGame(game);
        addProcessors(game);
    }
    private static void addProcessors(Game game) {
        GamesManager.addProcessor(new RequestProcessor(false, game));
        GamesManager.addProcessor(new AbilityProcessor(false, game));
        GamesManager.addProcessor(new SpawnProcessor(false, game));
        GamesManager.addProcessor(new BoardsProcessor(false, game));
        GamesManager.addProcessor(new AggressionProcessor(false, game));
        GamesManager.addProcessor(new MovementProcessor(false, game)); //important: must be after Aggression processor
        GamesManager.addProcessor(new DeathProcessor(false, game));
    }
}
