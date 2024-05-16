package bahar.window_kill.model;

import bahar.window_kill.control.Constants;
import bahar.window_kill.control.GameController;
import bahar.window_kill.control.SoundController;
import bahar.window_kill.model.entities.*;
import bahar.window_kill.model.entities.collectables.Collectable;
import bahar.window_kill.view.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;

public class MainBoard extends GameBoard {
    public Epsilon epsilon;
    private Label HPLabel, XPLabel;
    public int xp = 1000;
    public ArrayList<Bullet> bullets = new ArrayList<>();
    public ArrayList<Enemy> enemies = new ArrayList<>();
    public ArrayList<Collectable> collectables = new ArrayList<>();
    public boolean wIsPressed, aIsPressed, sIsPressed, dIsPressed;
    public boolean qIsPressed = false;
    public boolean onPause = false;
    public boolean isShooting; public double mouseX, mouseY;
    public MainBoard() {
        super();
        addMouseEvents();
    }
    private void addMouseEvents() {
        scene.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.W)
                wIsPressed = true;
            if (keyEvent.getCode() == KeyCode.A)
                aIsPressed = true;
            if (keyEvent.getCode() == KeyCode.S)
                sIsPressed = true;
            if (keyEvent.getCode() == KeyCode.D)
                dIsPressed = true;
            if (keyEvent.getCode() == KeyCode.E) {
                if (!onPause) {
                    GameController.pauseGame();
                    PauseStage pauseStage = new PauseStage();
                    pauseStage.show();
                    onPause = true;
                }
            }
            if (keyEvent.getCode() == KeyCode.Q) {
                if (qIsPressed) {
                    GameController.endGame();
                    GameOverStage gameOverStage = new GameOverStage();
                    GameLauncher.stage = gameOverStage;
                    gameOverStage.show();
                }
                qIsPressed = true;
                Timeline timeline = new Timeline(new KeyFrame(Duration.millis(200), e -> {qIsPressed = false;}));
                timeline.play();
            }
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
    public void addCollectable(Collectable collectable) {
        add(collectable.getView());
        collectables.add(collectable);
    }
    public void removeCollectable(Collectable collectable) {
        root.getChildren().remove(collectable.getView());
        collectables.remove(collectable);
    }
    public void updateXPLabel() {
        if (XPLabel == null) {
            XPLabel = new Label("XP: " + xp);
            XPLabel.setLayoutX(root.getWidth() - XPLabel.getWidth()); XPLabel.setLayoutY(0);
            XPLabel.getStyleClass().add("XPLabel");
            root.getChildren().add(XPLabel);
        }
        else {
            XPLabel.setText("XP: " + xp);
            XPLabel.setLayoutX(root.getWidth() - XPLabel.getWidth());
        }
    }
    public void updateHPLabel() {
        if (epsilon != null && HPLabel == null) {
            HPLabel = new Label("HP: " + epsilon.getHP());
            HPLabel.setLayoutX(0); HPLabel.setLayoutY(0);
            HPLabel.getStyleClass().add("HPLabel");
            root.getChildren().add(HPLabel);
        }
        else if (epsilon != null) {
            HPLabel.setText("HP: " + epsilon.getHP());
        }
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
        for (Bullet bullet: bullets)
            bullet.setLayoutX(bullet.getLayoutX() + deltaX);
        for (Enemy enemy: enemies)
            enemy.setLayoutX(enemy.getLayoutX() + deltaX);
        for (Collectable collectable: collectables)
            collectable.setLayoutX(collectable.getLayoutX() + deltaX);
        setX(x);
    }
    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
        add(enemy.getView());
    }
    public void setYAndMoveEntities(double y) {
        double deltaY = getY() - y;
        if (epsilon != null)
            epsilon.setLayoutY(epsilon.getLayoutY() + deltaY);
        for (Bullet bullet: bullets)
            bullet.setLayoutY(bullet.getLayoutY() + deltaY);
        for (Enemy enemy: enemies)
            enemy.setLayoutY(enemy.getLayoutY() + deltaY);
        for (Collectable collectable: collectables)
            collectable.setLayoutY(collectable.getLayoutY() + deltaY);
        setY(y);
    }
    public void setDimensions(double x, double y, double width, double height) {
        setXAndMoveEntities(x);
        setYAndMoveEntities(y);
        setWidth(width); setHeight(height);
    }
    public void moveEpsilon() {
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
        if (epsilon.inBoardArea(this) < 528) { //todo insert epsilon area
            Point2D collisionPoint = epsilon.collisionInBoardPoint(this);
            impact(collisionPoint.getX(), collisionPoint.getY(), new Entity[]{epsilon}, 1);
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
            LocalRouting.apply(collectable, epsilon);
            collectable.setDeltaX(collectable.getDeltaX() * 10);
            collectable.setDeltaY(collectable.getDeltaY() * 10);
            collectable.setLayoutX(collectable.getLayoutX() + collectable.getDeltaX());
            collectable.setLayoutY(collectable.getLayoutY() + collectable.getDeltaY());
        }
        boolean hasCollected = false;
        for (int i = collectables.size() - 1; i >= 0; i--) {
            Collectable collectable = collectables.get(i);
            if (Entity.commonArea(collectable.getView(), epsilon.getView()) > 5) {
                xp += collectable.getXp();
                collectables.remove(i);
                root.getChildren().remove(collectable.getView());
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
            if (Entity.commonArea(epsilon.getView(), enemy.getView()) > 5) {
                epsilon.setHP(epsilon.getHP() - enemy.getAttackDamage());
                impact((epsilon.getLayoutX() + enemy.getLayoutX()) / 2, (epsilon.getLayoutY() + enemy.getLayoutY()) / 2, new Entity[]{enemy}, 1);
                isDamaged = true;
            }
        if (isDamaged) {
            epsilon.setColor(Color.RED);
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), e -> {
                epsilon.setColor(Color.WHITE);
            }));
            timeline.play();
            SoundController.DAMAGE.play();
        }
        if (epsilon.getHP() < 0) {
            GameController.endGame();
            GameOverStage gameOverStage = new GameOverStage();
            GameLauncher.stage = gameOverStage;
            gameOverStage.show();
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
                setYAndMoveEntities(getY() - 3 * bullet.getHP());
                hasShoot = true;
                bullet.setHP(0);
            }
            if (bullet.getLayoutX() <= 0) {
                setWidth(getWidth() + 3 * bullet.getHP());
                setXAndMoveEntities(getX() - 3 * bullet.getHP());
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
                root.getChildren().remove(bullets.get(i).getView());
                bullets.remove(i);
            }
    }
    public void damage(Enemy enemy, double HP) {
        enemy.setHP(enemy.getHP() - HP);
        if (enemy.getHP() <= 0) {
            enemies.remove(enemy);
            enemy.addCollectible(this);
            root.getChildren().remove(enemy.getView());
        }
    }
    public void moveWalls() {
        double minusWidth = Math.min((getWidth() - Constants.MINIMUM_WIDTH) / 2, 0.2);
        double minusHeight = Math.min((getHeight() - Constants.MINIMUM_HEIGHT) / 2, 0.2);
        setDimensions(getX() + minusWidth / 2, getY() + minusHeight / 2, getWidth() - 2 * minusWidth , getHeight() - 2 * minusHeight);
    }
    public void setEpsilon(Epsilon epsilon) {
        if (this.epsilon != null)
            ((Pane) this.getScene().getRoot()).getChildren().remove(this.epsilon);
        this.epsilon = epsilon;
        ((Pane) this.getScene().getRoot()).getChildren().add(this.epsilon.getView());
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
