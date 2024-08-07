package bahar.window_kill.communications.processors;


import bahar.window_kill.communications.util.Constants;
import bahar.window_kill.communications.model.Game;
import bahar.window_kill.communications.model.User;

public class AbilityProcessor extends GameProcessor {
    public AbilityProcessor(Boolean isViewable, Game game) {
        super(isViewable, game);
    }

    @Override
    public void run() {
        game.clock += 30;
        for (User user: game.users) {
            user.coolDown = (long) Math.max(0, user.coolDown - Constants.RESPOND_DURATION);
            for (int i = user.abilities.size() - 1; i >= 0; i--)
                user.abilities.get(i).call(game.clock);
        }
    }
}
