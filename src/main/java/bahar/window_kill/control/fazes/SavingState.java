package bahar.window_kill.control.fazes;

import bahar.window_kill.control.Constants;
import bahar.window_kill.control.GameController;
import bahar.window_kill.view.MainStage;
import bahar.window_kill.view.PaneBuilder;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import static bahar.window_kill.control.Deck.mainBoard;
import static bahar.window_kill.control.Deck.user;

public class SavingState extends GameState {
    Pane savePane;
    public SavingState() {
        super(makeTimeLine());
        savePane = PaneBuilder.SAVE_PANE.generatePane();
    }
    private static Timeline makeTimeLine() {
        Timeline saveTimeLine = new Timeline(new KeyFrame(new Duration(Constants.RESPOND_DURATION), actionEvent -> {
            mainBoard.requestFocus();
        }));
        saveTimeLine.setCycleCount(-1);
        return saveTimeLine;
    }
    @Override
    public void play() {
        super.play();
        MainStage.add(savePane);
        savePane.setLayoutX(Constants.SCREEN_WIDTH / 6);
        savePane.setLayoutY(Constants.MINIMUM_HEIGHT / 6);
        mainBoard.requestFocus();
    }

    @Override
    public void stop() {
        super.stop();
        MainStage.remove(savePane);
    }
}