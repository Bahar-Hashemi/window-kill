package bahar.window_kill.communications.processors;

import bahar.window_kill.communications.processors.util.abilities.AbilityWatch;
import bahar.window_kill.communications.processors.util.abilities.AbilityType;
import bahar.window_kill.communications.model.Game;
import bahar.window_kill.client.control.states.offlline.PauseState;
import bahar.window_kill.communications.model.User;

public class RequestProcessor extends GameProcessor {
    public RequestProcessor(Boolean isViewable, Game game) {
        super(isViewable, game);
    }

    @Override
    public void run() {
        for (User user: game.users)
            user.coolDown = Math.max(0, user.coolDown - 30);
        for (User user: game.users)
            handleRequest(user);
    }
    private void handleRequest(User user) {
        if (isViewable && user.hasPauseRequest())
            game.setGameState(new PauseState(isViewable, game));
        if (user.hasKillWish())
            user.getEpsilon().setHP(-1 * (1E9 + 7));
        if (!user.abilityRequests.isEmpty() && user.coolDown <= 0) {
            for (AbilityType abilityName: user.abilityRequests) {
                AbilityWatch ability = AbilityWatch.getAbilityByType(game, user, abilityName);
                if (ability != null)
                    user.abilities.add(ability);
            }
            user.abilityRequests.clear();
        }
    }
}
