package bahar.window_kill.client.control.states.processors.abilities.skills;

import bahar.window_kill.client.control.states.processors.abilities.AbilityWatch;
import bahar.window_kill.client.model.Game;
import bahar.window_kill.client.model.User;
import bahar.window_kill.client.model.entities.generators.shooters.MiniEpsilon;

public class CerberusWatch extends AbilityWatch {
    public CerberusWatch(Game game, User user) {
        super(game, user, 30, "Cerberus", 1500);
    }

    @Override
    protected void onStart() {
        super.onStart();
        user.coolDown += 120 * 1000;
        MiniEpsilon miniEpsilon = new MiniEpsilon();
        user.mainBoard.add(miniEpsilon.getView());
        game.entities.add(miniEpsilon);
        miniEpsilon.setSceneX(user.getEpsilon().getSceneX() + miniEpsilon.getDifX());
        miniEpsilon.setSceneY(user.getEpsilon().getSceneY() + miniEpsilon.getDifY());
    }
}
