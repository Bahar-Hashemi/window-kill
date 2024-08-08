package bahar.window_kill.communications.processors.reader;

import bahar.window_kill.communications.model.Game;
import bahar.window_kill.communications.model.User;
import bahar.window_kill.communications.model.boards.MainBoard;
import bahar.window_kill.communications.processors.GameProcessor;

public class OnlineChangeProcessor extends GameProcessor {
    public OnlineChangeProcessor(boolean isViewable, Game game) {
        super(isViewable, game);
    }

    @Override
    public void run() {
        if (game.save == null)
            return;
        makeUsers();
        makeEntities();
        makeMainBoars();
    }
    private void makeUsers() {
        for (int i = 0; i < game.users.size(); i++) {
            User myUser = game.users.get(i);
            User goalUser = game.save.users.get(i);
            myUser.readFrom(goalUser);
        }
    }
    private void makeEntities() {
        for (int i = 0; i < game.entities.size(); i++) {
            if (game.entities.get(i).getHP() > game.save.entities.get(i).getHP()) {
                game.entities.get(i).shout();
                game.entities.get(i).setHP(game.save.entities.get(i).getHP());
            }
            game.entities.get(i).readFrom(game.save.entities.get(i));
        }
    }
    private void makeMainBoars() {
        for (User user: game.users) {
            MainBoard mainBoard = user.mainBoard;
            mainBoard.setHP((int) user.getEpsilon().getHP());
            mainBoard.setXP(user.getXp());
            mainBoard.setWave(game.wave);
        }
    }
}
