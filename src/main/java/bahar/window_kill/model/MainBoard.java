package bahar.window_kill.model;

import bahar.window_kill.control.GameController;
import bahar.window_kill.model.entities.Epsilon;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

public class MainBoard extends GameBoard {
    public Epsilon epsilon;
    public boolean wIsPressed, aIsPressed, sIsPressed, dIsPressed;
    public MainBoard() {
        super();
        scene.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.W)
                wIsPressed = true;
            if (keyEvent.getCode() == KeyCode.A)
                aIsPressed = true;
            if (keyEvent.getCode() == KeyCode.S)
                sIsPressed = true;
            if (keyEvent.getCode() == KeyCode.D)
                dIsPressed = true;
        });
        scene.setOnKeyReleased(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.W)
                wIsPressed = false;
            if (keyEvent.getCode() == KeyCode.A)
                aIsPressed = false;
            if (keyEvent.getCode() == KeyCode.S)
                sIsPressed = false;
            if (keyEvent.getCode() == KeyCode.D)
                dIsPressed = false;
        });
    }
    public void setXAndMoveEntities(double x) {
        double deltaX = getX() - x;
        if (epsilon != null)
            epsilon.setLayoutX(epsilon.getLayoutX() + deltaX);
        setX(x);
    }
    public void setYAndMoveEntities(double y) {
        double deltaY = getY() - y;
        if (epsilon != null)
            epsilon.setLayoutY(epsilon.getLayoutY() + deltaY);
        setY(y);
    }
    public void setDimensions(double x, double y, double width, double height) {
        setXAndMoveEntities(x);
        setYAndMoveEntities(y);
        setWidth(width); setHeight(height);
    }
    public void moveEpsilon() {
        if (wIsPressed) epsilon.setLayoutY(epsilon.getLayoutY() - epsilon.getDeltaY());
        if (sIsPressed) epsilon.setLayoutY(epsilon.getLayoutY() + epsilon.getDeltaX());
        if (aIsPressed) epsilon.setLayoutX(epsilon.getLayoutX() - epsilon.getDeltaX());
        if (dIsPressed) epsilon.setLayoutX(epsilon.getLayoutX() + epsilon.getDeltaX());
    }
    public void setEpsilon(Epsilon epsilon) {
        if (this.epsilon != null)
            ((Pane) this.getScene().getRoot()).getChildren().remove(this.epsilon);
        this.epsilon = epsilon;
        ((Pane) this.getScene().getRoot()).getChildren().add(this.epsilon.getView());
    }
}
