package bahar.window_kill.communications.model.entities.generators;

import bahar.window_kill.communications.model.Bounds;
import bahar.window_kill.communications.model.User;
import bahar.window_kill.communications.model.Watch;
import bahar.window_kill.communications.model.entities.Collectable;
import bahar.window_kill.communications.model.entities.Entity;
import bahar.window_kill.communications.model.entities.LootDropper;
import bahar.window_kill.communications.model.entities.attackers.AttackerArchmire;
import bahar.window_kill.communications.processors.util.strategies.attacks.SpawnStrategy;
import bahar.window_kill.communications.util.GameUtil;
import bahar.window_kill.client.control.util.SoundUtil;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

public class SpawnerArchmire extends GeneratorEntity implements LootDropper {
    Watch watch;
    double previousX = 1, previousY = 0;
    public SpawnerArchmire(boolean isViewable, String id) {
        super(isViewable, id, (isViewable? makeView(): null), new Bounds(-25, -50, 25, 50), SpawnerArchmire.class.getName(),
                30, false, new SpawnStrategy(200));
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
        double dx = user.getEpsilon().getX() - getX() + previousX;
        double dy = user.getEpsilon().getY() - getY() + previousY;
        double chord = Math.sqrt(dx * dx + dy * dy);
        if (chord == 0) {
            dx = 1;
            dy = 0;
            chord = 1;
        }
        setX(getX() + dx / chord * speed);
        setY(getY() + dy / chord * speed);
        previousX = dx;
        previousY = dy;
    }
    @Override
    public void aggress() {
        strategy.act(this, game);
    }

    @Override
    public void morph() {
        super.morph();
    }

    @Override
    public void shout() {
        if (isViewable)
            SoundUtil.HIT.play();
    }

    @Override
    public Entity makeBullet() {
        return new AttackerArchmire(isViewable, GameUtil.generateID());
    }

    @Override
    public int getLootCount() {
        return 5;
    }

    @Override
    public Entity makeLoot() {
        return new Collectable(isViewable, GameUtil.generateID(), 3, Color.DARKMAGENTA);
    }
}
