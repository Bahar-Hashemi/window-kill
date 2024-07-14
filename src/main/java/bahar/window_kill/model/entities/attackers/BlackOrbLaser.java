package bahar.window_kill.model.entities.attackers;

import bahar.window_kill.control.GameUtil;
import bahar.window_kill.control.fazes.processors.strategies.strategies.DamageStrategy;
import bahar.window_kill.control.fazes.processors.strategies.strategies.Strategy;
import bahar.window_kill.model.entities.Barricados;
import bahar.window_kill.model.entities.BlackOrb;
import bahar.window_kill.model.entities.Entity;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class BlackOrbLaser extends Entity implements AttackerEntity {
    private final BlackOrb terminal1, terminal2;
    private final Node terminal1View, terminal2View;
    private static final int INF = (int) 1E9 + 7;
    private int damage = 12;
    public BlackOrbLaser(BlackOrb terminal1, BlackOrb terminal2) {
        super(makeView(terminal1, terminal2), INF, true, new DamageStrategy());
        this.terminal1 = terminal1;
        this.terminal2 = terminal2;
        //در اینجا مقادیری تف زدیم
        terminal1View = makeView(terminal1, terminal2);
        terminal2View = makeView(terminal1, terminal2);
        terminal1.getBoard().getChildren().add(terminal1View);
        GameUtil.setSceneLocation(terminal1View, terminal1.getSceneX(), terminal1.getSceneY());
        terminal2.getBoard().getChildren().add(terminal2View);
        GameUtil.setSceneLocation(terminal2View, terminal1.getSceneX(), terminal1.getSceneY());
    }
    private static Node makeView(BlackOrb terminal1, BlackOrb terminal2) {
        // Assuming getSceneX() and getSceneY() methods exist in BlackOrb
        double x1 = terminal1.getSceneX();
        double y1 = terminal1.getSceneY();
        double x2 = terminal2.getSceneX();
        double y2 = terminal2.getSceneY();

        Line line = new Line(0, 0, x2 - x1, y2 - y1);
        line.setStroke(Color.LIGHTBLUE);
        line.setStrokeWidth(20);

        return line;
    }
    @Override
    public void move(double targetX, double targetY) {
    }

    @Override
    public void aggress() {
        if (terminal1.getHP() <= 0 || terminal2.getHP() <= 0) {
            setHP(0);
            terminal1.getBoard().getChildren().remove(terminal1View);
            terminal2.getBoard().getChildren().remove(terminal2View);
        }
        strategy.act(this);
    }

    @Override
    public void shout() {
        //todo add laser sound
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public void setDamage(int damage) {
        this.damage = damage;
    }
}
