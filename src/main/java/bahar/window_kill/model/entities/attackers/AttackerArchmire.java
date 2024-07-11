package bahar.window_kill.model.entities.attackers;

import bahar.window_kill.control.fazes.processors.strategies.strategies.DamageStrategy;
import bahar.window_kill.model.Watch;
import bahar.window_kill.model.entities.Collectable;
import bahar.window_kill.model.entities.Entity;
import bahar.window_kill.model.entities.LootDropper;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

public class AttackerArchmire extends AttackerEntity implements LootDropper {
    Watch opacityWatch;
    Watch damageWatch;
    public AttackerArchmire() {
        super(makeView(), 10, false, new DamageStrategy(), 10);
        opacityWatch = new Watch(30, actionEvent -> {view.setOpacity(view.getOpacity() - 0.003);});
        damageWatch = new Watch(800, actionEvent -> {setHP(getHP() - 2); setDamage(getDamage() - 2); strategy.act(this);});
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

        // Close the path to make a continuous shape
        path.getElements().add(new ClosePath());

        path.setStroke(Color.DARKMAGENTA);
        path.setFill(Color.DARKMAGENTA);
        path.setOpacity(0.5);

        return path;
    }
    @Override
    public void move(double targetX, double targetY) {
        opacityWatch.call();
        //it doesn't move
    }

    @Override
    public void aggress() {
        damageWatch.call();
    }

    @Override
    public void shout() {
        //it doesn't shout
    }

    @Override
    public int getLootCount() {
        return 2;
    }

    @Override
    public Entity makeLoot() {
        return new Collectable(3, Color.DARKMAGENTA);
    }
}
