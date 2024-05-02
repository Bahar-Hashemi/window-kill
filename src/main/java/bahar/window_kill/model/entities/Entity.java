package bahar.window_kill.model.entities;

import javafx.scene.Node;

abstract public class Entity {
    protected int HP;
    protected double deltaX, deltaY;
    protected Node view;
    abstract protected void setView();
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
    public double getDeltaX() {
        return deltaX;
    }
    public double getDeltaY() {
        return deltaY;
    }
}
