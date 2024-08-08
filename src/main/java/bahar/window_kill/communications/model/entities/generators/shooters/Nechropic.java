package bahar.window_kill.communications.model.entities.generators.shooters;

import bahar.window_kill.communications.model.GameElement;
import bahar.window_kill.communications.model.entities.additional.data.NechropicData;
import bahar.window_kill.communications.processors.util.strategies.attacks.SpawnStrategy;
import bahar.window_kill.communications.util.GameUtil;
import bahar.window_kill.client.control.util.SoundUtil;
import bahar.window_kill.communications.model.Bounds;
import bahar.window_kill.communications.model.User;
import bahar.window_kill.communications.model.Watch;
import bahar.window_kill.communications.model.entities.Collectable;
import bahar.window_kill.communications.model.entities.Entity;
import bahar.window_kill.communications.model.entities.LootDropper;
import bahar.window_kill.communications.model.entities.attackers.Bullet;
import com.google.gson.Gson;
import javafx.animation.*;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;

import java.util.Random;

public class Nechropic extends ShooterEntity implements LootDropper {
    private int statePointer = 0;
    boolean canAct = true;
    Watch stateWatch;
    public Nechropic(boolean isViewable, String id) {
        super(isViewable, id, (isViewable? makeView(): null), new Bounds(-10, -10, 10, 10), Nechropic.class.getName(),
                10, true, new SpawnStrategy(15));
        additionalData = new Gson().toJson(new NechropicData(statePointer));
        makeWatches();
        setBulletDamage(5);
    }
    private void makeWatches() {
        stateWatch = new Watch(500) {
            @Override
            protected void onCall() {
                super.onCall();
                statePointer = (statePointer + 1) % 24; canAct = true;
                additionalData = new Gson().toJson(new NechropicData(statePointer));
            }
        };
    }

    @Override
    public void readFrom(GameElement gameElement) {
        super.readFrom(gameElement);
        NechropicData nechropicData = new Gson().fromJson(gameElement.getAdditionalData(), NechropicData.class);
        statePointer = nechropicData.getState();
    }

    private static Node makeView() {
        Polygon polygon = new Polygon(
                -2, -10,
                6, -10,
                6, -6,
                2, -6,
                2, -2,
                10, -2,
                10, 6,
                6, 6,
                6, 2,
                2, 2,
                2, 10,
                -6, 10,
                -6, 6,
                -2, 6,
                -2, 2,
                -10, 2,
                -10, -6,
                -6, -6,
                -6, -2,
                -2, -2
        );
        polygon.setFill(Color.web("#640000"));
        polygon.setStrokeWidth(0);
        polygon.setOpacity(0);
        return polygon;
    }

    @Override
    public void move() {
        stateWatch.call(game.clock);
        User user = GameUtil.findMyUser(this, game);
        double targetX = user.getEpsilon().getX();
        double targetY = user.getEpsilon().getY();
        if (statePointer == 0 && canAct) {
            ascent(targetX, targetY);
            canAct = false;
        }
        if (statePointer == 20 && canAct) {
            canAct = false;
        }
    }
    @Override
    public void aggress() {
        stateWatch.call(game.clock);
        if (canAct && statePointer > 3 && statePointer < 20) {
            strategy.act(this, game);
            if (isViewable)
                SoundUtil.ENEMY_SHOOT.play();
            canAct = false;
        }
    }

    @Override
    public void morph() {
        super.morph();
        if (statePointer == 0 && canAct)
            AscentMorph();
        if (statePointer == 20 && canAct)
            descentMorph();
    }

    @Override
    public void shout() {
        if (isViewable)
            SoundUtil.HIT.play();
    }

    @Override
    public Entity makeBullet() {
        return new Bullet(isViewable, GameUtil.generateID(), getBulletDamage(), 3, Color.web("#640000"), getBulletDamage(), gunDirectionX, gunDirectionY, true);
    }
    private void ascent(double targetX, double targetY) {
        //
        double chord = 75;
        double dx = (new Random().nextDouble(-1 * chord, chord));
        double dy = (new Random().nextBoolean()? 1: -1) * (Math.sqrt(chord * chord - dx * dx));
        setX(targetX + dx);
        setY(targetY + dy);
    }
    private void AscentMorph() {
        if (isViewable) {
            RotateTransition rotateTransition = new RotateTransition(Duration.seconds(0.5), view);
            rotateTransition.setCycleCount(4);
            rotateTransition.setByAngle(360);
            rotateTransition.setInterpolator(Interpolator.LINEAR);
            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2), view);
            fadeTransition.setFromValue(0);
            fadeTransition.setToValue(1);
            SoundUtil.DRILL.play();
            rotateTransition.play();
            fadeTransition.play();
        }
    }
    private void descentMorph() {
        if (isViewable) {
            RotateTransition rotateTransition = new RotateTransition(Duration.seconds(0.5), view);
            rotateTransition.setCycleCount(4);
            rotateTransition.setByAngle(360);
            rotateTransition.setFromAngle(360); rotateTransition.setToAngle(0);
            rotateTransition.setInterpolator(Interpolator.LINEAR);
            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2), view);
            fadeTransition.setFromValue(1);
            fadeTransition.setToValue(0);
            rotateTransition.play();
            fadeTransition.play();
            SoundUtil.DRILL.play();
        }
    }

    @Override
    public int getLootCount() {
        return 4;
    }

    @Override
    public Entity makeLoot() {
        return new Collectable(isViewable, GameUtil.generateID(), 2, Color.web("#640000"));
    }
}
