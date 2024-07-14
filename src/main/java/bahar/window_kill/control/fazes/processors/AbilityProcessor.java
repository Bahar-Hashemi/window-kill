package bahar.window_kill.control.fazes.processors;


import static bahar.window_kill.control.Deck.*;

public class AbilityProcessor extends GameProcessor {
    @Override
    public void run() {
        for (int i = abilities.size() - 1; i >= 0; i--)
            abilities.get(i).call();
    }
}
