package bahar.window_kill.model.entities;

import bahar.window_kill.model.MainBoard;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.shape.Shape;

abstract public class Entity {
    protected double HP;
    boolean onImpact = false;
    protected double deltaX, deltaY;
    protected Node view;

    abstract protected Node makeView();
    public Node getView() {
        return view;
    }
    public void setLayoutLocation(double x, double y) {
        view.setLayoutX(x);
        view.setLayoutY(y);
    }
    public void setLayoutX(double x) {
        view.setLayoutX(x);
    }
    public void setLayoutY(double y) {
        view.setLayoutY(y);
    }
    public double getLayoutX() {
        return view.getLayoutX();
    }
    public double getLayoutY() {
        return view.getLayoutY();
    }
    public void setDeltaX(double deltaX) {
        this.deltaX = deltaX;
    }
    public void setDeltaY(double deltaY) {
        this.deltaY = deltaY;
    }
    public double getDeltaX() {
        return deltaX;
    }
    public double getDeltaY() {
        return deltaY;
    }
    public double getHP() {
        return HP;
    }
    public void setHP(double HP) {
        this.HP = HP;
    }
    public static double commonArea(Node first, Node second) {
            Shape intersection = Shape.intersect((Shape) first, (Shape) second);
            double area = intersection.getBoundsInLocal().getWidth() * intersection.getBoundsInLocal().getHeight();
            return area;
    }
    public void setImpact(Boolean onImpact) {
        this.onImpact = onImpact;
    }
    public Boolean onImpact() {
        return onImpact;
    }
}
