package bahar.window_kill.communications.processors.reader;

import bahar.window_kill.client.view.MainStage;
import bahar.window_kill.client.view.PaneBuilder;
import bahar.window_kill.communications.model.Game;
import bahar.window_kill.communications.model.User;
import bahar.window_kill.communications.model.boards.MainBoard;
import bahar.window_kill.communications.processors.GameProcessor;
import javafx.scene.layout.Pane;

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
        makeMainBoards();
        checkGameEnded();
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
    private void makeMainBoards() {
        for (User user: game.users) {
            MainBoard mainBoard = user.mainBoard;
            mainBoard.setHP((int) user.getEpsilon().getHP());
            mainBoard.setXP(user.getXp());
            mainBoard.setWave(game.wave);
            StringBuilder abilitiesString = new StringBuilder();
            if (user.coolDown / 1000 > 0)
                abilitiesString.append("cool down: ").append(user.coolDown / 1000).append("s\n");
            for (int i = 0; i < user.abilities.size(); i++)
                abilitiesString.append(user.abilities.get(i).getType()).append("\n");
            mainBoard.setAbilities(abilitiesString.toString());
        }
    }
    private void checkGameEnded() {
        for (User user: game.users)
            if (user.epsilon.getHP() <= 0) {
                game.gameState.stop();
                Pane pane = PaneBuilder.ONLINE_GAME_OVER_PANE.generatePane();
                MainStage.add(pane);
            }
    }
}
