package bahar.window_kill.communications.processors;

import bahar.window_kill.communications.model.Game;
import bahar.window_kill.communications.model.entities.Entity;

public class ViewProcessor extends GameProcessor {
    public ViewProcessor(boolean isViewable, Game game) {
        super(isViewable, game);
    }

    @Override
    public void run() {
        if(!isViewable) return;
        for (Entity entity: game.entities)
            entity.morph();
    }
}
