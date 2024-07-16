package bahar.window_kill.control.fazes.processors;

import bahar.window_kill.control.GameController;
import bahar.window_kill.control.fazes.PauseState;
import bahar.window_kill.control.fazes.PlayingState;

import static bahar.window_kill.control.Deck.*;

public class RequestProcessor extends GameProcessor {
    @Override
    public void run() {
        //coolDown = (long) Math.min(0, coolDown - Constants.RESPOND_DURATION);
        if (user.hasPauseRequest())
            GameController.setGameState(new PauseState());
        if (user.hasKillWish()) {
            user.getEpsilon().setHP(-1);
            PlayingState.endGame();
        }
        if (user.hasDefenseRequest() && user.getEpsilon().getXp() > 100 && coolDown <= 0 && development.getActiveDefense() != null) {
            user.getEpsilon().setXp(user.getEpsilon().getXp() - 100);
            user.setDefenseRequest(false);
            abilities.add(development.getActiveDefense());
        }
        else if (user.hasAttackRequest() && user.getEpsilon().getXp() > 100 && coolDown <= 0 && development.getActiveAttack() != null) {
            user.getEpsilon().setXp(user.getEpsilon().getXp() - 100);
            user.setAttackRequest(false);
            abilities.add(development.getActiveAttack());
        }
    }
}
