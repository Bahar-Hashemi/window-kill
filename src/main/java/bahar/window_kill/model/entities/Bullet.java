package bahar.window_kill.model.entities;

import bahar.window_kill.control.Constants;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Bullet extends Entity {
    public Bullet(double deltaX, double deltaY) {
        view = makeView();
        HP = 5;
        this.deltaX = Constants.RESPOND_DURATION / 3 * deltaX;
        this.deltaY = Constants.RESPOND_DURATION / 3 * deltaY;
    }
    @Override
    protected Node makeView() {
        Circle circle = new Circle();
        circle.setFill(Color.WHITE);
        circle.setRadius(3.5);
        circle.setStrokeWidth(0);
        return circle;
    }
}
