package bahar.window_kill.control;

import bahar.window_kill.control.fazes.processors.abilities.HealWatch;
import bahar.window_kill.control.fazes.processors.BoardsProcessor;
import bahar.window_kill.control.loader.ImageLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;

import static bahar.window_kill.control.Deck.*;

public class PauseMenuController {
    @FXML
    public Label HPLabel, XPLabel, volumeLabel;
    @FXML
    public Button healButton, banishButton, empowerButton, dismayButton, slumberButton, thunderButton;
    public Slider volumeSlider;
    @FXML
    public ImageView healImage, banishImage, empowerImage, dismayImage, slumberImage, thunderImage;

    @FXML
    public void initialize() {
        makeOnHovers();
        updateLabels();
        updateVolume();
        processButtonsDisable();
    }
    private void makeOnHovers() {
        makeButton(healButton, healImage, ImageLoader.HEAL, ImageLoader.HEAL_HOVER);
        makeButton(banishButton, banishImage, ImageLoader.BANISH, ImageLoader.BANISH_HOVER);
        makeButton(empowerButton, empowerImage, ImageLoader.EMPOWER, ImageLoader.EMPOWER_HOVER);
        makeButton(dismayButton, dismayImage, ImageLoader.DISMAY, ImageLoader.DISMAY_HOVER);
        makeButton(slumberButton, slumberImage, ImageLoader.SLUMBER, ImageLoader.SLUMBER_HOVER);
        makeButton(thunderButton, thunderImage, ImageLoader.THUNDER, ImageLoader.THUNDER_HOVER);
    }
    private void makeButton(Button button, ImageView buttonImage, ImageLoader image, ImageLoader onHover) {
        button.setOnMouseEntered(e -> buttonImage.setImage(onHover.getImage()));
        button.setOnMouseExited(e -> buttonImage.setImage(image.getImage()));
    }
    private void updateVolume() {
//        volumeLabel.setText("Volume: " + (int) SoundController.volume);
//        volumeSlider.setValue(SoundController.volume);
//        volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {
//            @Override
//            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
//                volumeLabel.setText("Volume: " + (int) volumeSlider.getValue());
//                SoundController.setVolume(volumeSlider.getValue());
//            }
//        });
    }
    private void updateLabels() {
        HPLabel.setText("HP: " + user.getEpsilon().getHP());
        XPLabel.setText("XP: " + user.getEpsilon().getXp());
    }
    @FXML
    public void onHeal(ActionEvent actionEvent) {
        abilities.add(new HealWatch());
        user.getEpsilon().setXp(user.getEpsilon().getXp() - 50);
        updateLabels();
        BoardsProcessor.updateMainBoardLabel();
        processButtonsDisable();
    }

    public void onEmpower(ActionEvent actionEvent) {
    }
    public void processButtonsDisable() {
        processButtonsEnable(healButton, 50);
        processButtonsEnable(banishButton, 100);
        processButtonsEnable(empowerButton, 75);
        processButtonsEnable(dismayButton, 120);
        processButtonsEnable(slumberButton, 150);
        processButtonsEnable(thunderButton, 200);
    }
    private void processButtonsEnable(Button button, int minimumXP) {
        if (user.getEpsilon().getXp() < minimumXP)
            button.setDisable(true);
    }
    public void onBanish(ActionEvent actionEvent) {
//        GameController.mainBoard.xp -= 100;
//        MainBoard mainBoard = GameController.mainBoard;
//        Entity[] entities = new Entity[mainBoard.enemies.size()];
//        for (int i = 0; i < mainBoard.enemies.size(); i++) entities[i] = mainBoard.enemies.get(i);
//        mainBoard.impact(GameController.mainBoard.getUser().epsilon.getLayoutX(), GameController.mainBoard.getUser().epsilon.getLayoutY(), entities, 3);
//        updateLabels();
//        processButtonsDisable();
    }

    public void onDismay(ActionEvent actionEvent) {
    }

    public void onSlumber(ActionEvent actionEvent) {
    }

    public void onThunder(ActionEvent actionEvent) {
    }
}
