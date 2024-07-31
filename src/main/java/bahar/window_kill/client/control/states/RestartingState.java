package bahar.window_kill.client.control.states;

import bahar.window_kill.client.model.Deck;
import bahar.window_kill.client.control.util.SoundUtil;
import bahar.window_kill.client.model.User;
import bahar.window_kill.client.model.boards.MainBoard;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class RestartingState extends GameState {
    static Color[] colors = new Color[] {Color.GREEN, Color.GREENYELLOW, Color.ORANGE, Color.RED};
    public RestartingState(Deck deck) {
        super(deck, makeTimeLine(deck));
    }
    private static Timeline makeTimeLine(Deck deck) {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            for (User user: deck.users)
                handleMainBoard(user.mainBoard);
            SoundUtil.DAMAGE.play();
        }));
        timeline.setCycleCount(3);
        timeline.setOnFinished(e -> {
            deck.setGameState(new PlayingState(deck));
        });
        return timeline;
    }
    private static void handleMainBoard(MainBoard mainBoard) {
        int ticks = Integer.parseInt(mainBoard.countDown.getText()) + 1;
        mainBoard.countDown.setTextFill(colors[ticks]);
        mainBoard.countDown.setText("" + ticks);
    }
    private static Label makeLabel(MainBoard mainBoard) {
        Label label = new Label("" + 0);
        label.setTextFill(Color.RED);
        label.setAlignment(Pos.CENTER);
        label.setFont(new Font("cooper black", 68));
        label.layoutXProperty().bind(mainBoard.widthProperty().subtract(label.widthProperty()).divide(2));
        label.layoutYProperty().bind(mainBoard.heightProperty().subtract(label.heightProperty()).divide(2));
        return label;
    }
    @Override
    public void play() {
        super.play();
        for (User user: deck.users)
            user.mainBoard.setCountDown(makeLabel(user.mainBoard));
    }

    @Override
    public void stop() {
        super.stop();
        for (User user: deck.users)
            user.mainBoard.removeCountDown();
    }
}
