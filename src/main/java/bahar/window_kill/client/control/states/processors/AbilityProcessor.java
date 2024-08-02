package bahar.window_kill.client.control.states.processors;


import bahar.window_kill.client.control.Constants;
import bahar.window_kill.client.model.Game;
import bahar.window_kill.client.model.User;

public class AbilityProcessor extends GameProcessor {
    public AbilityProcessor(Game game) {
        super(game);
    }

    @Override
    public void run() {
        for (User user: game.users) {
            user.coolDown = (long) Math.max(0, user.coolDown - Constants.RESPOND_DURATION);
            for (int i = user.abilities.size() - 1; i >= 0; i--)
                user.abilities.get(i).call(game.clock);
        }
    }
}
