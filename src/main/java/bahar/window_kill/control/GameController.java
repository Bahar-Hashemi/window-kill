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

import static bahar.window_kill.control.Deck.*;

public class GameController {
    private static GameState gameState;
    static public void run() {
        makeDeck();
        addControls();
        setGameState(new WhooshState());
    }
    private static void addControls() {
        mainBoard.setControlStrategy(settings.getControlStrategy());
        mainBoard.requestUserControl(user);
    }
    private static void makeDeck() {
        Deck.newDeck();
        mainBoard.setDimensions(Constants.SCREEN_WIDTH / 4, Constants.SCREEN_HEIGHT / 4, Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT / 2);
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
