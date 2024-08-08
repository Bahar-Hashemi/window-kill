package bahar.window_kill.communications.model.entities.generators.shooters;

import bahar.window_kill.communications.model.GameElement;
import bahar.window_kill.communications.model.entities.additional.data.WyrmData;
import bahar.window_kill.communications.processors.util.strategies.attacks.SpawnStrategy;
import bahar.window_kill.communications.util.GameUtil;
import bahar.window_kill.client.control.util.SoundUtil;
import bahar.window_kill.communications.model.Bounds;
import bahar.window_kill.communications.model.Watch;
import bahar.window_kill.communications.model.boards.GameBoard;
import bahar.window_kill.communications.model.entities.BoardOwner;
import bahar.window_kill.communications.model.entities.Collectable;
import bahar.window_kill.communications.model.entities.Entity;
import bahar.window_kill.communications.model.entities.LootDropper;
import bahar.window_kill.communications.model.entities.attackers.Bullet;
import com.google.gson.Gson;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

public class Wyrm extends ShooterEntity implements BoardOwner, LootDropper  {
    GameBoard gameBoard;
    transient Circle iris;
    public Wyrm(boolean isViewable, String id) {
        super(isViewable, id, (isViewable? makeView(): null), new Bounds(-20, -35, 30, 5), Wyrm.class.getName(),
                30, true, new SpawnStrategy(1000));
        if (isViewable)
            makeIris();
        additionalData = new Gson().toJson(new WyrmData(0, 1));
        byBoard();
        setBulletDamage(5);
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
    public void target(Entity target) {
        super.target(target);
        double dx = target.getX() - getX();
        double dy = target.getY() - getY();
        double chord = Math.sqrt(dx * dx + dy * dy);
        additionalData = new Gson().toJson(new WyrmData(dx / chord, dy / chord));
    }

    @Override
    public void byBoard() {
        gameBoard = new GameBoard(isViewable, GameUtil.generateID(), true);
        gameBoard.lockBoardSize(90, 70);
    }
    @Override
    public GameBoard getBoard() {
        return gameBoard;
    }

    @Override
    public void move() {
    }

    @Override
    public void aggress() {
        strategy.act(this, game);
    }

    @Override
    public void morph() {
        super.morph();
        if (!isViewable)
            return;
        iris.setLayoutX(0);
        iris.setLayoutY(0);
        iris.setLayoutX(iris.getLayoutX() + gunDirectionX * 10);
        iris.setLayoutY(iris.getLayoutY() + gunDirectionY * 5);
    }

    @Override
    public void shout() {
        if (isViewable)
            SoundUtil.HIT.play();
    }
    public void setX(double x) {
        super.setX(x);
        if (gameBoard != null)
            gameBoard.setLayoutX(x - 35);
    }
    public void setY(double y) {
        super.setY(y);
        if (gameBoard != null)
            gameBoard.setLayoutY(y - 50);
    }
    @Override
    public Entity makeBullet() {
        return new Bullet(isViewable, GameUtil.generateID(), getBulletDamage(), 5, Color.CRIMSON, getBulletDamage(), gunDirectionX, gunDirectionY, true);
    }

    @Override
    public int getLootCount() {
        return 2;
    }

    @Override
    public void readFrom(GameElement gameElement) {
        super.readFrom(gameElement);
        WyrmData wyrmData = new Gson().fromJson(gameElement.getAdditionalData(), WyrmData.class);
        gunDirectionX = wyrmData.getGunDirectionX();
        gunDirectionY = wyrmData.getGunDirectionY();
    }

    @Override
    public Entity makeLoot() {
        return new Collectable(isViewable, GameUtil.generateID(), 8, Color.CRIMSON);
    }
}
