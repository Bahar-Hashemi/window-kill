package bahar.window_kill.client.control;

import bahar.window_kill.client.control.states.offlline.WhooshState;
import bahar.window_kill.client.control.states.online.OnlinePlayingState;
import bahar.window_kill.client.control.util.FileUtil;
import bahar.window_kill.communications.util.GameUtil;
import bahar.window_kill.communications.model.Game;
import bahar.window_kill.client.view.MainStage;
import bahar.window_kill.communications.model.User;
import bahar.window_kill.communications.model.boards.MainBoard;
import bahar.window_kill.communications.model.entities.generators.shooters.Epsilon;
import bahar.window_kill.communications.data.Settings;

public class GameController {
    public static User user = new User(new Epsilon(true, GameUtil.generateID(), 8), new MainBoard(true, GameUtil.generateID()));
    public static Game game;
    public void newGame() {
        Settings settings = FileUtil.readSettings();
        user = new User(new Epsilon(true, GameUtil.generateID(), settings.getSpeed()), new MainBoard(true, GameUtil.generateID()));
        game = new Game(true);
        game.addUser(user);
       user.mainBoard.setDimensions(Constants.SCREEN_WIDTH / 4, Constants.SCREEN_HEIGHT / 4,
                 Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT / 2);
        user.mainBoard.add(user.epsilon.getView());
        user.epsilon.setLocation(Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT / 2);
        MainStage.newScene(); MainStage.add(user.mainBoard.getView());
        //
        user.mainBoard.setControlStrategy(user.settings.getControlStrategy());
        user.mainBoard.requestUserControl(user);
        game.setGameState(new WhooshState(true, game));
    }
    public void newOnlineGame() {
        game = new Game(true);
        game.setGameState(new OnlinePlayingState(true, game));
        //todo we are done with you :))
    }
}
