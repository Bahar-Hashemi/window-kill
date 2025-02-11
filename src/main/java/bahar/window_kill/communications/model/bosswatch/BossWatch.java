package bahar.window_kill.communications.model.bosswatch;

import bahar.window_kill.communications.model.Game;
import bahar.window_kill.communications.model.Watch;
import bahar.window_kill.communications.model.entities.generators.shooters.Epsilon;
import bahar.window_kill.communications.model.entities.generators.shooters.SmileyHand;
import bahar.window_kill.communications.model.boards.MainBoard;
import bahar.window_kill.communications.model.entities.generators.shooters.SmileyFace;

abstract public class BossWatch extends Watch {
    protected final SmileyFace face;
    protected final SmileyHand leftHand, rightHand;
    protected final Game game;
    public BossWatch(int duration, Game game, SmileyFace face, SmileyHand leftHand, SmileyHand rightHand) {
        super(duration);
        this.game = game;
        this.face = face;
        this.leftHand = leftHand;
        this.rightHand = rightHand;
    }
    abstract public boolean isValid(Epsilon epsilon, MainBoard mainBoard);
    abstract public String getMessage();
}
