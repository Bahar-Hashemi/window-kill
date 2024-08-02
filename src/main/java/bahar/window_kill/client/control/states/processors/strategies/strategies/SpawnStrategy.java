package bahar.window_kill.client.control.states.processors.strategies.strategies;

import bahar.window_kill.client.model.Game;
import bahar.window_kill.client.control.util.GameUtil;
import bahar.window_kill.client.model.Watch;
import bahar.window_kill.client.model.entities.Entity;
import bahar.window_kill.client.model.entities.generators.GeneratorEntity;
import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;

public class SpawnStrategy extends Strategy {
    boolean hasBullet = false;
    Watch watch;
    public SpawnStrategy(int spawnDuration) {
        watch = new Watch(spawnDuration, () -> hasBullet = true);
    }
    @Override
    public void act(Entity aggressionSource, Game game) {
        //check my entities Aggressions
        if (!(aggressionSource instanceof GeneratorEntity generatorEntity)) {
            System.err.println("Illegal entity inserted");
            return;
        }
        watch.call(game.clock);
        if (!hasBullet)
            return;
        Entity bullet = generatorEntity.makeBullet();
        ((Pane) generatorEntity.getView().getParent()).getChildren().add(bullet.getView());
        game.addEntity(bullet);
        Bounds entityBounds = GameUtil.getSceneBounds(generatorEntity);
        bullet.setSceneLocation(entityBounds.getCenterX(), entityBounds.getCenterY());
        aggressionSource.getView().toFront();
        onAct(aggressionSource);
        hasBullet = false;
    }
    public void setSpawnDuration(long spawnDuration) {
        watch.setDuration(spawnDuration);
    }
    public long getSpawnDuration() {
        return watch.getDuration();
    }
}
