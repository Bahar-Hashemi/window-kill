package bahar.window_kill.control;

import bahar.window_kill.control.fazes.processors.abilities.*;
import bahar.window_kill.control.fazes.processors.BoardsProcessor;
import bahar.window_kill.control.loader.ImageLoader;
import bahar.window_kill.control.loader.SoundLoader;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
        volumeLabel.setText("Volume: " + (int) SoundLoader.getVolume());
        volumeSlider.setValue(SoundLoader.getVolume());
        volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                volumeLabel.setText("Volume: " + (int) volumeSlider.getValue());
                SoundLoader.setVolume(volumeSlider.getValue());
            }
        });
    }
    private void updateLabels() {
        HPLabel.setText("HP: " + user.getEpsilon().getHP());
        XPLabel.setText("XP: " + user.getEpsilon().getXp());
    }
    @FXML
    public void onHeal(ActionEvent actionEvent) {
        abilities.add(new HealWatch());
        user.getEpsilon().setXp(user.getEpsilon().getXp() - 50);
        afterButtonPress();
    }
    private void afterButtonPress() {
        updateLabels();
        BoardsProcessor.updateMainBoardLabel();
        processButtonsDisable();
    }
    public void onEmpower(ActionEvent actionEvent) {
        abilities.add(new EmpowerWatch());
        user.getEpsilon().setXp(user.getEpsilon().getXp() - 75);
        afterButtonPress();
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
        if (user.getEpsilon().getXp() < minimumXP || coolDown > 0)
            button.setDisable(true);
    }
    public void onBanish(ActionEvent actionEvent) {
        abilities.add(new BanishWatch());
        user.getEpsilon().setXp(user.getEpsilon().getXp() - 100);
        afterButtonPress();
    }

    public void onDismay(ActionEvent actionEvent) {
        abilities.add(new DismayWatch());
        user.getEpsilon().setXp(user.getEpsilon().getXp() - 120);
        afterButtonPress();
    }

    public void onSlumber(ActionEvent actionEvent) {
        abilities.add(new SlumberWatch());
        user.getEpsilon().setXp(user.getEpsilon().getXp() - 150);
        afterButtonPress();
    }

    public void onThunder(ActionEvent actionEvent) {
        abilities.add(new ThunderWatch());
        user.getEpsilon().setXp(user.getEpsilon().getXp() - 200);
        afterButtonPress();
    }
}
