package bahar.window_kill.model;

import bahar.window_kill.control.GameController;
import bahar.window_kill.control.SoundController;
import bahar.window_kill.model.entities.Bullet;
import bahar.window_kill.model.entities.Epsilon;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class MainBoard extends GameBoard {
    public Epsilon epsilon;
    public ArrayList<Bullet> bullets = new ArrayList<>();
    public boolean wIsPressed, aIsPressed, sIsPressed, dIsPressed;
    public boolean isShooting; public double mouseX, mouseY;
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
        scene.setOnMousePressed(mouseEvent -> {
            isShooting = true;
            mouseX = mouseEvent.getX();
            mouseY = mouseEvent.getY();
        });
        scene.setOnMouseReleased(mouseEvent -> isShooting = false);
        scene.setOnMouseDragged(mouseEvent -> {mouseX = mouseEvent.getX(); mouseY = mouseEvent.getY();});
    }
    public void makeBullet() {
        double x = mouseX - epsilon.getLayoutX();
        double y = mouseY - epsilon.getLayoutY();
        double chord = Math.sqrt(x * x + y * y);
        Bullet bullet = new Bullet(x / chord, y / chord);
        bullet.setLayoutLocation(epsilon.getLayoutX(), epsilon.getLayoutY());
        bullets.add(bullet);
        ((Pane) scene.getRoot()).getChildren().add(bullet.getView());
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
    public void moveEntities() {
        moveEpsilon();
        for (Bullet bullet: bullets)
            bullet.setLayoutLocation(bullet.getLayoutX() + bullet.getDeltaX(), bullet.getLayoutY() + bullet.getDeltaY());
    }
    public void setEpsilon(Epsilon epsilon) {
        if (this.epsilon != null)
            ((Pane) this.getScene().getRoot()).getChildren().remove(this.epsilon);
        this.epsilon = epsilon;
        ((Pane) this.getScene().getRoot()).getChildren().add(this.epsilon.getView());
    }
}
