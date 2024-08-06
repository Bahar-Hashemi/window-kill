package bahar.window_kill.server.control.game.processors.abilities.skills;

import bahar.window_kill.server.model.Game;
import bahar.window_kill.client.model.User;
import bahar.window_kill.client.model.entities.generators.shooters.MiniEpsilon;
import bahar.window_kill.server.control.game.processors.abilities.AbilityType;
import bahar.window_kill.server.control.game.processors.abilities.AbilityWatch;

public class CerberusWatch extends AbilityWatch {
    public CerberusWatch(Game game, User user) {
        super(game, user, 30, AbilityType.CERBERUS, 1500);
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
