package bahar.window_kill.client.model;

import javafx.beans.binding.Bindings;
import javafx.scene.Node;

public class GameElement {
    protected double x, y;
    protected double width, height;
    protected final String id;
    protected final Node view;
    public GameElement(String id, Node view) {
        this.id = id;
        this.view = view;
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
    protected void setWidth(double width) {
        this.width = width;
    }
    protected void setHeight(double height) {
        this.height = height;
    }
    protected void setSize(double width, double height) {
        setWidth(width);
        setHeight(height);
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

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}
