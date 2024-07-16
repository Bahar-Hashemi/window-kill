package bahar.window_kill.view.controller;

import bahar.window_kill.control.Deck;
import bahar.window_kill.control.fazes.processors.BoardsProcessor;
import bahar.window_kill.control.fazes.processors.abilities.shop.*;
import bahar.window_kill.control.util.ImageUtil;
import bahar.window_kill.control.util.SoundUtil;
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
        makeButton(healButton, healImage, ImageUtil.HEAL, ImageUtil.HEAL_HOVER);
        makeButton(banishButton, banishImage, ImageUtil.BANISH, ImageUtil.BANISH_HOVER);
        makeButton(empowerButton, empowerImage, ImageUtil.EMPOWER, ImageUtil.EMPOWER_HOVER);
        makeButton(dismayButton, dismayImage, ImageUtil.DISMAY, ImageUtil.DISMAY_HOVER);
        makeButton(slumberButton, slumberImage, ImageUtil.SLUMBER, ImageUtil.SLUMBER_HOVER);
        makeButton(thunderButton, thunderImage, ImageUtil.THUNDER, ImageUtil.THUNDER_HOVER);
    }
    private void makeButton(Button button, ImageView buttonImage, ImageUtil image, ImageUtil onHover) {
        button.setOnMouseEntered(e -> buttonImage.setImage(onHover.getImage()));
        button.setOnMouseExited(e -> buttonImage.setImage(image.getImage()));
    }
    private void updateVolume() {
        volumeLabel.setText("Volume: " + (int) SoundUtil.getVolume());
        volumeSlider.setValue(SoundUtil.getVolume());
        volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                volumeLabel.setText("Volume: " + (int) volumeSlider.getValue());
                SoundUtil.setVolume(volumeSlider.getValue());
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
        Deck.coolDown += 10 * 1000;
        afterButtonPress();
    }

    public void onThunder(ActionEvent actionEvent) {
        abilities.add(new ThunderWatch());
        user.getEpsilon().setXp(user.getEpsilon().getXp() - 200);
        coolDown += 120 * 1000;
        afterButtonPress();
    }
}
