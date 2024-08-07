package bahar.window_kill.communications.model.entities;

import bahar.window_kill.communications.model.Bounds;
import bahar.window_kill.communications.model.User;
import bahar.window_kill.communications.processors.util.strategies.attacks.PositiveStrategy;
import bahar.window_kill.communications.util.GameUtil;
import bahar.window_kill.client.control.util.SoundUtil;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Random;

public class Collectable extends Entity {
    private final int xp;
    private static final int INF = (int) (1E9 + 7);

    public Collectable(boolean isViewable, String id, int xp, Color color) {
        super(isViewable, id, createView(isViewable, color), new Bounds(-4, -4, 4, 4), Collectable.class.getName(),
                INF, false, new PositiveStrategy() {
            @Override
            public void onAct(Entity source) {
                source.setHP(0);
            }
        });
        this.xp = xp;
    }
    private static Node createView(boolean isViewable, Color color) {
        if (!isViewable)
            return null;
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
    public void move() {
        User user = GameUtil.findMyUser(this, game);
        if (user == null)
            user = game.users.get(new Random().nextInt(game.users.size()));
        double targetX = user.getEpsilon().getX();
        double targetY = user.getEpsilon().getY();
        double speed = 10;
        double dx = targetX - getX();
        double dy = targetY - getY();
        double chord = Math.sqrt(dx * dx + dy * dy);
        setX(getX() + dx / chord * speed);
        setY(getY() + dy / chord * speed);
    }
    @Override
    public void aggress() {
        strategy.act(this, game);
    }

    @Override
    public void morph() {

    }


    @Override
    public void shout() {
        SoundUtil.COIN_COLLECT.play();
    }
}
