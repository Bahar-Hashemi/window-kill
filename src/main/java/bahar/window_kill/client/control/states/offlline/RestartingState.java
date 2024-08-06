package bahar.window_kill.client.control.states.offlline;

import bahar.window_kill.client.control.states.GameState;
import bahar.window_kill.client.control.states.offlline.PlayingState;
import bahar.window_kill.client.model.Game;
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
    public RestartingState(Game game) {
        super(game, makeTimeLine(game));
    }
    private static Timeline makeTimeLine(Game game) {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            for (User user: game.users)
                handleMainBoard(user.mainBoard);
            SoundUtil.DAMAGE.play();
        }));
        timeline.setCycleCount(3);
        timeline.setOnFinished(e -> {
            game.setGameState(new PlayingState(game));
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
        for (User user: game.users)
            user.mainBoard.setCountDown(makeLabel(user.mainBoard));
    }

    @Override
    public void stop() {
        super.stop();
        for (User user: game.users)
            user.mainBoard.removeCountDown();
    }
}
