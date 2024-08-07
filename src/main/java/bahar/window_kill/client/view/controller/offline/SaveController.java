package bahar.window_kill.client.view.controller.offline;

import bahar.window_kill.communications.util.Constants;
import bahar.window_kill.client.control.GameController;
import bahar.window_kill.client.control.states.offlline.RestartingState;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class SaveController {
    public Label xpLabel, savingLabel, notSavingLabel;
    public Button saveButton, notSaveButton;
    public VBox root;
    public void initialize() {
        xpLabel.setText("XP: " + GameController.user.getXp());
        savingLabel.setText("Saving Price: " + GameController.game.getPR());
        notSavingLabel.setText("Not Saving XP: " + (GameController.game.getPR() / 5));
        if (GameController.user.getXp() < GameController.game.getPR())
            saveButton.setDisable(true);
        root.setLayoutX(Constants.SCREEN_WIDTH / 6);
        root.setLayoutY(Constants.SCREEN_HEIGHT / 6);
    }

    public void onSave(ActionEvent actionEvent) { //todo complete onSave
        GameController.user.setXp(GameController.user.getXp() - GameController.game.getPR());
        GameController.user.getEpsilon().setHP(GameController.user.getEpsilon().getHP() + 10);
//        GameController.game.save = (new SaveUtil()).write(GameController.game);
        GameController.game.setGameState(new RestartingState(true, GameController.game));
    }

    public void onNotSave(ActionEvent actionEvent) {
        GameController.user.setXp(GameController.user.getXp() + GameController.game.getPR() / 5);
        GameController.game.setGameState(new RestartingState(true, GameController.game));
    }
}
