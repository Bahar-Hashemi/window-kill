package bahar.window_kill.model.entities.collectables;

import bahar.window_kill.model.entities.Trigorath;
import bahar.window_kill.model.entities.collectables.Collectable;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Random;

public class TrigorathCollectable extends Collectable {
    void setXp() {
        xp = 5;
    }
    Trigorath trigorath;

    @Override
    protected Node makeView() {
        Random random = new Random();
        Circle circle = new Circle();
        circle.setFill(Color.ORANGE);
        circle.setRadius(1.5);
        circle.setStrokeWidth(0);
        circle.setCenterX(trigorath.getLayoutX() + random.nextInt(0, 30));
        circle.setCenterY(trigorath.getLayoutY() + random.nextInt(0, 30));
        return circle;
    }
    public TrigorathCollectable(Trigorath trigorath) {
        this.trigorath = trigorath;
        view = makeView();
        setXp();
    }
}
