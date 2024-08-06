package bahar.window_kill.client.control.states.offlline.processors.spawners;

import bahar.window_kill.client.control.util.GameUtil;
import bahar.window_kill.client.model.Game;
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
        gitPush = new GitPush(GameUtil.generateID());
        addEntity(gitPush, game);
        gitPush.setY(new Random().nextDouble(0, game.users.get(0).mainBoard.getHeight()));
        gitPush.setX(new Random().nextDouble(0, game.users.get(0).mainBoard.getWidth()));
    }
}
