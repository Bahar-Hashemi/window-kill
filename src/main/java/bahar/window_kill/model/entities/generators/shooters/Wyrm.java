package bahar.window_kill.model.entities.generators.shooters;

import bahar.window_kill.control.fazes.processors.strategies.strategies.SpawnStrategy;
import bahar.window_kill.control.loader.SoundLoader;
import bahar.window_kill.model.Watch;
import bahar.window_kill.model.boards.GameBoard;
import bahar.window_kill.model.entities.BoardOwner;
import bahar.window_kill.model.entities.Collectable;
import bahar.window_kill.model.entities.Entity;
import bahar.window_kill.model.entities.LootDropper;
import bahar.window_kill.model.entities.attackers.Bullet;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

import java.util.ArrayList;
import java.util.Collections;

public class Wyrm extends ShooterEntity implements BoardOwner, LootDropper  {
    Watch gunWatch;
    GameBoard gameBoard;
    Circle iris;
    public Wyrm() {
        super(makeView(), 12, true, new SpawnStrategy());
        makeIris();
        gunWatch = new Watch(1000, event -> {
            strategy.act(this);
            SoundLoader.ENEMY_SHOOT.play();
        });
        byBoard();
    }
    private void makeIris() {
        // Adjust iris to be centered in the wider eye
        iris = new Circle(10, -15, 10, Color.CRIMSON); // Adjusted position for centered iris
        ((Group) getView()).getChildren().add(iris);
        iris.toBack();
    }
    private static Node makeView() {
        Path eyeOutline = new Path();
        MoveTo moveTo = new MoveTo(-20, -15);
        CubicCurveTo upperEyelid = new CubicCurveTo(-10, -35, 30, -35, 40, -15);
        CubicCurveTo lowerEyelid = new CubicCurveTo(30, 5, -10, 5, -20, -15);

        eyeOutline.getElements().add(moveTo);
        eyeOutline.getElements().addAll(upperEyelid, lowerEyelid);
        eyeOutline.setStroke(Color.CRIMSON);
        eyeOutline.setStrokeWidth(2.5);
        eyeOutline.setFill(Color.TRANSPARENT);

        Group eye = new Group();
        eye.getChildren().addAll(eyeOutline);
        return eye;
    }

    @Override
    public void byBoard() {
        gameBoard = new GameBoard(true);
        gameBoard.lockSize(90, 70);
    }
    @Override
    public GameBoard getBoard() {
        return gameBoard;
    }

    @Override
    public void move(double targetX, double targetY) {
        iris.setLayoutX(0);
        iris.setLayoutY(0);
        double dx = targetX - iris.getLayoutX() - getView().getLayoutX();
        double dy = targetY - iris.getLayoutY() - getView().getLayoutY();
        double chord = Math.sqrt(dx * dx + dy * dy);
        iris.setLayoutX(iris.getLayoutX() + dx / chord * 10);
        iris.setLayoutY(iris.getLayoutY() + dy / chord * 5);
    }

    @Override
    public void aggress() {
        gunWatch.call();
    }

    @Override
    public void shout() {

    }
    public void setSceneX(double x) {
        super.setSceneX(x);
        if (gameBoard != null)
            gameBoard.setLayoutX(x - 35);
    }
    public void setSceneY(double y) {
        super.setSceneY(y);
        if (gameBoard != null)
            gameBoard.setLayoutY(y - 50);
    }
    @Override
    public Entity makeBullet() {
        return new Bullet(5, 5, Color.CRIMSON, 5, gunDirectionX, gunDirectionY, this, true);
    }

    @Override
    public int getLootCount() {
        return 2;
    }

    @Override
    public Entity makeLoot() {
        return new Collectable(8, Color.CRIMSON);
    }
}
