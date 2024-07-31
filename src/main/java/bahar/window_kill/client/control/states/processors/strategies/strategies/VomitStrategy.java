package bahar.window_kill.client.control.states.processors.strategies.strategies;

import bahar.window_kill.client.model.Deck;
import bahar.window_kill.client.control.util.SoundUtil;
import bahar.window_kill.client.model.Watch;
import bahar.window_kill.client.model.boards.MainBoard;
import bahar.window_kill.client.model.entities.Entity;
import bahar.window_kill.client.model.entities.attackers.Vomit;

import java.util.Random;

public class VomitStrategy extends Strategy {
    boolean hasBullet = false;
    Watch watch;
    public VomitStrategy(int spawnDuration) {
        watch = new Watch(spawnDuration, () -> hasBullet = true);
    }
    @Override
    public void act(Entity aggressionSource, Deck deck) {
        watch.call(deck.clock);
        if (!hasBullet)
            return;
        Entity bullet = new Vomit();
        int index = new Random().nextInt(deck.users.size());
        MainBoard mainBoard = deck.users.get(index).mainBoard;
        mainBoard.getChildren().add(bullet.getView());
        deck.addEntity(bullet);
        Random random = new Random();
        bullet.setSceneLocation(random.nextDouble(mainBoard.getLayoutX(), mainBoard.getLayoutX() + mainBoard.getWidth()),
                random.nextDouble(mainBoard.getLayoutY(), mainBoard.getLayoutY() + mainBoard.getHeight()));
        SoundUtil.ENEMY_SHOOT.play();
        onAct(aggressionSource); hasBullet = false;
    }
}
