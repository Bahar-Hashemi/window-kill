package bahar.window_kill.model;

import bahar.window_kill.control.fazes.processors.abilities.AbilityWatch;
import bahar.window_kill.model.boards.MainBoard;
import bahar.window_kill.model.data.Development;
import bahar.window_kill.model.data.Settings;
import bahar.window_kill.model.entities.generators.shooters.Epsilon;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

import java.util.ArrayList;

public class User {
    private boolean hasUpRequest = false, hasLeftRequest = false, hasDownRequest = false, hasRightRequest = false;
    private boolean killWish = false;
    private boolean shooting = false;
    private double mouseX, mouseY;
    private boolean hasPauseRequest = false;
    private boolean hasDefenseRequest = false;
    private boolean hasAttackRequest = false;
    final private Epsilon epsilon;
    public static MainBoard mainBoard;
    public Development development;
    public Settings settings;
    public long coolDown;
    int xp = 0;
    public ArrayList<AbilityWatch> abilities;
    public User(Epsilon epsilon) {
        this.epsilon = epsilon;
    }
    public void setKillWish(boolean killWish) {
        this.killWish = killWish;
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

    public boolean hasDefenseRequest() {
        return hasDefenseRequest;
    }

    public void setDefenseRequest(boolean hasDefenseRequest) {
        this.hasDefenseRequest = hasDefenseRequest;
    }

    public boolean hasAttackRequest() {
        return hasAttackRequest;
    }

    public void setAttackRequest(boolean hasAttackRequest) {
        this.hasAttackRequest = hasAttackRequest;
    }
    public void setXp(int xp) {
        this.xp = xp;
    }
    public int getXp() {
        return xp;
    }
}
