package bahar.window_kill.client.control.states;

import bahar.window_kill.client.control.Constants;
import bahar.window_kill.client.model.Deck;
import bahar.window_kill.client.control.states.processors.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.ArrayList;

public class PlayingState extends GameState {
    static ArrayList<GameProcessor> gameProcessors;
    static Timeline gameTimeLine;
    PlayingState(Deck deck) {
        super(deck, makeTimeLine(deck));
        makeProcessors();
    }
    private void makeProcessors() {
        gameProcessors = new ArrayList<>();
        gameProcessors.add(new RequestProcessor(deck));
        gameProcessors.add(new AbilityProcessor(deck));
        gameProcessors.add(new SpawnProcessor(deck));
        gameProcessors.add(new BoardsProcessor(deck));
        gameProcessors.add(new AggressionProcessor(deck));
        gameProcessors.add(new MovementProcessor(deck)); //important: must be after Aggression processor
        gameProcessors.add(new DeathProcessor(deck));
    }
    private static Timeline makeTimeLine(Deck deck) {
        gameTimeLine = new Timeline(new KeyFrame(new Duration(Constants.RESPOND_DURATION), actionEvent -> {
            deck.clock += (long) Constants.RESPOND_DURATION;
            for (GameProcessor gameProcessor: gameProcessors)
                gameProcessor.run();
        }));
        gameTimeLine.setCycleCount(-1);
        return gameTimeLine;
    }
}
