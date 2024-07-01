package bahar.window_kill.model;

import bahar.window_kill.control.Constants;
import bahar.window_kill.model.entities.Epsilon;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class User {
    public Epsilon epsilon;
    public boolean wIsPressed = false, aIsPressed = false, sIsPressed = false, dIsPressed = false;
    private boolean killWish = false;
    public boolean isShooting = false;
    public double mouseX, mouseY;
    public boolean hasPauseRequest = false;
    public void killRequest() {
        killWish = true;
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(500), actionEvent -> killWish = false));
        timeline.play();
    }
    public boolean hasKillWish() {
        return killWish;
    }
    public User() {
        epsilon = new Epsilon();
    }
    public User(Epsilon epsilon) {
        this.epsilon = epsilon;
    }
    public void move() {
        if (epsilon.onImpact()) {
            epsilon.setLayoutX(epsilon.getLayoutX() + epsilon.getDeltaX());
            epsilon.setLayoutY(epsilon.getLayoutY() + epsilon.getDeltaY());
            epsilon.setDeltaX(epsilon.getDeltaX() * 0.80);
            epsilon.setDeltaY(epsilon.getDeltaY() * 0.80);
            if (Math.sqrt(epsilon.getDeltaX() * epsilon.getDeltaX() + epsilon.getDeltaY() * epsilon.getDeltaY()) < 1) {
                epsilon.setImpact(false);
                epsilon.setDeltaX(Constants.RESPOND_DURATION / 5);
                epsilon.setDeltaY(Constants.RESPOND_DURATION / 5);
            }
            return;
        }
        if (wIsPressed) epsilon.setLayoutY(epsilon.getLayoutY() - epsilon.getDeltaY());
        if (sIsPressed) epsilon.setLayoutY(epsilon.getLayoutY() + epsilon.getDeltaX());
        if (aIsPressed) epsilon.setLayoutX(epsilon.getLayoutX() - epsilon.getDeltaX());
        if (dIsPressed) epsilon.setLayoutX(epsilon.getLayoutX() + epsilon.getDeltaX());
    }
}
