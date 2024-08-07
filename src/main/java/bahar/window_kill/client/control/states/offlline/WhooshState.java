package bahar.window_kill.client.control.states.offlline;

import bahar.window_kill.client.control.Constants;
import bahar.window_kill.client.control.states.GameState;
import bahar.window_kill.communications.model.Game;
import bahar.window_kill.client.control.util.SoundUtil;
import bahar.window_kill.communications.model.User;
import bahar.window_kill.communications.model.boards.MainBoard;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class WhooshState extends GameState {
    public WhooshState(boolean isViewable, Game game) {
        super(isViewable, game, makeTimeLine(isViewable, game));
    }
    private static Timeline makeTimeLine(boolean isViewable, Game game) {
        Timeline whooshTimeLine = new Timeline(new KeyFrame(new Duration(Constants.RESPOND_DURATION), actionEvent -> {
            for (User user: game.users) {
                MainBoard mainBoard = user.mainBoard;
                user.getEpsilon().setX(Constants.SCREEN_WIDTH / 2);
                user.getEpsilon().setY(Constants.SCREEN_HEIGHT / 2);
                mainBoard.setSize(mainBoard.getWidth() * 0.95, mainBoard.getHeight() * 0.95);
                mainBoard.setLayoutX((Constants.SCREEN_WIDTH - mainBoard.getWidth()) / 2);
                mainBoard.setLayoutY((Constants.SCREEN_HEIGHT - mainBoard.getHeight()) / 2);
            }
        }));
        whooshTimeLine.setCycleCount(15);
        whooshTimeLine.setOnFinished(actionEvent -> {
            game.setGameState(new PlayingState(isViewable, game));
        });
        return whooshTimeLine;
    }

    @Override
    public void play() {
        super.play();
        SoundUtil.WHOOSH.play();
    }

}
