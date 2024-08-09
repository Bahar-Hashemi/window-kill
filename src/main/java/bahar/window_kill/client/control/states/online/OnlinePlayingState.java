package bahar.window_kill.client.control.states.online;

import bahar.window_kill.client.view.MainStage;
import bahar.window_kill.client.view.PaneBuilder;
import bahar.window_kill.communications.processors.util.abilities.AbilityType;
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
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.ArrayList;

public class OnlinePlayingState extends GameState {
    private static Pane pausePane = null; //a little bit of tof
    public OnlinePlayingState(boolean isViewable, Game game) {
        super(isViewable, game, makeTimeLine(game));
        makeGameProcessors();
    }
    private static Timeline makeTimeLine(Game game) {
        Timeline gameTimeLine = new Timeline(new KeyFrame(new Duration(40), actionEvent -> {
            if (GameController.user.hasPauseRequest() && pausePane == null)
                pause();
            if (!GameController.user.hasPauseRequest() && pausePane != null)
                resume();
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
    private static void pause() {
        pausePane = PaneBuilder.PAUSE_PANE.generatePane();
        MainStage.add(pausePane);
    }
    private static void resume() {
        MainStage.remove(pausePane);
        pausePane = null;
    }
    private static void sendMyControls() {
        new TCPClient().sendControls(GameController.user.getUsername(), GameController.user);
        GameController.user.abilityRequests.clear();
        GameController.user.setSummonRequest(false);
    }
    private void makeGameProcessors() {
        game.gameProcessors = new ArrayList<>();
        game.gameProcessors.add(new OnlineUserProcessor(isViewable, game));
        game.gameProcessors.add(new OnlineEntityProcessor(isViewable, game));
        game.gameProcessors.add(new OnlineChangeProcessor(isViewable, game)); //important it must be the last processor
        game.gameProcessors.add(new ViewProcessor(isViewable, game));
    }
}
