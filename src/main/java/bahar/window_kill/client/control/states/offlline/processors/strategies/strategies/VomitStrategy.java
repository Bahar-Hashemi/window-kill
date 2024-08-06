package bahar.window_kill.client.control.states.offlline.processors.strategies.strategies;

import bahar.window_kill.client.control.util.GameUtil;
import bahar.window_kill.client.model.Game;
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
        watch = new Watch(spawnDuration) {
            @Override
            protected void onCall() {
                super.onCall();
                hasBullet = true;
            }
        };
    }
    @Override
    public void act(Entity aggressionSource, Game game) {
        watch.call(game.clock);
        if (!hasBullet)
            return;
        Entity bullet = new Vomit(GameUtil.generateID());
        int index = new Random().nextInt(game.users.size());
        MainBoard mainBoard = game.users.get(index).mainBoard;
        mainBoard.add(bullet.getView());
        game.addEntity(bullet);
        Random random = new Random();
        bullet.setLocation(random.nextDouble(mainBoard.getX(), mainBoard.getX() + mainBoard.getWidth()),
                random.nextDouble(mainBoard.getY(), mainBoard.getY() + mainBoard.getHeight()));
        SoundUtil.ENEMY_SHOOT.play();
        onAct(aggressionSource); hasBullet = false;
    }
}
