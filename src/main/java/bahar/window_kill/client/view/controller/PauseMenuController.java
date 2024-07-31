package bahar.window_kill.client.view.controller;

import bahar.window_kill.client.control.util.ImageUtil;
import bahar.window_kill.client.control.util.SoundUtil;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;

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
//       HPLabel.setText("HP: " + user.getEpsilon().getHP());
//        XPLabel.setText("XP: " + user.getXp());
    }
    @FXML
    public void onHeal(ActionEvent actionEvent) {
//        user.abilities.add(new HealWatch());
//        user.setXp(user.getXp() - 50);
//        afterButtonPress();
    }
    private void afterButtonPress() {
        updateLabels();
        processButtonsDisable();
    }
    public void onEmpower(ActionEvent actionEvent) {
//        user.abilities.add(new EmpowerWatch());
//        user.setXp(user.getXp() - 75);
//        afterButtonPress();
    }
    public void processButtonsDisable() {
//        processButtonsEnable(healButton, 50);
//        processButtonsEnable(banishButton, 100);
//        processButtonsEnable(empowerButton, 75);
//        processButtonsEnable(dismayButton, 120);
//        processButtonsEnable(slumberButton, 150);
//        processButtonsEnable(thunderButton, 200);
    }
    private void processButtonsEnable(Button button, int minimumXP) {
//        if (user.getXp() < minimumXP || user.coolDown > 0)
//            button.setDisable(true);
    }
    public void onBanish(ActionEvent actionEvent) {
//        user.abilities.add(new BanishWatch());
//        user.setXp(user.getXp() - 100);
//        afterButtonPress();
    }

    public void onDismay(ActionEvent actionEvent) {
//        user.abilities.add(new DismayWatch());
//        user.setXp(user.getXp() - 120);
//        afterButtonPress();
    }

    public void onSlumber(ActionEvent actionEvent) {
//        user.abilities.add(new SlumberWatch());
//        user.setXp(user.getXp() - 150);
//        user.coolDown += 10 * 1000;
//        afterButtonPress();
    }

    public void onThunder(ActionEvent actionEvent) {
//        user.abilities.add(new ThunderWatch());
//        user.setXp(user.getXp() - 200);
//        user.coolDown += 120 * 1000;
//        afterButtonPress();
    }
}
