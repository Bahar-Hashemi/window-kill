package bahar.window_kill.client.control.states.offlline.processors.strategies.movements;

import bahar.window_kill.client.model.entities.Entity;

public class ImpactStrategy { //todo need to change it for slap
    boolean onImpact, canImpact;
    double deltaX, deltaY;
    public ImpactStrategy(boolean canImpact) {
        this.canImpact = canImpact;
    }
    public void impact(double impactX, double impactY, Entity target) {
        impact(impactX, impactY, target, 15);
    }
    public void impact(double impactX, double impactY, Entity target, double power) {
        onImpact = true;
        deltaX = target.getX() - impactX;
        deltaY = target.getY() - impactY;
        double chord = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        deltaX = deltaX / chord * power;
        deltaY = deltaY / chord * power;
    }
    
    public void act(Entity entity) {
        if (onImpact) {
            entity.setX(entity.getX() + deltaX);
            entity.setY(entity.getY() + deltaY);
            deltaX *= 0.8;
            deltaY *= 0.8;
            if (Math.sqrt(deltaX * deltaX + deltaY * deltaY) < 0.5)
                onImpact = false;
        }
    }

    public boolean canImpact() {
        return canImpact;
    }

    public void setCanImpact(boolean canImpact) {
        this.canImpact = canImpact;
    }

    public boolean isOnImpact() {
        return onImpact;
    }

    public void setOnImpact(boolean onImpact) {
        this.onImpact = onImpact;
    }

    public double getDeltaX() {
        return deltaX;
    }

    public void setDeltaX(double deltaX) {
        this.deltaX = deltaX;
    }

    public double getDeltaY() {
        return deltaY;
    }

    public void setDeltaY(double deltaY) {
        this.deltaY = deltaY;
    }
}
