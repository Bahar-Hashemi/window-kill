package bahar.window_kill.client.control.states.online;

import bahar.window_kill.client.control.Constants;
import bahar.window_kill.client.control.GameController;
import bahar.window_kill.client.control.connection.TCPClient;
import bahar.window_kill.client.control.states.GameState;
import bahar.window_kill.communications.model.Game;
import bahar.window_kill.communications.model.User;
import bahar.window_kill.communications.processors.GameProcessor;
import bahar.window_kill.communications.processors.ViewProcessor;
import bahar.window_kill.communications.processors.reader.OnlineUserProcessor;
import com.google.gson.Gson;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Scanner;

public class OnlinePlayingState extends GameState {
    public OnlinePlayingState(boolean isViewable, Game game) {
        super(isViewable, game, makeTimeLine(game));
        makeGameProcessors();
    }
    private static Timeline makeTimeLine(Game game) {
        Timeline gameTimeLine = new Timeline(new KeyFrame(new Duration(Constants.RESPOND_DURATION), actionEvent -> {
            for (GameProcessor gameProcessor: game.gameProcessors)
                gameProcessor.run();
            game.save = new TCPClient().getGameData(GameController.user.getUsername());
        }));
        gameTimeLine.setCycleCount(-1);
        return gameTimeLine;
    }
    private void makeGameProcessors() {
        game.gameProcessors = new ArrayList<>();
        game.gameProcessors.add(new ViewProcessor(isViewable, game));
        game.gameProcessors.add(new OnlineUserProcessor(isViewable, game));
    }
}
