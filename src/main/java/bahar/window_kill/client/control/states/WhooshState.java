package bahar.window_kill.client.control.states;

import bahar.window_kill.client.control.Constants;
import bahar.window_kill.client.model.Deck;
import bahar.window_kill.client.control.util.SoundUtil;
import bahar.window_kill.client.model.User;
import bahar.window_kill.client.model.boards.MainBoard;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class WhooshState extends GameState {
    public WhooshState(Deck deck) {
        super(deck, makeTimeLine(deck));
    }
    private static Timeline makeTimeLine(Deck deck) {
        Timeline whooshTimeLine = new Timeline(new KeyFrame(new Duration(Constants.RESPOND_DURATION), actionEvent -> {
            for (User user: deck.users) {
                MainBoard mainBoard = user.mainBoard;
                mainBoard.setSize(mainBoard.getWidth() * 0.95, mainBoard.getHeight() * 0.95);
                mainBoard.IndependentMoveX((Constants.SCREEN_WIDTH - mainBoard.getWidth()) / 2);
                mainBoard.IndependentMoveY((Constants.SCREEN_HEIGHT - mainBoard.getHeight()) / 2);
            }
        }));
        whooshTimeLine.setCycleCount(15);
        whooshTimeLine.setOnFinished(actionEvent -> {
            deck.setGameState(new PlayingState(deck));
        });
        return whooshTimeLine;
    }

    @Override
    public void play() {
        super.play();
        SoundUtil.WHOOSH.play();
    }

}
