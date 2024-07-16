package bahar.window_kill.control.fazes;

import bahar.window_kill.control.Constants;
import bahar.window_kill.control.Deck;
import bahar.window_kill.control.GameController;
import bahar.window_kill.control.fazes.processors.*;
import bahar.window_kill.view.MainStage;
import bahar.window_kill.view.PaneBuilder;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.ArrayList;

import static bahar.window_kill.control.Deck.newDeck;
import static bahar.window_kill.control.Deck.user;

public class PlayingState extends GameState {
    static ArrayList<GameProcessor> gameProcessors;
    static PlayingState instance;
    static Timeline gameTimeLine;
    private PlayingState() {
        super(makeTimeLine());
        makeProcessors();
    }
    private static void makeProcessors() {
        gameProcessors = new ArrayList<>();
        gameProcessors.add(new RequestProcessor());
        gameProcessors.add(new AbilityProcessor());
        gameProcessors.add(new SpawnProcessor());
        gameProcessors.add(new BoardsProcessor());
        gameProcessors.add(new AggressionProcessor());
        gameProcessors.add(new MovementProcessor()); //important: must be after Aggression processor
        gameProcessors.add(new DeathProcessor());
    }
    private static Timeline makeTimeLine() {
        gameTimeLine = new Timeline(new KeyFrame(new Duration(Constants.RESPOND_DURATION), actionEvent -> {
            Deck.clock += (long) Constants.RESPOND_DURATION;
            for (GameProcessor gameProcessor: gameProcessors)
                gameProcessor.run();
        }));
        gameTimeLine.setCycleCount(-1);
        return gameTimeLine;
    }
    public static PlayingState getInstance() {
        if (instance == null)
            instance = new PlayingState();
        return instance;
    }
    public static void newGame() {
        Deck.clock = 0;
        instance = new PlayingState();
    }
    public static void endGame() {
        gameTimeLine.stop();
        Pane pane = PaneBuilder.GAME_OVER_PANE.generatePane();
        MainStage.add(pane);
    }
}
