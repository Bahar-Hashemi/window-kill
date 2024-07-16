package bahar.window_kill.view.controller;

import bahar.window_kill.control.Constants;
import bahar.window_kill.control.Deck;
import bahar.window_kill.control.GameController;
import bahar.window_kill.control.fazes.GameState;
import bahar.window_kill.control.fazes.RestartingState;
import bahar.window_kill.control.util.reader.SaveUtil;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import static bahar.window_kill.control.Deck.*;

public class SaveController {
    public Label xpLabel, savingLabel, notSavingLabel;
    public Button saveButton, notSaveButton;
    public VBox root;
    public void initialize() {
        xpLabel.setText("XP: " + user.getEpsilon().getXp());
        savingLabel.setText("Saving Price: " + getPR());
        notSavingLabel.setText("Not Saving XP: " + (getPR() / 10));
        if (user.getEpsilon().getXp() < getPR())
            saveButton.setDisable(true);
        root.setLayoutX(Constants.SCREEN_WIDTH / 6);
        root.setLayoutY(Constants.SCREEN_HEIGHT / 6);
    }

    public void onSave(ActionEvent actionEvent) {
        user.getEpsilon().setHP(user.getEpsilon().getHP() + 10);
        user.getEpsilon().setXp(user.getEpsilon().getXp() - getPR());
        save = (new SaveUtil()).write();
        GameController.setGameState(new RestartingState());
    }

    public void onNotSave(ActionEvent actionEvent) {
        user.getEpsilon().setXp(user.getEpsilon().getXp() + getPR() / 10);
        GameController.setGameState(new RestartingState());
    }
}
