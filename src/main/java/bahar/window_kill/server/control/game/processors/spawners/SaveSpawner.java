package bahar.window_kill.server.control.game.processors.spawners;

import bahar.window_kill.server.model.Game;
import bahar.window_kill.client.model.entities.GitPush;

import java.util.Random;

public class SaveSpawner extends Spawner {
    GitPush gitPush;
    public SaveSpawner(Game game) {
        super(1000, game);
        setCycleCount(20);
    }

    @Override
    protected void onEnd() {
        super.onEnd();
        gitPush.setHP(0);
    }

    @Override
    protected void onStart() {
        super.onStart();
        gitPush = new GitPush();
        addEntity(gitPush, game);
        gitPush.setLayoutY(new Random().nextDouble(0, game.users.get(0).mainBoard.getHeight()));
        gitPush.setLayoutX(new Random().nextDouble(0, game.users.get(0).mainBoard.getWidth()));
    }
}
