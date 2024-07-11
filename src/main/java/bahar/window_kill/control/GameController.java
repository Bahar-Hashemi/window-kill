package bahar.window_kill.control;

import bahar.window_kill.control.fazes.GameState;
import bahar.window_kill.control.fazes.WhooshState;
import bahar.window_kill.model.User;
import bahar.window_kill.model.boards.MainBoard;
import bahar.window_kill.model.boards.controller.WASDStrategy;
import bahar.window_kill.model.entities.generators.shooters.Epsilon;
import bahar.window_kill.view.MainStage;
import javafx.animation.Timeline;

import java.util.ArrayList;

import static bahar.window_kill.control.Deck.mainBoard;
import static bahar.window_kill.control.Deck.user;

public class GameController {
    static Timeline gameTimeLine, whooshTimeLine;
    private static GameState gameState;
    static public void run() {
        makeDeck();
        addControls();
        setGameState(new WhooshState());
    }
    private static void addControls() {
        mainBoard.setControlStrategy(new WASDStrategy(mainBoard));
        mainBoard.requestUserControl(user);
    }
    private static void makeDeck() {
        Deck.entities = new ArrayList<>();
        mainBoard = new MainBoard(100, 0, 1);
        mainBoard.setDimensions(Constants.SCREEN_WIDTH / 4, Constants.SCREEN_HEIGHT / 4, Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT / 2);
        user = new User(new Epsilon());
        MainStage.add(mainBoard);
        mainBoard.add(user.getEpsilon().getView());
        user.getEpsilon().setSceneLocation(Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT / 2);
    }
    public static void setGameState(GameState gameState) {
        if (GameController.gameState != null)
            GameController.gameState.stop();
        GameController.gameState = gameState;
        GameController.gameState.play();
    }

}
