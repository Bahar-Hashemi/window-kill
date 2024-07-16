package bahar.window_kill.control.fazes;

import bahar.window_kill.control.GameController;
import bahar.window_kill.control.util.SoundUtil;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

import static bahar.window_kill.control.Deck.mainBoard;

public class RestartingState extends GameState {
    private static Label countDown = makeLabel();
    private static int ticks;
    static Color[] colors = new Color[] {Color.GREEN, Color.GREENYELLOW, Color.ORANGE};
    public RestartingState() {
        super(makeTimeLine());
    }
    private static Timeline makeTimeLine() {
        ticks = 0;
        countDown = makeLabel();
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            ticks = (ticks + 1) % 3;
            countDown.setTextFill(colors[ticks]);
            countDown.setText("" + ticks);
            SoundUtil.DAMAGE.play();
        }));
        timeline.setCycleCount(3);
        timeline.setOnFinished(e -> {
            GameController.setGameState(PlayingState.getInstance());
        });
        return timeline;
    }
    private static Label makeLabel() {
        ticks = 0;
        Label label = new Label("" + ticks);
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
        mainBoard.getChildren().add(countDown);
    }

    @Override
    public void stop() {
        super.stop();
        mainBoard.getChildren().remove(countDown);
    }
}
