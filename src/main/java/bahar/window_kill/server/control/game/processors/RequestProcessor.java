package bahar.window_kill.server.control.game.processors;

import bahar.window_kill.client.control.states.offlline.processors.abilities.AbilityType;
import bahar.window_kill.server.model.Game;
import bahar.window_kill.server.model.User;

public class RequestProcessor extends GameProcessor {
    public RequestProcessor(Game game) {
        super(game);
    }

    @Override
    public void run() {
        for (User user: game.users)
            handleRequest(user);
    }
    private void handleRequest(User user) {
        if (user.hasKillWish())
            user.getEpsilon().setHP(-1 * (1E9 + 7));
        if (!user.abilityRequests.isEmpty()) {
            for (AbilityType abilityName: user.abilityRequests) {
//                AbilityWatch ability = AbilityWatch.getAbilityByType(game, user, abilityName);
//                if (ability != null)
//                    user.abilities.add(ability);
            }
            user.abilityRequests.clear();
        }
    }
}
