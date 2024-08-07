package bahar.window_kill.client.control.states.offlline;

import bahar.window_kill.client.control.Constants;
import bahar.window_kill.client.control.states.GameState;
import bahar.window_kill.communications.model.Game;
import bahar.window_kill.client.view.MainStage;
import bahar.window_kill.client.view.PaneBuilder;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class SavingState extends GameState {
    Pane savePane;
    public SavingState(boolean isViewable, Game game) {
        super(isViewable, game, makeTimeLine(game));
        savePane = PaneBuilder.SAVE_PANE.generatePane();
    }
    private static Timeline makeTimeLine(Game game) {
        Timeline saveTimeLine = new Timeline(new KeyFrame(new Duration(Constants.RESPOND_DURATION), actionEvent -> {
            game.users.get(0).mainBoard.getView().requestFocus();
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
    }

    @Override
    public void stop() {
        super.stop();
        MainStage.remove(savePane);
    }
}