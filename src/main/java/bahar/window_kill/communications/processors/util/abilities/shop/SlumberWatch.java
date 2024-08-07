package bahar.window_kill.communications.processors.util.abilities.shop;

import bahar.window_kill.communications.processors.util.abilities.AbilityType;
import bahar.window_kill.communications.processors.util.abilities.AbilityWatch;
import bahar.window_kill.communications.model.Game;
import bahar.window_kill.communications.model.User;

public class SlumberWatch extends AbilityWatch {
    public SlumberWatch(Game game, User user) {
        super(game, user, 30, AbilityType.SLUMBER, 150);
        setCycleCount(333);
    }

    @Override
    protected void onStart() {
        super.onStart();
        game.isLocked = true;
    }

    @Override
    protected void onEnd() {
        super.onEnd();
        game.isLocked = false;
    }

}
