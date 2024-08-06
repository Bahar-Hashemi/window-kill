package bahar.window_kill.client.control.states.offlline;

import bahar.window_kill.client.control.Constants;
import bahar.window_kill.client.control.states.GameState;
import bahar.window_kill.client.control.states.offlline.processors.*;
import bahar.window_kill.client.model.Game;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.ArrayList;

public class PlayingState extends GameState {
    static ArrayList<GameProcessor> gameProcessors;
    static Timeline gameTimeLine;
    PlayingState(Game game) {
        super(game, makeTimeLine(game));
        makeProcessors();
    }
    private void makeProcessors() {
        gameProcessors = new ArrayList<>();
        gameProcessors.add(new RequestProcessor(game));
        gameProcessors.add(new AbilityProcessor(game));
        gameProcessors.add(new SpawnProcessor(game));
        gameProcessors.add(new BoardsProcessor(game));
        gameProcessors.add(new AggressionProcessor(game));
        gameProcessors.add(new MovementProcessor(game)); //important: must be after Aggression processor
        gameProcessors.add(new DeathProcessor(game));
    }
    private static Timeline makeTimeLine(Game game) {
        gameTimeLine = new Timeline(new KeyFrame(new Duration(Constants.RESPOND_DURATION), actionEvent -> {
            game.clock += (long) Constants.RESPOND_DURATION;
            for (GameProcessor gameProcessor: gameProcessors)
                gameProcessor.run();
        }));
        gameTimeLine.setCycleCount(-1);
        return gameTimeLine;
    }
}
