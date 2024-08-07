package bahar.window_kill.communications.model;

import javafx.scene.Node;

public class GameElement {
    protected double x, y;
    protected final Bounds bounds;
    protected final String id;
    transient protected final Node view;
    public final boolean isViewable;
    protected final String className;
    protected String additionalData;
    protected double opacity = 1;
    public GameElement(boolean isViewable, String id, Node view, Bounds bounds, String className) {
        this.isViewable = isViewable;
        this.id = id;
        this.view = view;
        this.bounds = bounds;
        this.className = className;
    }
    public Node getView() {
        return view;
    }
    public void setX(double x) {
        this.x = x;
        if (getView() != null && getView().getParent() != null)
            getView().setLayoutX(x - getView().getParent().getLayoutX());
    }
    public void setY(double y) {
        this.y = y;
        if (getView() != null && getView().getParent() != null)
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
    public String getId() {
        return id;
    }
    public void readFrom(GameElement gameElement) {
        setLocation(gameElement.getX(), gameElement.getY());
        opacity = gameElement.opacity;
    }
    public String getClassName() {
        return className;
    }

    public String getAdditionalData() {
        return additionalData;
    }
}
