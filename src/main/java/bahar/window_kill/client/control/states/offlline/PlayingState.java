package bahar.window_kill.client.control.states.offlline;

import bahar.window_kill.communications.util.Constants;
import bahar.window_kill.client.control.states.GameState;
import bahar.window_kill.communications.model.Game;
import bahar.window_kill.communications.processors.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.ArrayList;

public class PlayingState extends GameState {
    PlayingState(boolean isViewable, Game game) {
        super(isViewable, game, makeTimeLine(game));
        makeProcessors();
    }
    private void makeProcessors() {
        game.gameProcessors = new ArrayList<>();
        game.gameProcessors.add(new RequestProcessor(isViewable, game));
        game.gameProcessors.add(new AbilityProcessor(isViewable, game));
        game.gameProcessors.add(new SpawnProcessor(isViewable, game));
        game.gameProcessors.add(new BoardsProcessor(isViewable, game));
        game.gameProcessors.add(new AggressionProcessor(isViewable, game));
        game.gameProcessors.add(new MovementProcessor(isViewable, game)); //important: must be after Aggression processor
        game.gameProcessors.add(new DeathProcessor(isViewable, game));
        game.gameProcessors.add(new ViewProcessor(isViewable, game));
    }
    private static Timeline makeTimeLine(Game game) {
        Timeline gameTimeLine = new Timeline(new KeyFrame(new Duration(Constants.RESPOND_DURATION), actionEvent -> {
            game.clock += (long) Constants.RESPOND_DURATION;
            for (GameProcessor gameProcessor: game.gameProcessors)
                gameProcessor.run();
        }));
        gameTimeLine.setCycleCount(-1);
        return gameTimeLine;
    }
}
