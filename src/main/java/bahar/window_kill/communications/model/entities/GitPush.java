package bahar.window_kill.communications.model.entities;

import bahar.window_kill.communications.processors.util.strategies.attacks.SaveStrategy;
import bahar.window_kill.client.control.util.ImageUtil;
import bahar.window_kill.communications.model.Bounds;
import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class GitPush extends Entity {
    public GitPush(boolean isViewable, String id) {
        super(isViewable, id, (isViewable? makeView(): null), new Bounds(0, 0, 50, 50), GitPush.class.getName(),
                (int) 1E9 + 7, false, new SaveStrategy() {
            @Override
            public void onAct(Entity source) {
                source.setHP(0);
            }
        });
    }
    private static Node makeView() {
        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(50);
        rectangle.setHeight(50);
        rectangle.setArcWidth(10);
        rectangle.setArcHeight(10);
        ImagePattern imagePattern = new ImagePattern(ImageUtil.GIT_PUSH.getImage());
        rectangle.setFill(imagePattern);
        //
        FadeTransition fadeTransition = new FadeTransition(javafx.util.Duration.millis(2500), rectangle);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.1);
        fadeTransition.setCycleCount(FadeTransition.INDEFINITE);
        fadeTransition.setAutoReverse(true);
        fadeTransition.play();
        return rectangle;
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
    }

    @Override
    public void shout() {

    }
}
