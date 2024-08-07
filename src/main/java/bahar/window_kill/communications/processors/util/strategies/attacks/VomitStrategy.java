package bahar.window_kill.communications.processors.util.strategies.attacks;

import bahar.window_kill.communications.util.GameUtil;
import bahar.window_kill.communications.model.Game;
import bahar.window_kill.client.control.util.SoundUtil;
import bahar.window_kill.communications.model.Watch;
import bahar.window_kill.communications.model.boards.MainBoard;
import bahar.window_kill.communications.model.entities.Entity;
import bahar.window_kill.communications.model.entities.attackers.Vomit;

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
        Entity bullet = new Vomit(aggressionSource.isViewable, GameUtil.generateID());
        int index = new Random().nextInt(game.users.size());
        MainBoard mainBoard = game.users.get(index).mainBoard;
        mainBoard.add(bullet.getView());
        game.addEntity(bullet);
        Random random = new Random();
        bullet.setLocation(random.nextDouble(mainBoard.getBounds().getMinimumX(), mainBoard.getBounds().getMaximumX()),
                random.nextDouble(mainBoard.getBounds().getMinimumY(), mainBoard.getBounds().getMaximumY()));
        SoundUtil.ENEMY_SHOOT.play();
        onAct(aggressionSource); hasBullet = false;
    }
}
