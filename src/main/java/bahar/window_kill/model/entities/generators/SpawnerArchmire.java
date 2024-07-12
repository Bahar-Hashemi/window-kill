package bahar.window_kill.model.entities.generators;

import bahar.window_kill.control.fazes.processors.strategies.strategies.SpawnStrategy;
import bahar.window_kill.control.loader.SoundLoader;
import bahar.window_kill.model.Watch;
import bahar.window_kill.model.entities.Collectable;
import bahar.window_kill.model.entities.Entity;
import bahar.window_kill.model.entities.LootDropper;
import bahar.window_kill.model.entities.attackers.AttackerArchmire;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

public class SpawnerArchmire extends GeneratorEntity implements LootDropper {
    Watch watch;
    double previousX = 1, previousY = 0;
    public SpawnerArchmire() {
        super(makeView(), 30, false, new SpawnStrategy(200));
    }
    private static Node makeView() {
        Path path = new Path();

        // Move to the start point of the upper curve
        MoveTo moveTo = new MoveTo(-25, 0);

        // Upper curve
        QuadCurveTo upperCurve = new QuadCurveTo();
        upperCurve.setControlX(0);
        upperCurve.setControlY(-50);
        upperCurve.setX(25);
        upperCurve.setY(0);

        // Lower curve, starting from the end of the upper curve
        QuadCurveTo lowerCurve = new QuadCurveTo();
        lowerCurve.setControlX(0);
        lowerCurve.setControlY(25);
        lowerCurve.setX(-25);
        lowerCurve.setY(0);

        // Add all parts to the path
        path.getElements().add(moveTo);
        path.getElements().addAll(upperCurve, lowerCurve);
        path.setFill(Color.DARKMAGENTA);

        // Close the path to make a continuous shape
        path.getElements().add(new ClosePath());
        return path;
    }
    @Override
    public void move(double targetX, double targetY) {
        double speed = 3;
        double dx = targetX - getSceneX() + previousX;
        double dy = targetY - getSceneY() + previousY;
        double chord = Math.sqrt(dx * dx + dy * dy);
        if (chord == 0) {
            dx = 1;
            dy = 0;
            chord = 1;
        }
        setSceneX(getSceneX() + dx / chord * speed);
        setSceneY(getSceneY() + dy / chord * speed);
        previousX = dx;
        previousY = dy;
    }

    @Override
    public void aggress() {
        strategy.act(this);
    }

    @Override
    public void shout() {
        SoundLoader.HIT.play();
    }

    @Override
    public Entity makeBullet() {
        return new AttackerArchmire();
    }

    @Override
    public int getLootCount() {
        return 5;
    }

    @Override
    public Entity makeLoot() {
        return new Collectable(3, Color.DARKMAGENTA);
    }
}
