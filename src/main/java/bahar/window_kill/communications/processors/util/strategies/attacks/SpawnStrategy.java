package bahar.window_kill.communications.processors.util.strategies.attacks;

import bahar.window_kill.communications.model.Game;
import bahar.window_kill.communications.model.Watch;
import bahar.window_kill.communications.model.entities.Entity;
import bahar.window_kill.communications.model.entities.generators.GeneratorEntity;
import javafx.scene.layout.Pane;

public class SpawnStrategy extends Strategy {
    boolean hasBullet = false;
    Watch watch;
    public SpawnStrategy(int spawnDuration) {
        watch = new Watch(spawnDuration) {
            @Override
            protected void onCall() {
                super.onCall();
                hasBullet = true;
            }
        };
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
        if (aggressionSource.isViewable)
            ((Pane) generatorEntity.getView().getParent()).getChildren().add(bullet.getView());
        game.addEntity(bullet);
        bullet.setLocation(generatorEntity.getBounds().getCenterX(), generatorEntity.getBounds().getCenterY());
        if (aggressionSource.isViewable)
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
