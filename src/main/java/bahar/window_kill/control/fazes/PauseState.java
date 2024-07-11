package bahar.window_kill.control.fazes;

import bahar.window_kill.control.Constants;
import bahar.window_kill.control.Deck;
import bahar.window_kill.control.GameController;
import bahar.window_kill.view.MainStage;
import bahar.window_kill.view.PaneBuilder;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import static bahar.window_kill.control.Deck.mainBoard;
import static bahar.window_kill.control.Deck.user;

public class PauseState extends GameState {
    final static Pane pausePane = PaneBuilder.PAUSE_PANE.generatePane();
    protected PauseState() {
        super(makeTimeLine());
    }
    private static Timeline makeTimeLine() {
        Timeline pauseTimeLine = new Timeline(new KeyFrame(new Duration(Constants.RESPOND_DURATION), actionEvent -> {
            if (!user.hasPauseRequest())
                GameController.setGameState(new RestartingState());
            mainBoard.requestFocus();
        }));
        pauseTimeLine.setCycleCount(-1);
        return pauseTimeLine;
    }
    @Override
    public void play() {
        super.play();
        MainStage.add(pausePane);
        Deck.mainBoard.requestFocus();
    }

    @Override
    public void stop() {
        super.stop();
        MainStage.remove(pausePane);
    }
}
