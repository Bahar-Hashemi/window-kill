package bahar.window_kill.communications.processors.reader;

import bahar.window_kill.communications.model.Game;
import bahar.window_kill.communications.model.User;
import bahar.window_kill.communications.processors.GameProcessor;

public class OnlineChangeProcessor extends GameProcessor {
    public OnlineChangeProcessor(boolean isViewable, Game game) {
        super(isViewable, game);
    }

    @Override
    public void run() {
        makeUsers();
        makeEntities();
    }
    private void makeUsers() {
        for (int i = 0; i < game.users.size(); i++) {
            User myUser = game.users.get(i);
            User goalUser = game.save.users.get(i);
            myUser.readFrom(goalUser);
        }
    }
    private void makeEntities() {

    }
}
