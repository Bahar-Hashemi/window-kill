package bahar.window_kill.communications.processors.reader;

import bahar.window_kill.client.Main;
import bahar.window_kill.client.control.Constants;
import bahar.window_kill.client.control.GameController;
import bahar.window_kill.client.control.states.offlline.WhooshState;
import bahar.window_kill.client.view.MainStage;
import bahar.window_kill.client.view.PaneBuilder;
import bahar.window_kill.communications.model.Game;
import bahar.window_kill.communications.model.User;
import bahar.window_kill.communications.model.boards.MainBoard;
import bahar.window_kill.communications.model.entities.generators.shooters.Epsilon;
import bahar.window_kill.communications.processors.GameProcessor;
import bahar.window_kill.communications.util.GameUtil;
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
        System.out.println("fucking here!");
        if (user.getUsername().equals(GameController.user.getUsername())) {
            game.gameState.stop();
            if (isViewable) {
                Pane pane = PaneBuilder.GAME_OVER_PANE.generatePane();
                MainStage.add(pane);
            }
        }
        else {
            MainStage.remove(user.mainBoard.getView());
            game.users.remove(user);
        }
    }
    private void addUser(User user) {
        System.out.println("hello");
        User newUser = new User(new Epsilon(true, user.getEpsilon().getId(), 8), new MainBoard(true, user.mainBoard.getId()));
        newUser.setUsername(user.getUsername());
        game.addUser(newUser);
        newUser.mainBoard.add(newUser.epsilon.getView());
        newUser.mainBoard.setSize(1000, 1000);

        MainStage.add(newUser.mainBoard.getView());
////        //todo remove next two lines
//        newUser.mainBoard.setDimensions(Constants.SCREEN_WIDTH / 4, Constants.SCREEN_HEIGHT / 4,
//                Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT / 2);
//        newUser.epsilon.setLocation(Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT / 2);
//        if (user.getUsername().equals(GameController.user.getUsername())) {
////            newUser.mainBoard.setControlStrategy(GameController.user.settings.getControlStrategy());
////            newUser.mainBoard.requestUserControl(user);
////            newUser.setDevelopment(user.getDevelopment());
//            ((Circle) newUser.epsilon.getView()).setStroke(Color.ALICEBLUE);
//        }
//        System.out.println(newUser.mainBoard.getView());
//        System.out.println(newUser.epsilon.getView());
//        MainStage.add(newUser.mainBoard.getView());
//        game.addUser(newUser);
    }
}
