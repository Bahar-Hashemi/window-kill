package bahar.window_kill.model.entities.generators.shooters;

import bahar.window_kill.control.loader.ImageLoader;
import bahar.window_kill.control.loader.SoundLoader;
import bahar.window_kill.model.boards.GameBoard;
import bahar.window_kill.model.entities.BoardOwner;
import bahar.window_kill.model.entities.Collectable;
import bahar.window_kill.model.entities.Entity;
import bahar.window_kill.model.entities.LootDropper;
import bahar.window_kill.model.entities.attackers.AttackerEntity;
import bahar.window_kill.model.entities.attackers.Bullet;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class SmileyHand extends ShooterEntity implements LootDropper, BoardOwner, AttackerEntity {
    GameBoard gameBoard;
    boolean isLeftHand;
    int damage = 0;
    public SmileyHand(boolean isLeftHand) {
        super(makeView(isLeftHand), 300, true, null);
        this.isLeftHand = isLeftHand;
        byBoard();
        setBulletDamage(10);
    }
    private static Node makeView(boolean isLeftHand) {
        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(80);
        rectangle.setHeight(80);
        rectangle.setArcWidth(2);
        rectangle.setArcHeight(2);
        Image image = isLeftHand? ImageLoader.LEFT_HAND.getImage() : ImageLoader.RIGHT_HAND.getImage();
        ImagePattern imagePattern = new ImagePattern(image);
        rectangle.setFill(imagePattern);
        return rectangle;
    }
    @Override
    public void byBoard() {
        gameBoard = new GameBoard(true);
        gameBoard.lockSize(100, 100);
    }
    public void punch() {
        Image image = isLeftHand? ImageLoader.LEFT_PUNCH.getImage() : ImageLoader.RIGHT_PUNCH.getImage();
        ((Rectangle) view).setFill(new ImagePattern(image));
    }
    public void free() {
        Image image = isLeftHand? ImageLoader.LEFT_HAND.getImage() : ImageLoader.RIGHT_HAND.getImage();
        ((Rectangle) view).setFill(new ImagePattern(image));
    }
    @Override
    public GameBoard getBoard() {
        return gameBoard;
    }

    @Override
    public void move(double targetX, double targetY) {

    }

    @Override
    public void aggress() {
        if (strategy != null)
            strategy.act(this);
    }

    @Override
    public void shout() {
        SoundLoader.HIT.play();
    }

    @Override
    public int getLootCount() {
        return 10;
    }

    @Override
    public Entity makeLoot() {
        return new Collectable(30, Color.YELLOW);
    }
    public void setSceneX(double x) {
        super.setSceneX(x);
        gameBoard.setLayoutX(x - 10);
    }
    public void setSceneY(double y) {
        super.setSceneY(y);
        gameBoard.setLayoutY(y - 10);
    }

    @Override
    public Entity makeBullet() {
        return new Bullet(getBulletDamage(), 5, Color.YELLOW, getBulletDamage(), gunDirectionX, gunDirectionY, this, true);
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public void setDamage(int bulletDamage) {
        this.damage = bulletDamage;
    }
}
