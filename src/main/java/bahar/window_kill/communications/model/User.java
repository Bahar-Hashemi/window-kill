package bahar.window_kill.communications.model;

import bahar.window_kill.communications.processors.util.abilities.AbilityType;
import bahar.window_kill.communications.model.entities.generators.shooters.Epsilon;
import bahar.window_kill.communications.processors.util.abilities.AbilityWatch;
import bahar.window_kill.client.control.util.FileUtil;
import bahar.window_kill.communications.model.boards.MainBoard;
import bahar.window_kill.communications.data.Development;
import bahar.window_kill.communications.data.Settings;
import bahar.window_kill.communications.data.TableSquad;
import bahar.window_kill.communications.data.TableUser;


import java.util.ArrayList;

public class User {
    /*send to server*/
    private String username, password;
    private boolean hasUpRequest = false, hasLeftRequest = false, hasDownRequest = false, hasRightRequest = false;
    private boolean shooting = false;
    private double mouseX, mouseY;
    private boolean hasSummonRequest = false;

    public boolean hasSummonRequest() {
        return hasSummonRequest;
    }

    public void setSummonRequest(boolean hasSummonRequest) {
        this.hasSummonRequest = hasSummonRequest;
    }

    private boolean hasPauseRequest = false, killWish = false;
    public ArrayList<AbilityWatch> abilities;
    public ArrayList<AbilityType> abilityRequests;
    public Development development;
    public TableSquad tableSquad;
    public TableUser tableUser;
    public long coolDown;

    // get from server
    public Epsilon epsilon;
    public MainBoard mainBoard;
    public Settings settings;
    public String state;
    int xp = 0;
    public static User onlineInstance;
    public User(Epsilon epsilon, MainBoard mainBoard) {
        this.epsilon = epsilon;
        this.mainBoard = mainBoard;
        abilities = new ArrayList<>();
        abilityRequests = new ArrayList<>();
        settings = FileUtil.readSettings();
        development = FileUtil.readDevelopment();
        state = "offline";
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
            double dx = mouseX - epsilon.getBounds().getCenterX();
            double dy = mouseY - epsilon.getBounds().getCenterY();
            double chord = Math.sqrt(dx * dx + dy * dy);
            epsilon.setGunDirectionX(dx / chord);
            epsilon.setGunDirectionY(dy / chord);
            epsilon.aggress();
        }
    }
    public void setXp(int xp) {
        this.xp = xp;
    }
    public int getXp() {
        return xp;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public Development getDevelopment() {
        return this.development;
    }
    public void setDevelopment(Development development) {
        this.development = development;
    }

    public TableUser getTableUser() {
        return tableUser;
    }

    public void setTableUser(TableUser tableUser) {
        this.tableUser = tableUser;
    }

    public void readFrom(User goalUser) {
        setXp(goalUser.getXp());
        epsilon.setHP(goalUser.epsilon.getHP());
        epsilon.setLocation(goalUser.epsilon.getX(), goalUser.epsilon.getY());
        mainBoard.setLocation(goalUser.mainBoard.getX(), goalUser.mainBoard.getY());
        mainBoard.setSize(goalUser.mainBoard.getWidth(), goalUser.mainBoard.getHeight());
        abilities = goalUser.abilities;
        coolDown = goalUser.coolDown;
        setTableUser(goalUser.getTableUser());
    }
}
