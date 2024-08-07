package bahar.window_kill.client.control.states.offlline;

import bahar.window_kill.client.control.Constants;
import bahar.window_kill.client.control.states.GameState;
import bahar.window_kill.communications.model.Game;
import bahar.window_kill.client.view.MainStage;
import bahar.window_kill.client.view.PaneBuilder;
import bahar.window_kill.communications.model.User;
import com.google.gson.Gson;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class PauseState extends GameState {
    Pane pausePane;
    public PauseState(boolean isViewable, Game game) {
        super(isViewable, game, makeTimeLine(isViewable, game));
        pausePane = PaneBuilder.PAUSE_PANE.generatePane();
    }
    private static Timeline makeTimeLine(boolean isViewable, Game game) {
        Timeline pauseTimeLine = new Timeline(new KeyFrame(new Duration(1000), actionEvent -> {
            User user = game.users.get(0);
            if (!user.hasPauseRequest())
                game.setGameState(new RestartingState(isViewable, game));
            user.mainBoard.getView().requestFocus();
            Gson gson = new Gson();
            System.out.println(gson.toJson(game));
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
