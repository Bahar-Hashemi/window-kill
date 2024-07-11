package bahar.window_kill.control.fazes;

import bahar.window_kill.control.Constants;
import bahar.window_kill.control.GameController;
import bahar.window_kill.control.loader.SoundLoader;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import static bahar.window_kill.control.Deck.mainBoard;

public class WhooshState extends GameState {
    public WhooshState() {
        super(makeTimeLine());
    }
    private static Timeline makeTimeLine() {
        Timeline whooshTimeLine = new Timeline(new KeyFrame(new Duration(Constants.RESPOND_DURATION), actionEvent -> {
            mainBoard.setSize(mainBoard.getWidth() * 0.95, mainBoard.getHeight() * 0.95);
            mainBoard.IndependentMoveX((Constants.SCREEN_WIDTH - mainBoard.getWidth()) / 2);
            mainBoard.IndependentMoveY((Constants.SCREEN_HEIGHT - mainBoard.getHeight()) / 2);
        }));
        whooshTimeLine.setCycleCount(15);
        whooshTimeLine.setOnFinished(actionEvent -> {PlayingState.newGame(); GameController.setGameState(PlayingState.getInstance());});
        return whooshTimeLine;
    }

    @Override
    public void play() {
        super.play();
        SoundLoader.WHOOSH.play();
    }

}
