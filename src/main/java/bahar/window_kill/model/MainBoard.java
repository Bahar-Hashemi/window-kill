package bahar.window_kill.model;

import bahar.window_kill.control.Constants;
import bahar.window_kill.control.GameController;
import bahar.window_kill.control.SoundController;
import bahar.window_kill.model.entities.*;
import bahar.window_kill.model.entities.collectables.Collectable;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;

public class MainBoard extends GameBoard {
    private Label HPLabel, XPLabel;
    private User user;
    public int xp = 0;
    public ArrayList<Bullet> bullets = new ArrayList<>();
    public ArrayList<Enemy> enemies = new ArrayList<>();
    public ArrayList<Collectable> collectables = new ArrayList<>();
    public MainBoard() {
        super();
        user = new User();
    }
    public void requestUserControls(User user) {
        requestFocus();
        this.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.W)
                user.wIsPressed = true;
            if (keyEvent.getCode() == KeyCode.A)
                user.aIsPressed = true;
            if (keyEvent.getCode() == KeyCode.S)
                user.sIsPressed = true;
            if (keyEvent.getCode() == KeyCode.D)
                user.dIsPressed = true;
            if (keyEvent.getCode() == KeyCode.E) {
                user.hasPauseRequest = !user.hasPauseRequest;
                if (user.hasPauseRequest)
                    GameController.pauseGame();
                else
                    GameController.reStart();
            }
            if (keyEvent.getCode() == KeyCode.Q) {
                if (user.hasKillWish())
                    GameController.endGame();
                user.killRequest();
            }
        });
        this.setOnKeyReleased(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.W)
                user.wIsPressed = false;
            if (keyEvent.getCode() == KeyCode.A)
                user.aIsPressed = false;
            if (keyEvent.getCode() == KeyCode.S)
                user.sIsPressed = false;
            if (keyEvent.getCode() == KeyCode.D)
                user.dIsPressed = false;
        });
        this.setOnMousePressed(mouseEvent -> {
            user.isShooting = true;
            user.mouseX = mouseEvent.getX();
            user.mouseY = mouseEvent.getY();
        });
        this.setOnMouseReleased(mouseEvent -> user.isShooting = false);
        this.setOnMouseDragged(mouseEvent -> {user.mouseX = mouseEvent.getX(); user.mouseY = mouseEvent.getY();});
    }
    public void setUser(User user) {
        this.user = user;
        add(user.epsilon.getView());
    }
    public User getUser() {
        return user;
    }
    public void addCollectable(Collectable collectable) {
        add(collectable.getView());
        collectables.add(collectable);
    }
    public void removeCollectable(Collectable collectable) {
        getChildren().remove(collectable.getView());
        collectables.remove(collectable);
    }
    public void updateXPLabel() {
        if (XPLabel == null) {
            XPLabel = new Label("XP: " + xp);
            XPLabel.setLayoutX(getWidth() - XPLabel.getWidth()); XPLabel.setLayoutY(0);
            XPLabel.getStyleClass().add("XPLabel");
            getChildren().add(XPLabel);
        }
        else {
            XPLabel.setText("XP: " + xp);
            XPLabel.setLayoutX(getWidth() - XPLabel.getWidth());
        }
    }
    public void updateHPLabel() {
        if (user.epsilon != null && HPLabel == null) {
            HPLabel = new Label("HP: " + user.epsilon.getHP());
            HPLabel.setLayoutX(0); HPLabel.setLayoutY(0);
            HPLabel.getStyleClass().add("HPLabel");
            getChildren().add(HPLabel);
        }
        else if (user.epsilon != null) {
            HPLabel.setText("HP: " + user.epsilon.getHP());
        }
    }
    public void makeBullet() {
        double x = user.mouseX - user.epsilon.getLayoutX();
        double y = user.mouseY - user.epsilon.getLayoutY();
        double chord = Math.sqrt(x * x + y * y);
        Bullet bullet = new Bullet(x / chord, y / chord);
        bullet.setLayoutLocation(user.epsilon.getLayoutX(), user.epsilon.getLayoutY());
        bullets.add(bullet);
        getChildren().add(bullet.getView());
    }
    public void setXAndMoveEntities(double x) {
        double deltaX = getLayoutX() - x;
        if (user.epsilon != null)
            user.epsilon.setLayoutX(user.epsilon.getLayoutX() + deltaX);
        for (Bullet bullet: bullets)
            bullet.setLayoutX(bullet.getLayoutX() + deltaX);
        for (Enemy enemy: enemies)
            enemy.setLayoutX(enemy.getLayoutX() + deltaX);
        for (Collectable collectable: collectables)
            collectable.setLayoutX(collectable.getLayoutX() + deltaX);
        setLayoutX(x);
    }
    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
        add(enemy.getView());
    }
    public void setYAndMoveEntities(double y) {
        double deltaY = getLayoutY() - y;
        if (user.epsilon != null)
            user.epsilon.setLayoutY(user.epsilon.getLayoutY() + deltaY);
        for (Bullet bullet: bullets)
            bullet.setLayoutY(bullet.getLayoutY() + deltaY);
        for (Enemy enemy: enemies)
            enemy.setLayoutY(enemy.getLayoutY() + deltaY);
        for (Collectable collectable: collectables)
            collectable.setLayoutY(collectable.getLayoutY() + deltaY);
        setLayoutY(y);
    }
    public void setDimensions(double x, double y, double width, double height) {
        setXAndMoveEntities(x);
        setYAndMoveEntities(y);
        setSize(width, height);
    }
    public void moveEpsilon() {
        user.move();
        //process being in board
        if (user.epsilon.inBoardArea(this) < 527) { //todo insert epsilon area
            Point2D collisionPoint = user.epsilon.collisionInBoardPoint(this);
            impact(collisionPoint.getX(), collisionPoint.getY(), new Entity[]{user.epsilon}, 1);
        }
    }
    public void moveEntities() {
        processCollectibles();
        processImpacts();
        processEpsilonDamage();
        moveEpsilon();
        moveBullets();
        moveEnemies();
    }
    private void processCollectibles() {
        Random random = new Random();
        for (Collectable collectable: collectables) {
            LocalRouting.apply(collectable, user.epsilon);
            collectable.setDeltaX(collectable.getDeltaX() * 10);
            collectable.setDeltaY(collectable.getDeltaY() * 10);
            collectable.setLayoutX(collectable.getLayoutX() + collectable.getDeltaX());
            collectable.setLayoutY(collectable.getLayoutY() + collectable.getDeltaY());
        }
        boolean hasCollected = false;
        for (int i = collectables.size() - 1; i >= 0; i--) {
            Collectable collectable = collectables.get(i);
            if (Entity.commonArea(collectable.getView(), user.epsilon.getView()) > 5) {
                xp += collectable.getXp();
                collectables.remove(i);
                getChildren().remove(collectable.getView());
                hasCollected = true;
            }
        }
        if (hasCollected) {
            SoundController.COIN_COLLECT.play();
        }
    }
    private void processEpsilonDamage() {
        boolean isDamaged = false;
        for (Enemy enemy: enemies)
            if (Entity.commonArea(user.epsilon.getView(), enemy.getView()) > 5) {
                user.epsilon.setHP(user.epsilon.getHP() - enemy.getAttackDamage());
                impact((user.epsilon.getLayoutX() + enemy.getLayoutX()) / 2, (user.epsilon.getLayoutY() + enemy.getLayoutY()) / 2, new Entity[]{enemy}, 1);
                isDamaged = true;
            }
        if (isDamaged) {
            user.epsilon.setColor(Color.RED);
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), e -> {
                user.epsilon.setColor(Color.WHITE);
            }));
            timeline.play();
            SoundController.DAMAGE.play();
        }
        if (user.epsilon.getHP() < 0) {
            GameController.endGame();
        }
    }
    private void moveEnemies() {
        for (Enemy enemy: enemies) {
            enemy.processMovement(this);
            enemy.setLayoutX(enemy.getLayoutX() + enemy.getDeltaX());
            enemy.setLayoutY(enemy.getLayoutY() + enemy.getDeltaY());
            if (enemy.onImpact()) {
                enemy.setDeltaX(enemy.getDeltaX() * 0.80);
                enemy.setDeltaY(enemy.getDeltaY() * 0.80);
                if (Math.sqrt(enemy.getDeltaX() * enemy.getDeltaX() + enemy.getDeltaY() * enemy.getDeltaY()) < 1) {
                    enemy.setImpact(false);
                }
            }
        }
    }
    private void moveBullets() {
        boolean hasShoot = false;
        for (Bullet bullet: bullets) {
            bullet.setLayoutLocation(bullet.getLayoutX() + bullet.getDeltaX(), bullet.getLayoutY() + bullet.getDeltaY());
            if (bullet.getLayoutY() <= 0) {
                setHeight(getHeight() + 3 * bullet.getHP());
                setYAndMoveEntities(getLayoutY() - 3 * bullet.getHP());
                hasShoot = true;
                bullet.setHP(0);
            }
            if (bullet.getLayoutX() <= 0) {
                setWidth(getWidth() + 3 * bullet.getHP());
                setXAndMoveEntities(getLayoutX() - 3 * bullet.getHP());
                hasShoot = true;
                bullet.setHP(0);
            }
            if (bullet.getLayoutY() > getHeight()) {
                setHeight(getHeight() + 3 * bullet.getHP());
                hasShoot = true;
                bullet.setHP(0);
            }
            if (bullet.getLayoutX() > getWidth()) {
                setWidth(getWidth() + 3 * bullet.getHP());
                hasShoot = true;
                bullet.setHP(0);
            }
            for (Enemy enemy: enemies) {
                if (Entity.commonArea(bullet.getView(), enemy.getView()) > 5) {
                    damage(enemy, bullet.getHP());
                    bullet.setHP(0);
                    break;
                }
            }

        }
        if (hasShoot)
            SoundController.HIT.play();
        for (int i = bullets.size() - 1; i >= 0; i--)
            if (bullets.get(i).getHP() == 0) {
                SoundController.HIT.play();
                getChildren().remove(bullets.get(i).getView());
                bullets.remove(i);
            }
    }
    public void damage(Enemy enemy, double HP) {
        enemy.setHP(enemy.getHP() - HP);
        if (enemy.getHP() <= 0) {
            enemies.remove(enemy);
            enemy.addCollectible(this);
            getChildren().remove(enemy.getView());
        }
    }
    public void moveWalls() {
        double minusWidth = Math.min((getWidth() - Constants.MINIMUM_WIDTH) / 2, 0.2);
        double minusHeight = Math.min((getHeight() - Constants.MINIMUM_HEIGHT) / 2, 0.2);
        setDimensions(getLayoutX() + minusWidth / 2, getLayoutY() + minusHeight / 2, getWidth() - 2 * minusWidth , getHeight() - 2 * minusHeight);
    }
    public void impact(double x, double y, Entity[] entities, double power) {
        for (Entity entity: entities) {
            if (entity == null) continue;
            double dx = entity.getLayoutX() - x;
            double dy = entity.getLayoutY() - y;
            entity.setImpact(true);
            double chord = Math.sqrt(dx * dx + dy * dy);
            entity.setDeltaX(dx / chord * 10 * power);
            entity.setDeltaY(dy / chord * 10 * power);
        }
    }

    void processImpacts() {
        for (int i = 0; i < enemies.size(); i++)
            for (int j = i + 1; j < enemies.size(); j++) {
                Entity first = enemies.get(i);
                Entity second = enemies.get(j);
                if (Entity.commonArea(first.getView(), second.getView()) > 5) {
                    impact((first.getLayoutX() + second.getLayoutX()) / 2, (first.getLayoutY() + second.getLayoutY()) / 2, new Entity[] {first, second}, 1);
                }
            }
    }
}
