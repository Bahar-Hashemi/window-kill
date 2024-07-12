package bahar.window_kill.model;

import bahar.window_kill.model.entities.generators.shooters.Epsilon;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

public class User {
    private boolean hasUpRequest = false, hasLeftRequest = false, hasDownRequest = false, hasRightRequest = false;
    private boolean killWish = false;
    private boolean shooting = false;
    private double mouseX, mouseY;
    private boolean hasPauseRequest = false;
    final private Epsilon epsilon;
    public User(Epsilon epsilon) {
        this.epsilon = epsilon;
    }
    public void killRequest() {
        killWish = true;
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(500), actionEvent -> killWish = false));
        timeline.play();
    }
    public boolean hasKillWish() {
        return killWish;
    }

    public boolean hasUpRequest() {
        return hasUpRequest;
    }

    public void setUpRequest(boolean hasUpRequest) {
        this.hasUpRequest = hasUpRequest;
    }

    public boolean hasLeftRequest() {
        return hasLeftRequest;
    }

    public void setLeftRequest(boolean hasLeftRequest) {
        this.hasLeftRequest = hasLeftRequest;
    }

    public boolean hasDownRequest() {
        return hasDownRequest;
    }

    public void setDownRequest(boolean hasDownRequest) {
        this.hasDownRequest = hasDownRequest;
    }

    public boolean hasRightRequest() {
        return hasRightRequest;
    }

    public void setRightRequest(boolean hasRightRequest) {
        this.hasRightRequest = hasRightRequest;
    }

    public boolean isShooting() {
        return shooting;
    }

    public void setShooting(boolean shooting) {
        this.shooting = shooting;
    }

    public double getMouseX() {
        return mouseX;
    }

    public void setMouseX(double mouseX) {
        this.mouseX = mouseX;
    }

    public double getMouseY() {
        return mouseY;
    }

    public void setMouseY(double mouseY) {
        this.mouseY = mouseY;
    }

    public boolean hasPauseRequest() {
        return hasPauseRequest;
    }

    public void setPauseRequest(boolean hasPauseRequest) {
        this.hasPauseRequest = hasPauseRequest;
    }

    public Epsilon getEpsilon() {
        return epsilon;
    }
    public void aggress() {
        if (isShooting()) {
            double dx = mouseX - epsilon.getSceneX();
            double dy = mouseY - epsilon.getSceneY();
            double chord = Math.sqrt(dx * dx + dy * dy);
            epsilon.setGunDirectionX(dx / chord);
            epsilon.setGunDirectionY(dy / chord);
            epsilon.aggress();
        }
    }
}
