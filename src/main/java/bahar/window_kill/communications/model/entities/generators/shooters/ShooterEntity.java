package bahar.window_kill.communications.model.entities.generators.shooters;

import bahar.window_kill.communications.processors.util.strategies.attacks.Strategy;
import bahar.window_kill.communications.model.Bounds;
import bahar.window_kill.communications.model.entities.Entity;
import bahar.window_kill.communications.model.entities.generators.GeneratorEntity;
import javafx.geometry.Point2D;
import javafx.scene.Node;

public abstract class ShooterEntity extends GeneratorEntity {
    protected Double gunDirectionX, gunDirectionY;
    protected int bulletDamage;

    protected ShooterEntity(boolean isViewable, String id, Node view, Bounds bounds, String className, int HP, boolean canImpact, Strategy strategy) {
        super(isViewable, id, view, bounds, className, HP, canImpact, strategy);
    }
    public void setGunDirection(double gunDirectionX, double gunDirectionY) {
        setGunDirectionX(gunDirectionX);
        setGunDirectionY(gunDirectionY);
    }
    public void setBulletDamage(int bulletDamage) {
        this.bulletDamage = bulletDamage;
    }
    public int getBulletDamage() {
        return bulletDamage;
    }
    public void target(Entity target) {
        Point2D sourcePosition = this.getView().localToScene(0, 0);
        Point2D targetPosition = target.getView().localToScene(0, 0);

        double dx = targetPosition.getX() - sourcePosition.getX();
        double dy = targetPosition.getY() - sourcePosition.getY();

        double chord = Math.sqrt(dx * dx + dy * dy);
        setGunDirection(dx / chord, dy / chord);
    }
    public double getGunDirectionX() {
        return gunDirectionX;
    }

    public void setGunDirectionX(double gunDirectionX) {
        this.gunDirectionX = gunDirectionX;
    }

    public double getGunDirectionY() {
        return gunDirectionY;
    }

    public void setGunDirectionY(double gunDirectionY) {
        this.gunDirectionY = gunDirectionY;
    }
}
