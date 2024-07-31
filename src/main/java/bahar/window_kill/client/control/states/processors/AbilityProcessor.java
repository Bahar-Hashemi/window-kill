package bahar.window_kill.client.control.states.processors;


import bahar.window_kill.client.control.Constants;
import bahar.window_kill.client.model.Deck;
import bahar.window_kill.client.model.User;

public class AbilityProcessor extends GameProcessor {
    public AbilityProcessor(Deck deck) {
        super(deck);
    }

    @Override
    public void run() {
        for (User user: deck.users) {
            user.coolDown = (long) Math.max(0, user.coolDown - Constants.RESPOND_DURATION);
            for (int i = user.abilities.size() - 1; i >= 0; i--)
                user.abilities.get(i).call(deck.clock);
        }
    }
}
