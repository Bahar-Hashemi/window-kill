package bahar.window_kill.client.control.states.processors.abilities.skills;

import bahar.window_kill.client.control.states.processors.abilities.AbilityWatch;
import bahar.window_kill.client.model.Game;
import bahar.window_kill.client.model.User;

public class AthenaWatch extends AbilityWatch {
    public AthenaWatch(Game game, User user) {
        super(game, user, 30, "Athena", 1200);
    }

    @Override
    protected void onStart() {
        super.onStart();
        user.mainBoard.shrink *= 0.8;
    }
}
