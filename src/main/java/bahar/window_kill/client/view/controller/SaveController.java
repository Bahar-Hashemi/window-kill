package bahar.window_kill.client.view.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class SaveController {
    public Label xpLabel, savingLabel, notSavingLabel;
    public Button saveButton, notSaveButton;
    public VBox root;
    public void initialize() {
//        xpLabel.setText("XP: " + user.getEpsilon().getXp());
//        savingLabel.setText("Saving Price: " + getPR());
//        notSavingLabel.setText("Not Saving XP: " + (getPR() / 5));
//        if (user.getEpsilon().getXp() < getPR())
//            saveButton.setDisable(true);
//        root.setLayoutX(Constants.SCREEN_WIDTH / 6);
//        root.setLayoutY(Constants.SCREEN_HEIGHT / 6);
    }

    public void onSave(ActionEvent actionEvent) {
//        user.getEpsilon().setXp(user.getEpsilon().getXp() - getPR());
//        user.getEpsilon().setHP(user.getEpsilon().getHP() + 10);
//        save = (new SaveUtil()).write();
//        GameController.setGameState(new RestartingState());
    }

    public void onNotSave(ActionEvent actionEvent) {
//        user.getEpsilon().setXp(user.getEpsilon().getXp() + getPR() / 5);
//        GameController.setGameState(new RestartingState());
    }
}
