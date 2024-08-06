package bahar.window_kill.client.view.controller;

import bahar.window_kill.client.control.Constants;
import bahar.window_kill.client.control.states.offlline.RestartingState;
import bahar.window_kill.client.control.util.reader.SaveUtil;
import bahar.window_kill.client.model.Game;
import bahar.window_kill.client.model.User;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class SaveController {
    public Label xpLabel, savingLabel, notSavingLabel;
    public Button saveButton, notSaveButton;
    public VBox root;
    public void initialize() {
        xpLabel.setText("XP: " + User.getInstance().getXp());
        savingLabel.setText("Saving Price: " + Game.getInstance().getPR());
        notSavingLabel.setText("Not Saving XP: " + (Game.getInstance().getPR() / 5));
        if (User.getInstance().getXp() < Game.getInstance().getPR())
            saveButton.setDisable(true);
        root.setLayoutX(Constants.SCREEN_WIDTH / 6);
        root.setLayoutY(Constants.SCREEN_HEIGHT / 6);
    }

    public void onSave(ActionEvent actionEvent) {
        User.getInstance().setXp(User.getInstance().getXp() - Game.getInstance().getPR());
        User.getInstance().getEpsilon().setHP(User.getInstance().getEpsilon().getHP() + 10);
        Game.getInstance().save = (new SaveUtil()).write(Game.getInstance());
        Game.getInstance().setGameState(new RestartingState(Game.getInstance()));
    }

    public void onNotSave(ActionEvent actionEvent) {
        User.getInstance().setXp(User.getInstance().getXp() + Game.getInstance().getPR() / 5);
        Game.getInstance().setGameState(new RestartingState(Game.getInstance()));
    }
}
