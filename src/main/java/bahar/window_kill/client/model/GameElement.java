package bahar.window_kill.client.model;

import javafx.scene.Node;

public class GameElement {
    protected double x, y;
    protected final Bounds bounds;
    protected final String id;
    transient protected final Node view;
    public GameElement(String id, Node view, Bounds bounds) {
        this.id = id;
        this.view = view;
        this.bounds = bounds;
    }
    public Node getView() {
        return view;
    }
    public void setX(double x) {
        this.x = x;
        if (getView().getParent() != null)
            getView().setLayoutX(x - getView().getParent().getLayoutX());
    }
    public void setY(double y) {
        this.y = y;
        if (getView().getParent() != null)
            getView().setLayoutY(y - getView().getParent().getLayoutY());
    }
    public void setLocation(double x, double y) {
        setX(x);
        setY(y);
    }
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Bounds getBounds() {
        return new Bounds(x + bounds.getMinimumX(), y + bounds.getMinimumY(), x + bounds.getMaximumX(), y + bounds.getMaximumY());
    }

    public double getWidth() {
        return bounds.getMaximumX() - bounds.getMinimumX();
    }
    public double getHeight() {
        return bounds.getMaximumY() - bounds.getMinimumY();
    }
}
