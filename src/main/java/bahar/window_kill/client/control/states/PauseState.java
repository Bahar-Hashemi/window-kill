package bahar.window_kill.client.control.states;

import bahar.window_kill.client.control.Constants;
import bahar.window_kill.client.model.Game;
import bahar.window_kill.client.view.MainStage;
import bahar.window_kill.client.view.PaneBuilder;
import bahar.window_kill.client.model.User;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class PauseState extends GameState {
    Pane pausePane;
    public PauseState(Game game) {
        super(game, makeTimeLine(game));
        pausePane = PaneBuilder.PAUSE_PANE.generatePane();
    }
    private static Timeline makeTimeLine(Game game) {
        Timeline pauseTimeLine = new Timeline(new KeyFrame(new Duration(Constants.RESPOND_DURATION), actionEvent -> {
            User user = game.users.get(0);
            if (!user.hasPauseRequest())
                game.setGameState(new RestartingState(game));
            user.mainBoard.requestFocus();
        }));
        pauseTimeLine.setCycleCount(-1);
        return pauseTimeLine;
    }
    @Override
    public void play() {
        super.play();
        MainStage.add(pausePane);
        pausePane.setLayoutX(Constants.SCREEN_WIDTH / 6);
        pausePane.setLayoutY(Constants.MINIMUM_HEIGHT / 6);
    }

    @Override
    public void stop() {
        super.stop();
        MainStage.remove(pausePane);
    }
}
