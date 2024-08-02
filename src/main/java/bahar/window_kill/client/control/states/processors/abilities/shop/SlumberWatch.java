package bahar.window_kill.client.control.states.processors.abilities.shop;

import bahar.window_kill.client.control.states.processors.abilities.AbilityWatch;
import bahar.window_kill.client.model.Game;
import bahar.window_kill.client.model.User;

public class SlumberWatch extends AbilityWatch {
    public SlumberWatch(Game game, User user) {
        super(game, user, 30, "Slumber", 150);
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
