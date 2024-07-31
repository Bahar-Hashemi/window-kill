package bahar.window_kill.client.control;

import bahar.window_kill.client.control.states.WhooshState;
import bahar.window_kill.client.control.util.FileUtil;
import bahar.window_kill.client.model.Deck;
import bahar.window_kill.client.view.MainStage;
import bahar.window_kill.client.model.User;
import bahar.window_kill.client.model.boards.MainBoard;
import bahar.window_kill.client.model.entities.generators.shooters.Epsilon;
import bahar.window_kill.communications.data.Settings;

public class GameController {
    public void newGame() {
        Settings settings = FileUtil.readSettings();
        User.newInstance(new Epsilon(settings.getSpeed()), new MainBoard(100, 0, 0));
        Deck.newInstance();
        Deck.getInstance().addUser(User.getInstance());
        User.getInstance().mainBoard.setDimensions(Constants.SCREEN_WIDTH / 4, Constants.SCREEN_HEIGHT / 4,
                 Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT / 2);
        User.getInstance().mainBoard.getChildren().add(User.getInstance().epsilon.getView());
        User.getInstance().epsilon.setSceneLocation(Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT / 2);
        MainStage.newScene(); MainStage.add(User.getInstance().mainBoard);
        //
        User.getInstance().mainBoard.setControlStrategy(User.getInstance().settings.getControlStrategy());
        User.getInstance().mainBoard.requestUserControl(User.getInstance()); //todo correct here
        Deck.getInstance().setGameState(new WhooshState(Deck.getInstance()));
    }
}
