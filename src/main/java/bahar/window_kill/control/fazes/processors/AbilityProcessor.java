package bahar.window_kill.control.fazes.processors;


import bahar.window_kill.control.Constants;

import static bahar.window_kill.control.Deck.*;

public class AbilityProcessor extends GameProcessor {
    @Override
    public void run() {
        coolDown = (long) Math.max(0, coolDown - Constants.RESPOND_DURATION);
        for (int i = abilities.size() - 1; i >= 0; i--)
            abilities.get(i).call();
    }
}
