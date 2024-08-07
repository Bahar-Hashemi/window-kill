package bahar.window_kill.client.control.states.online;

import bahar.window_kill.communications.util.Constants;
import bahar.window_kill.client.control.GameController;
import bahar.window_kill.client.control.connection.TCPClient;
import bahar.window_kill.client.control.states.GameState;
import bahar.window_kill.communications.model.Game;
import bahar.window_kill.communications.processors.GameProcessor;
import bahar.window_kill.communications.processors.ViewProcessor;
import bahar.window_kill.communications.processors.reader.OnlineChangeProcessor;
import bahar.window_kill.communications.processors.reader.OnlineEntityProcessor;
import bahar.window_kill.communications.processors.reader.OnlineUserProcessor;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.ArrayList;

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
            sendMyControls();
            if (GameController.user != null && GameController.user.mainBoard != null && GameController.user.mainBoard.getView() != null)
                GameController.user.mainBoard.getView().requestFocus();
        }));
        gameTimeLine.setCycleCount(-1);
        return gameTimeLine;
    }
    private static void sendMyControls() {
        new TCPClient().sendControls(GameController.user.getUsername(), GameController.user);
        GameController.user.abilityRequests.clear();
    }
    private void makeGameProcessors() {
        game.gameProcessors = new ArrayList<>();
        game.gameProcessors.add(new ViewProcessor(isViewable, game));
        game.gameProcessors.add(new OnlineUserProcessor(isViewable, game));
        game.gameProcessors.add(new OnlineEntityProcessor(isViewable, game));
        game.gameProcessors.add(new OnlineChangeProcessor(isViewable, game));
    }
}
