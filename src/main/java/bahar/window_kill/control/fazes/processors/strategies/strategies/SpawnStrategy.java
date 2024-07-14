package bahar.window_kill.control.fazes.processors.strategies.strategies;

import bahar.window_kill.control.GameUtil;
import bahar.window_kill.model.Watch;
import bahar.window_kill.model.entities.Entity;
import bahar.window_kill.model.entities.generators.GeneratorEntity;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;

import static bahar.window_kill.control.Deck.*;

public class SpawnStrategy extends Strategy {
    boolean hasBullet = false;
    Watch watch;
    public SpawnStrategy(int spawnDuration) {
        watch = new Watch(spawnDuration, event -> hasBullet = true);
    }
    @Override
    public void act(Entity aggressionSource) {
        //check my entities Aggressions
        if (!(aggressionSource instanceof GeneratorEntity generatorEntity)) {
            System.err.println("Illegal entity inserted");
            return;
        }
        watch.call();
        if (!hasBullet)
            return;
        Entity bullet = generatorEntity.makeBullet();
        ((Pane) generatorEntity.getView().getParent()).getChildren().add(bullet.getView());
        entities.add(bullet);
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
