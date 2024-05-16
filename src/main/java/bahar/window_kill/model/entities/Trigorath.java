package bahar.window_kill.model.entities;

import bahar.window_kill.control.Constants;
import bahar.window_kill.model.LocalRouting;
import bahar.window_kill.model.MainBoard;
import bahar.window_kill.model.entities.collectables.TrigorathCollectable;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;

import java.util.Random;

public class Trigorath extends Enemy {
    public Trigorath(double x, double y) {
        super(10);
        HP = 15;
        view = makeView();
        setLayoutX(x);
        setLayoutY(y);
    }

    public void processMovement(MainBoard mainBoard) {
        if (onImpact)
            return;
        LocalRouting.apply(this, mainBoard.epsilon);
        double dx = mainBoard.epsilon.getLayoutX() - getLayoutX();
        double dy = mainBoard.epsilon.getLayoutY() - getLayoutY();
        double dist = Math.sqrt(dx * dx + dy * dy);
        deltaX *= dist / 40;
        deltaY *= dist / 40;
    }

    @Override
    protected Node makeView() {
        Polygon polygon = new Polygon(15, 0, 0, 15 * Math.sqrt(3), 30, 15 * Math.sqrt(3));
        polygon.setFill(Constants.BACKGROUND_COLOR);
        polygon.setStroke(Color.ORANGE);
        polygon.setStrokeWidth(3);
        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(10), polygon);
        rotateTransition.setCycleCount(-1);
        rotateTransition.setByAngle(360);
        rotateTransition.setInterpolator(Interpolator.LINEAR);
        rotateTransition.play();
        return polygon;
    }

    static public Enemy randomGenerate(MainBoard mainBoard) {
        Random random = new Random();
        if (random.nextBoolean() == true) {
            double height = (random.nextBoolean() ? mainBoard.getHeight() + 30 : -30);
            double width = (random.nextDouble(-30, mainBoard.getWidth() + 30));
            return new Trigorath(width, height);
        }
        double width = (random.nextBoolean() ? mainBoard.getWidth() + 30 : -30);
        double height = (random.nextDouble(-30, mainBoard.getHeight() + 30));
        Trigorath trigorath = new Trigorath(width, height);
        LocalRouting.apply(trigorath, mainBoard.epsilon);
        return new Trigorath(width, height);
    }

    public void addCollectible(MainBoard mainBoard) {
        mainBoard.addCollectable(new TrigorathCollectable(this));
        mainBoard.addCollectable(new TrigorathCollectable(this));
        mainBoard.addCollectable(new TrigorathCollectable(this));
    }
}