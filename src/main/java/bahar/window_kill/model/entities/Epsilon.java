package bahar.window_kill.model.entities;

import bahar.window_kill.control.Constants;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Epsilon extends Entity {
    public Epsilon() {
        setView();
        deltaX = 0.4;
        deltaY = 0.4;
        HP = 100;
    }
    public Epsilon(double x, double y) {
        this();
        setLayoutLocation(x, y);
    }
    @Override
    protected void setView() {
        Circle circle = new Circle();
        circle.setFill(Constants.BACKGROUND_COLOR);
        circle.setStroke(Color.WHITE); // Set stroke color
        circle.setRadius(10);
        circle.setStrokeWidth(3);
        view = circle;
    }
}
