package bahar.window_kill.client.view.controller;

import bahar.window_kill.client.control.states.offlline.processors.abilities.AbilityType;
import bahar.window_kill.client.control.util.ImageUtil;
import bahar.window_kill.client.control.util.SoundUtil;
import bahar.window_kill.client.model.User;
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
    private User user = User.getInstance();
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
       XPLabel.setText("XP: " + user.getXp());
    }
    @FXML
    public void onHeal(ActionEvent actionEvent) {
        user.abilityRequests.add(AbilityType.HEAL);
        user.setXp(user.getXp() - 50);
        afterButtonPress();
    }
    private void afterButtonPress() {
        updateLabels();
        processButtonsDisable();
    }
    public void onEmpower(ActionEvent actionEvent) {
        user.abilityRequests.add(AbilityType.EMPOWER);
        user.setXp(user.getXp() - 75);
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
        if (user.getXp() < minimumXP || user.coolDown > 0)
            button.setDisable(true);
    }
    public void onBanish(ActionEvent actionEvent) {
        user.abilityRequests.add(AbilityType.BANISH);
        user.setXp(user.getXp() - 100);
        afterButtonPress();
    }

    public void onDismay(ActionEvent actionEvent) {
        user.abilityRequests.add(AbilityType.DISMAY);
        user.setXp(user.getXp() - 120);
        afterButtonPress();
    }

    public void onSlumber(ActionEvent actionEvent) {
        user.abilityRequests.add(AbilityType.SLUMBER);
        user.setXp(user.getXp() - 150);
        user.coolDown += 10 * 1000;
        afterButtonPress();
    }

    public void onThunder(ActionEvent actionEvent) {
        user.abilityRequests.add(AbilityType.THUNDER);
        user.setXp(user.getXp() - 200);
        user.coolDown += 120 * 1000;
        afterButtonPress();
    }
}
