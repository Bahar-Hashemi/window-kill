package bahar.window_kill.client.control;

import bahar.window_kill.client.control.states.offlline.WhooshState;
import bahar.window_kill.client.control.states.online.OnlinePlayingState;
import bahar.window_kill.client.control.util.FileUtil;
import bahar.window_kill.client.model.Game;
import bahar.window_kill.client.view.MainStage;
import bahar.window_kill.client.model.User;
import bahar.window_kill.client.model.boards.MainBoard;
import bahar.window_kill.client.model.entities.generators.shooters.Epsilon;
import bahar.window_kill.communications.data.Settings;

public class GameController {
    public void newGame() {
        Settings settings = FileUtil.readSettings();
        User.newInstance(new Epsilon(settings.getSpeed()), new MainBoard(100, 0, 0));
        Game.newInstance();
        Game.getInstance().addUser(User.getInstance());
        User.getInstance().mainBoard.setDimensions(Constants.SCREEN_WIDTH / 4, Constants.SCREEN_HEIGHT / 4,
                 Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT / 2);
        User.getInstance().mainBoard.getChildren().add(User.getInstance().epsilon.getView());
        User.getInstance().epsilon.setSceneLocation(Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT / 2);
        MainStage.newScene(); MainStage.add(User.getInstance().mainBoard);
        //
        User.getInstance().mainBoard.setControlStrategy(User.getInstance().settings.getControlStrategy());
        User.getInstance().mainBoard.requestUserControl(User.getInstance()); //todo correct here
        Game.getInstance().setGameState(new WhooshState(Game.getInstance()));
    }
    public void newOnlineGame() {
        Game.newInstance();
        Game.getInstance().setGameState(new OnlinePlayingState(Game.getInstance()));
        //todo we are done with you :))
    }
}
