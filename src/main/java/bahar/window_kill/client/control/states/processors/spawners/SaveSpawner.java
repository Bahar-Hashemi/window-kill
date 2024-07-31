package bahar.window_kill.client.control.states.processors.spawners;

import bahar.window_kill.client.model.Deck;
import bahar.window_kill.client.model.entities.GitPush;

import java.util.Random;

public class SaveSpawner extends Spawner {
    GitPush gitPush;
    public SaveSpawner(Deck deck) {
        super(1000, () -> {}, deck);
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
        addEntity(gitPush, deck);
        gitPush.setLayoutY(new Random().nextDouble(0, deck.users.get(0).mainBoard.getHeight()));
        gitPush.setLayoutX(new Random().nextDouble(0, deck.users.get(0).mainBoard.getWidth()));
    }
}
