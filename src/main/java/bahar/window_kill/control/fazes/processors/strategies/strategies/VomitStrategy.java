package bahar.window_kill.control.fazes.processors.strategies.strategies;

import bahar.window_kill.control.GameUtil;
import bahar.window_kill.control.loader.SoundLoader;
import bahar.window_kill.model.Watch;
import bahar.window_kill.model.entities.Entity;
import bahar.window_kill.model.entities.attackers.Vomit;
import bahar.window_kill.model.entities.generators.GeneratorEntity;
import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;

import java.util.Random;

import static bahar.window_kill.control.Deck.entities;
import static bahar.window_kill.control.Deck.mainBoard;

public class VomitStrategy extends Strategy {
    boolean hasBullet = false;
    Watch watch;
    public VomitStrategy(int spawnDuration) {
        watch = new Watch(spawnDuration, event -> hasBullet = true);
    }
    @Override
    public void act(Entity aggressionSource) {
        watch.call();
        if (!hasBullet)
            return;
        Entity bullet = new Vomit();
        mainBoard.getChildren().add(bullet.getView());
        entities.add(bullet);
        Random random = new Random();
        bullet.setSceneLocation(random.nextDouble(mainBoard.getLayoutX(), mainBoard.getLayoutX() + mainBoard.getWidth()),
                random.nextDouble(mainBoard.getLayoutY(), mainBoard.getLayoutY() + mainBoard.getHeight()));
        SoundLoader.ENEMY_SHOOT.play();
        onAct(aggressionSource); hasBullet = false;
    }
}
