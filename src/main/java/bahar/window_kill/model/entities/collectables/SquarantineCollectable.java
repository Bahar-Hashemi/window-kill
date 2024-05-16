package bahar.window_kill.model.entities.collectables;

import bahar.window_kill.model.entities.Squarantine;
import bahar.window_kill.model.entities.collectables.Collectable;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Random;

public class SquarantineCollectable extends Collectable {
    @Override
    void setXp() {
        xp = 5;
    }
    Squarantine squarantine;
    @Override
    protected Node makeView() {
        Random random = new Random();
        Circle circle = new Circle();
        circle.setFill(Color.LIMEGREEN);
        circle.setRadius(1.5);
        circle.setStrokeWidth(0);
        circle.setCenterX(squarantine.getLayoutX() + random.nextInt(0, 20));
        circle.setCenterY(squarantine.getLayoutY() + random.nextInt(0, 20));
        return  circle;
    }
    public SquarantineCollectable(Squarantine squarantine) {
        this.squarantine = squarantine;
        setXp();
        view = makeView();
    }
}
