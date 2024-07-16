package bahar.window_kill.control.fazes.processors.abilities.skills;

import bahar.window_kill.control.fazes.processors.abilities.AbilityWatch;
import bahar.window_kill.model.entities.Entity;
import bahar.window_kill.model.entities.generators.shooters.MiniEpsilon;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import static bahar.window_kill.control.Deck.*;

public class CerberusWatch extends AbilityWatch {
    public CerberusWatch() {
        super(30,() -> {}, "Cerberus", 1500);
    }

    @Override
    protected void onStart() {
        super.onStart();
        coolDown += 120 * 1000;
        MiniEpsilon miniEpsilon = new MiniEpsilon();
        mainBoard.add(miniEpsilon.getView());
        entities.add(miniEpsilon);
        miniEpsilon.setSceneX(user.getEpsilon().getSceneX() + miniEpsilon.getDifX());
        miniEpsilon.setSceneY(user.getEpsilon().getSceneY() + miniEpsilon.getDifY());
    }
}
