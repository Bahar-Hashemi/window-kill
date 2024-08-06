package bahar.window_kill.client.control.states.online;

import bahar.window_kill.client.control.Constants;
import bahar.window_kill.client.control.connection.TCPClient;
import bahar.window_kill.client.control.states.GameState;
import bahar.window_kill.client.model.Game;
import bahar.window_kill.client.model.User;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.Scanner;

public class OnlinePlayingState extends GameState {
    public OnlinePlayingState(Game game) {
        super(game, makeTimeLine(game));
    }
    private static Timeline makeTimeLine(Game game) {
        Timeline gameTimeLine = new Timeline(new KeyFrame(new Duration(Constants.RESPOND_DURATION), actionEvent -> {
            String result = new TCPClient().getGameData(User.getInstance().getUsername());
            game.readFromString(new Scanner(result));
        }));
        gameTimeLine.setCycleCount(-1);
        return gameTimeLine;
    }
}
