package bahar.window_kill.communications.processors.util.abilities.skills;

import bahar.window_kill.communications.processors.util.abilities.AbilityType;
import bahar.window_kill.communications.processors.util.abilities.AbilityWatch;
import bahar.window_kill.communications.util.GameUtil;
import bahar.window_kill.communications.model.Game;
import bahar.window_kill.communications.model.User;
import bahar.window_kill.communications.model.entities.generators.shooters.MiniEpsilon;

public class CerberusWatch extends AbilityWatch {
    public CerberusWatch(Game game, User user) {
        super(game, user, 30, AbilityType.CERBERUS, 1500);
    }

    @Override
    protected void onStart() {
        super.onStart();
        user.coolDown += 120 * 1000;
        MiniEpsilon miniEpsilon = new MiniEpsilon(false, GameUtil.generateID()); //todo correct here!
        user.mainBoard.add(miniEpsilon.getView());
        game.entities.add(miniEpsilon);
        miniEpsilon.setX(user.getEpsilon().getX() + miniEpsilon.getDifX());
        miniEpsilon.setY(user.getEpsilon().getY() + miniEpsilon.getDifY());
    }
}
