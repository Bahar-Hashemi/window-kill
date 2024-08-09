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
        //make busy
        TableUser tableUser = DataBaseManager.getInstance().getUser(username);
        if (tableUser.getState().equals("busy") || tableUser.getState().equals("offline"))
            return;
        tableUser.setState("busy"); DataBaseManager.getInstance().updateUser(tableUser);
        // make Game!!!
        Game game = new Game(false);
        User user = new User(new Epsilon(false, GameUtil.generateID(), 8), new MainBoard(false, GameUtil.generateID()));
        user.mainBoard.setLocation(384, 237.5);
        user.mainBoard.setSize(768, 475);
        user.epsilon.setLocation(768, 475);
        user.setUsername(tableUser.getUsername());
        user.setDevelopment(tableUser.getDevelopment());
        game.addUser(user);
        addProcessors(game);
        GamePool.addGame(game);
    }
    private static void addProcessors(Game game) {
        game.gameProcessors.add(new RequestProcessor(false, game));
        game.gameProcessors.add(new AbilityProcessor(false, game));
        game.gameProcessors.add(new SpawnProcessor(false, game));
        game.gameProcessors.add(new BoardsProcessor(false, game));
        game.gameProcessors.add(new AggressionProcessor(false, game));
        game.gameProcessors.add(new MovementProcessor(false, game)); //important: must be after Aggression processor
        game.gameProcessors.add(new DeathProcessor(false, game));
    }

    public static void newMultiplayerGame(String firstPlayer, String secondPlayer) {
        //check busy
        TableUser firstTableUser = DataBaseManager.getInstance().getUser(firstPlayer);
        TableUser secondTableUser = DataBaseManager.getInstance().getUser(secondPlayer);
        if (firstTableUser.getState().equals("busy") || firstTableUser.getState().equals("offline") ||
                secondTableUser.getState().equals("busy") || secondTableUser.getState().equals("offline"))
            return;
        firstTableUser.setState("busy"); DataBaseManager.getInstance().updateUser(firstTableUser);
        secondTableUser.setState("busy"); DataBaseManager.getInstance().updateUser(secondTableUser);
        // make Game!!!
        Game game = new Game(false);
        User firstUser = new User(new Epsilon(false, GameUtil.generateID(), 8), new MainBoard(false, GameUtil.generateID()));
        firstUser.setUsername(firstPlayer); firstUser.setDevelopment(firstTableUser.getDevelopment());
        firstUser.mainBoard.setLocation(1536.0 / 8, 950.0 / 5);
        firstUser.mainBoard.setSize(1536.0 * 3 / 8, 950.0 * 3 / 5);
        firstUser.epsilon.setLocation(1536.0 * 5 / 16, 950.0 * 5 / 10);
        User secondUser = new User(new Epsilon(false, GameUtil.generateID(), 8), new MainBoard(false, GameUtil.generateID()));
        secondUser.setUsername(secondPlayer); secondUser.setDevelopment(secondTableUser.getDevelopment());
        secondUser.mainBoard.setLocation(1536.0 * 5 / 8, 950.0 / 5);
        secondUser.mainBoard.setSize(1536.0 * 3 / 8, 950.0 * 3 / 5);
        secondUser.epsilon.setLocation(1536.0 * 11 / 16, 950.0 * 5 / 10);
        game.addUser(firstUser); game.addUser(secondUser);
        addProcessors(game);
        GamePool.addGame(game);
    }
    public static void addUserToGame(String username, Game game) {
        //check busy
        TableUser tableUser = DataBaseManager.getInstance().getUser(username);
        if (tableUser.getState().equals("busy") || tableUser.getState().equals("offline"))
            return;
        tableUser.setState("busy"); DataBaseManager.getInstance().updateUser(tableUser);
        // make Game!!!
        User user = new User(new Epsilon(false, GameUtil.generateID(), 8), new MainBoard(false, GameUtil.generateID()));
        user.mainBoard.setLocation(0, 0);
        user.mainBoard.setSize(500, 300);
        user.epsilon.setLocation(250, 150);
        user.setUsername(tableUser.getUsername());
        user.setDevelopment(tableUser.getDevelopment());
        game.addUser(user);
    }
}
