package bahar.window_kill.client.control.states;

import bahar.window_kill.client.control.Constants;
import bahar.window_kill.client.model.Deck;
import bahar.window_kill.client.view.MainStage;
import bahar.window_kill.client.view.PaneBuilder;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class SavingState extends GameState {
    Pane savePane;
    public SavingState(Deck deck) {
        super(deck, makeTimeLine(deck));
        savePane = PaneBuilder.SAVE_PANE.generatePane();
    }
    private static Timeline makeTimeLine(Deck deck) {
        Timeline saveTimeLine = new Timeline(new KeyFrame(new Duration(Constants.RESPOND_DURATION), actionEvent -> {
            deck.users.get(0).mainBoard.requestFocus();
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