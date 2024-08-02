package bahar.window_kill.client.model.entities.attackers;

import bahar.window_kill.client.control.states.processors.strategies.strategies.DamageStrategy;
import bahar.window_kill.client.model.entities.Entity;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Bullet extends Entity implements AttackerEntity {
    private final double directionX, directionY;
    private final boolean traverser;
    private int damage;
    public Bullet(int HP, double radius, Color color, int damage, double directionX, double directionY, boolean traversesWalls) {
        super(makeView(radius, color), HP, false,
                new DamageStrategy() {
                    @Override
                    public void onAct(Entity aggressionSource) {
                        super.onAct(aggressionSource);
                        aggressionSource.setHP(0);
                        ((Bullet)aggressionSource).setDamage(0);
                    }
                });
        this.damage = damage;
        this.directionX = directionX;
        this.directionY = directionY;
        this.traverser = traversesWalls;
    }
    private static Node makeView(double radius, Color color) {
        Circle circle = new Circle();
        circle.setFill(color);
        circle.setRadius(radius);
        circle.setStrokeWidth(0);
        return circle;
    }

    @Override
    public void move() {
        double SPEED = 17;
        setSceneY(getSceneY() + directionY * SPEED);
        setSceneX(getSceneX() + directionX * SPEED);
    }

    @Override
    public void aggress() {
        strategy.act(this, game);
    }

    public void shout() {

    }

    public boolean isTraverser() {
        return traverser;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public void setDamage(int damage) {
        this.damage = damage;
    }
}
