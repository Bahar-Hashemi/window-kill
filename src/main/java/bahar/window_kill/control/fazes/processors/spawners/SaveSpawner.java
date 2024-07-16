package bahar.window_kill.control.fazes.processors.spawners;

import bahar.window_kill.model.entities.GitPush;

import java.util.Random;

import static bahar.window_kill.control.Deck.*;

public class SaveSpawner extends Spawner {
    GitPush gitPush;
    public SaveSpawner() {
        super(1000, () -> {});
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
        addEntity(gitPush);
        gitPush.setLayoutY(new Random().nextDouble(0, mainBoard.getHeight()));
        gitPush.setLayoutX(new Random().nextDouble(0, mainBoard.getWidth()));
    }
}
