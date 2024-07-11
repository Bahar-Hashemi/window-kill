package bahar.window_kill.control;

import bahar.window_kill.control.loader.ImageLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class PauseMenuController {
    @FXML
    public Label HPLabel, XPLabel;
    @FXML
    public Button healButton, banishButton, empowerButton;
    public Label volumeLabel;
    public Slider volumeSlider;
    public ImageView healImage, banishImage, empowerImage;
    public AnchorPane pane;

    @FXML
    public void initialize() {
        makeOnHovers();
        updateLabels();
        updateVolume();
        processButtonsDisable();
    }
    private void makeOnHovers() {
        healButton.setOnMouseEntered(e -> healImage.setImage(ImageLoader.HEAL_HOVER.getImage()));
        healButton.setOnMouseExited(e -> healImage.setImage(ImageLoader.HEAL.getImage()));
        banishButton.setOnMouseEntered(e -> banishImage.setImage(ImageLoader.BANISH_HOVER.getImage()));
        banishButton.setOnMouseExited(e -> banishImage.setImage(ImageLoader.BANISH.getImage()));
        empowerButton.setOnMouseEntered(e -> empowerImage.setImage(ImageLoader.EMPOWER_HOVER.getImage()));
        empowerButton.setOnMouseExited(e -> empowerImage.setImage(ImageLoader.EMPOWER.getImage()));
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
//        HPLabel.setText("HP: " + GameController.mainBoard.getUser().epsilon.getHP());
//        XPLabel.setText("XP: " + GameController.mainBoard.xp);
    }
    @FXML
    public void onHeal(ActionEvent actionEvent) {
//        //GameController.mainBoard.getUser().epsilon.setHP(GameController.mainBoard.getUser().epsilon.getHP() + 10);
//        //GameController.mainBoard.xp -= 50;
//        updateLabels();
//        processButtonsDisable();
    }

    public void onEmpower(ActionEvent actionEvent) {
//        //GameController.mainBoard.xp -= 75;
//        //GameController.shootCount += 2;
//        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(15), e -> {GameController.shootCount -= 2;}));
//        timeline.play();
//        updateLabels();
//        processButtonsDisable();
    }
    private void processButtonsDisable() {
//        if (GameController.mainBoard.xp < 75) {
//            empowerButton.setDisable(true);
//        }
//        if (GameController.mainBoard.xp < 50) {
//            healButton.setDisable(true);
//        }
//        if (GameController.mainBoard.xp < 100) {
//            banishButton.setDisable(true);
//        }
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
}
