package bahar.window_kill.control.fazes.processors.abilities.shop;

import bahar.window_kill.control.fazes.processors.abilities.AbilityWatch;
import bahar.window_kill.model.entities.Entity;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import static bahar.window_kill.control.Deck.*;

public class BanishWatch extends AbilityWatch {
    public BanishWatch() {
        super(30, () -> {}, "Banish", 100);
        setCycleCount(1);
    }

    @Override
    protected void onStart() {
        super.onStart();
        for (Entity entity: entities)
            entity.impactFrom(user.getEpsilon().getSceneX(), user.getEpsilon().getSceneY(), 50);
    }

}
