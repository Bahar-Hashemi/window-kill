package bahar.window_kill.client.control.states.processors;

import bahar.window_kill.client.model.Deck;
import bahar.window_kill.client.control.states.PauseState;
import bahar.window_kill.client.model.User;

public class RequestProcessor extends GameProcessor {
    public RequestProcessor(Deck deck) {
        super(deck);
    }

    @Override
    public void run() {
        for (User user: deck.users)
            handleRequest(user);
    }
    private void handleRequest(User user) {
        if (user.hasPauseRequest())
            deck.setGameState(new PauseState(deck));
        if (user.hasKillWish())
            user.getEpsilon().setHP(-1 * (1E9 + 7));
        if (user.hasDefenseRequest() && user.getXp() > 100 && user.coolDown <= 0 && user.development.getActiveDefense() != null) {
            user.setXp(user.getXp() - 100);
            user.setDefenseRequest(false);
            user.abilities.add(user.development.getActiveDefense());
        }
        else if (user.hasAttackRequest() && user.getXp() > 100 && user.coolDown <= 0 && user.development.getActiveAttack() != null) {
            user.setXp(user.getXp() - 100);
            user.setAttackRequest(false);
            user.abilities.add(user.development.getActiveAttack());
        }
    }
}
