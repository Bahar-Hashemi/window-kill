package bahar.window_kill.model.entities.generators.shooters;

import bahar.window_kill.control.fazes.processors.strategies.strategies.Strategy;
import bahar.window_kill.model.boards.GameBoard;
import bahar.window_kill.model.entities.BoardOwner;
import bahar.window_kill.model.entities.Entity;
import javafx.scene.Node;

import java.util.ArrayList;

public class BlackOrb extends ShooterEntity implements BoardOwner {
    protected BlackOrb(Node view, int HP, boolean canImpact, Strategy strategy) {
        super(view, HP, canImpact, strategy);
    }

    @Override
    public void move(double targetX, double targetY) {

    }

    @Override
    public void aggress() {

    }

    @Override
    public void shout() {

    }

    @Override
    public Entity makeBullet() {
        return null;
    }

    @Override
    public void byBoard() {

    }

    @Override
    public GameBoard getBoard() {
        return null;
    }
}
