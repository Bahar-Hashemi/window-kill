package bahar.window_kill.client.control.states.processors.abilities.shop;

import bahar.window_kill.client.control.states.processors.abilities.AbilityWatch;

public class BanishWatch extends AbilityWatch {
    public BanishWatch() {
        super(30, () -> {}, "Banish", 100);
        setCycleCount(1);
    }

    @Override
    protected void onStart() {
        super.onStart();
//        for (Entity entity: entities)
//            entity.impactFrom(user.getEpsilon().getSceneX(), user.getEpsilon().getSceneY(), 50);
    }

}
