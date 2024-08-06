package bahar.window_kill.client.model.entities.generators;

import bahar.window_kill.client.model.User;
import bahar.window_kill.client.model.Watch;
import bahar.window_kill.client.model.entities.Collectable;
import bahar.window_kill.client.model.entities.Entity;
import bahar.window_kill.client.model.entities.LootDropper;
import bahar.window_kill.client.model.entities.attackers.AttackerArchmire;
import bahar.window_kill.client.control.states.offlline.processors.strategies.strategies.SpawnStrategy;
import bahar.window_kill.client.control.util.GameUtil;
import bahar.window_kill.client.control.util.SoundUtil;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

public class SpawnerArchmire extends GeneratorEntity implements LootDropper {
    Watch watch;
    double previousX = 1, previousY = 0;
    public SpawnerArchmire(String id) {
        super(id, makeView(), 30, false, new SpawnStrategy(200));
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
    public void move() {
        double speed = 3;
        User user = GameUtil.findMyUser(this, game);
        double dx = user.getEpsilon().getSceneX() - getSceneX() + previousX;
        double dy = user.getEpsilon().getSceneY() - getSceneY() + previousY;
        double chord = Math.sqrt(dx * dx + dy * dy);
        if (chord == 0) {
            dx = 1;
            dy = 0;
            chord = 1;
        }
        setX(getSceneX() + dx / chord * speed);
        setY(getSceneY() + dy / chord * speed);
        previousX = dx;
        previousY = dy;
    }
    @Override
    public void aggress() {
        strategy.act(this, game);
    }

    @Override
    public void shout() {
        SoundUtil.HIT.play();
    }

    @Override
    public Entity makeBullet() {
        return new AttackerArchmire(GameUtil.generateID());
    }

    @Override
    public int getLootCount() {
        return 5;
    }

    @Override
    public Entity makeLoot() {
        return new Collectable(GameUtil.generateID(), 3, Color.DARKMAGENTA);
    }
}
