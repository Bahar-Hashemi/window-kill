package bahar.window_kill.communications.processors.reader;

import bahar.window_kill.client.control.GameController;
import bahar.window_kill.client.view.MainStage;
import bahar.window_kill.client.view.PaneBuilder;
import bahar.window_kill.communications.model.Game;
import bahar.window_kill.communications.model.User;
import bahar.window_kill.communications.model.boards.MainBoard;
import bahar.window_kill.communications.model.entities.generators.shooters.Epsilon;
import bahar.window_kill.communications.processors.GameProcessor;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class OnlineUserProcessor extends GameProcessor {
    public OnlineUserProcessor(boolean isViewable, Game game) {
        super(isViewable, game);
    }

    @Override
    public void run() {
        if (game.save == null)
            return;
        int myPointer = 0;
        int savePointer = 0;
        while (myPointer < game.users.size() && savePointer < game.save.users.size()) {
            if (game.users.get(myPointer).getUsername().equals(game.save.users.get(savePointer).getUsername())) {
                myPointer++;
                savePointer++;
            }
            else
                removeUser(game.users.get(myPointer));
        }
        while (myPointer < game.users.size())
            removeUser(game.users.get(myPointer));
        while (savePointer < game.save.users.size())
            addUser(game.save.users.get(savePointer++));
    }
    private void removeUser(User user) {
        if (user.getUsername().equals(GameController.user.getUsername())) {
            game.gameState.stop();
            if (isViewable) {
                Pane pane = PaneBuilder.GAME_OVER_PANE.generatePane();
                MainStage.add(pane);
            }
        }
        else {
            MainStage.remove(user.mainBoard.getView());
            game.removeUser(user);
        }
    }
    private void addUser(User user) {
        User newUser = new User(new Epsilon(true, user.getEpsilon().getId(), 8), new MainBoard(true, user.mainBoard.getId()));
        newUser.setUsername(user.getUsername());
        game.addUser(newUser);
        newUser.mainBoard.add(newUser.epsilon.getView());
        MainStage.add(newUser.mainBoard.getView());
        if (user.getUsername().equals(GameController.user.getUsername())) {
            newUser.mainBoard.setControlStrategy(GameController.user.settings.getControlStrategy());
            newUser.mainBoard.requestUserControl(newUser);
            newUser.setDevelopment(user.getDevelopment());
            ((Circle) newUser.epsilon.getView()).setStroke(Color.LIGHTSKYBLUE);
            GameController.user = newUser;
        }
    }
}
