package bahar.window_kill.client.control.states.offlline;

import bahar.window_kill.client.control.Constants;
import bahar.window_kill.client.control.states.GameState;
import bahar.window_kill.client.control.states.offlline.PlayingState;
import bahar.window_kill.client.model.Game;
import bahar.window_kill.client.control.util.SoundUtil;
import bahar.window_kill.client.model.User;
import bahar.window_kill.client.model.boards.MainBoard;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class WhooshState extends GameState {
    public WhooshState(Game game) {
        super(game, makeTimeLine(game));
    }
    private static Timeline makeTimeLine(Game game) {
        Timeline whooshTimeLine = new Timeline(new KeyFrame(new Duration(Constants.RESPOND_DURATION), actionEvent -> {
            for (User user: game.users) {
                MainBoard mainBoard = user.mainBoard;
                mainBoard.setSize(mainBoard.getWidth() * 0.95, mainBoard.getHeight() * 0.95);
                mainBoard.IndependentMoveX((Constants.SCREEN_WIDTH - mainBoard.getWidth()) / 2);
                mainBoard.IndependentMoveY((Constants.SCREEN_HEIGHT - mainBoard.getHeight()) / 2);
            }
        }));
        whooshTimeLine.setCycleCount(15);
        whooshTimeLine.setOnFinished(actionEvent -> {
            game.setGameState(new PlayingState(game));
        });
        return whooshTimeLine;
    }

    @Override
    public void play() {
        super.play();
        SoundUtil.WHOOSH.play();
    }

}
