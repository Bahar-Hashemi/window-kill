package bahar.window_kill.client.control.states.processors.abilities.skills;

import bahar.window_kill.client.control.states.processors.abilities.AbilityWatch;

public class CerberusWatch extends AbilityWatch {
    public CerberusWatch() {
        super(30,() -> {}, "Cerberus", 1500);
    }

    @Override
    protected void onStart() {
        super.onStart();
//        coolDown += 120 * 1000;
//        MiniEpsilon miniEpsilon = new MiniEpsilon();
//        mainBoard.add(miniEpsilon.getView());
//        entities.add(miniEpsilon);
//        miniEpsilon.setSceneX(user.getEpsilon().getSceneX() + miniEpsilon.getDifX());
//        miniEpsilon.setSceneY(user.getEpsilon().getSceneY() + miniEpsilon.getDifY());
    }
}
