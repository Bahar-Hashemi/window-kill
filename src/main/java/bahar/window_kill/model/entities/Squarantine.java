package bahar.window_kill.model.entities;
import bahar.window_kill.control.Constants;
import bahar.window_kill.model.LocalRouting;
import bahar.window_kill.model.MainBoard;
import bahar.window_kill.model.entities.collectables.SquarantineCollectable;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;

import java.util.Random;

public class Squarantine extends Enemy {
    double mult = 1;
    public Squarantine(double x, double y) {
        super(6);
        HP  = 15;
        view = makeView();
        setLayoutX(x);
        setLayoutY(y);
    }
    public void processMovement(MainBoard mainBoard) {
        if (onImpact)
            return;
        LocalRouting.apply(this, mainBoard.getUser().epsilon);
        Random random = new Random();
        if (mult > 2)
            mult-=2;
        else if (random.nextDouble(0, 1) > 0.99) {
            mult = 16;
        }
        deltaX *= mult;
        deltaY *= mult;
    }

    public void addCollectible(MainBoard mainBoard) {
        mainBoard.addCollectable(new SquarantineCollectable(this));
        mainBoard.addCollectable(new SquarantineCollectable(this));
    }

    @Override
    protected Node makeView() {
        Group group = new Group();
        Polygon polygon = new Polygon(0, 0, 0, 25, 25, 25, 25, 0);
        polygon.setFill(Constants.BACKGROUND_COLOR);
        polygon.setStroke(Color.LIMEGREEN);
        polygon.setStrokeWidth(3);
        group.getChildren().add(polygon);
        //
        Circle circle2 = new Circle();
        circle2.setFill(Constants.BACKGROUND_COLOR);
        circle2.setStroke(Color.PINK); // Set stroke color
        circle2.setRadius(4);
        circle2.setStrokeWidth(4);
        circle2.setCenterX(-10); circle2.setCenterY(0);
        group.getChildren().add(circle2);
        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(10), group);
        rotateTransition.setCycleCount(-1);
        rotateTransition.setByAngle(360);
        rotateTransition.setInterpolator(Interpolator.LINEAR);
        rotateTransition.play();
        return group;
    }

    static public Enemy randomGenerate(MainBoard mainBoard) {
        Random random = new Random();
        if (random.nextBoolean() == true) {
            double height = (random.nextBoolean()? mainBoard.getHeight() + 30: -30);
            double width = (random.nextDouble(-30, mainBoard.getWidth() + 30));
            return new Squarantine(width, height);
        }
        double width = (random.nextBoolean()? mainBoard.getWidth() + 30: -30);
        double height = (random.nextDouble(-30, mainBoard.getHeight() + 30));
        Trigorath trigorath = new Trigorath(width, height);
        LocalRouting.apply(trigorath, mainBoard.getUser().epsilon);
        return new Squarantine(width, height);
    }
}