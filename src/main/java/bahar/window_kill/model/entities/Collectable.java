package bahar.window_kill.model.entities;

import bahar.window_kill.control.fazes.processors.strategies.strategies.PositiveStrategy;
import bahar.window_kill.control.util.SoundUtil;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Random;

public class Collectable extends Entity {
    private final int xp;
    private static final int INF = (int) (1E9 + 7);

    public Collectable(int xp, Color color) {
        super(createView(color), INF, false, new PositiveStrategy() {
            @Override
            public void onAct(Entity source) {
                source.setHP(0);
            }
        });
        this.xp = xp;
    }
    private static Node createView(Color color) {
        Circle circle = new Circle();
        circle.setFill(color);
        circle.setRadius(new Random().nextDouble(2, 4));
        circle.setStrokeWidth(0);
        return circle;
    }
    public int getXp() {
        return xp;
    }

    @Override
    public void move(double targetX, double targetY) {
        double speed = 10;
        double dx = targetX - getSceneX();
        double dy = targetY - getSceneY();
        double chord = Math.sqrt(dx * dx + dy * dy);
        setSceneX(getSceneX() + dx / chord * speed);
        setSceneY(getSceneY() + dy / chord * speed);
    }

    @Override
    public void aggress() {
        strategy.act(this);
    }


    @Override
    public void shout() {
        SoundUtil.COIN_COLLECT.play();
    }
}
