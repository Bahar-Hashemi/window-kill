package bahar.window_kill.model.entities;

import bahar.window_kill.control.Constants;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Bullet extends Entity {
    public Bullet(double deltaX, double deltaY) {
        setView();
        HP = 1;
        this.deltaX = deltaX / 5;
        this.deltaY = deltaY / 5;
    }
    @Override
    protected void setView() {
        Circle circle = new Circle();
        circle.setFill(Color.WHITE);
        circle.setRadius(3);
        circle.setStrokeWidth(0);
        view = circle;
    }
}
