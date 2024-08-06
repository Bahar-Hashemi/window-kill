package bahar.window_kill.server.control.game.processors.abilities.skills;

import bahar.window_kill.server.model.Game;
import bahar.window_kill.client.model.User;
import bahar.window_kill.server.control.game.processors.abilities.AbilityType;
import bahar.window_kill.server.control.game.processors.abilities.AbilityWatch;

public class AthenaWatch extends AbilityWatch {
    public AthenaWatch(Game game, User user) {
        super(game, user, 30, AbilityType.ATHENA, 1200);
    }

    @Override
    protected void onStart() {
        super.onStart();
        user.mainBoard.shrink *= 0.8;
    }
}
