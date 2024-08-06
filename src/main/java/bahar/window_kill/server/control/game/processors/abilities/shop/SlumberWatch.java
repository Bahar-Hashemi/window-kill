package bahar.window_kill.server.control.game.processors.abilities.shop;

import bahar.window_kill.server.model.Game;
import bahar.window_kill.client.model.User;
import bahar.window_kill.server.control.game.processors.abilities.AbilityType;
import bahar.window_kill.server.control.game.processors.abilities.AbilityWatch;

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
