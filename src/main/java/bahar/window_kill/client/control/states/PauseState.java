package bahar.window_kill.client.control.states;

import bahar.window_kill.client.control.Constants;
import bahar.window_kill.client.model.Deck;
import bahar.window_kill.client.view.MainStage;
import bahar.window_kill.client.view.PaneBuilder;
import bahar.window_kill.client.model.User;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class PauseState extends GameState {
    Pane pausePane;
    public PauseState(Deck deck) {
        super(deck, makeTimeLine(deck));
        pausePane = PaneBuilder.PAUSE_PANE.generatePane();
    }
    private static Timeline makeTimeLine(Deck deck) {
        Timeline pauseTimeLine = new Timeline(new KeyFrame(new Duration(Constants.RESPOND_DURATION), actionEvent -> {
            User user = deck.users.get(0);
            if (!user.hasPauseRequest())
                deck.setGameState(new RestartingState(deck));
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
