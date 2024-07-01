package bahar.window_kill.control;

import javafx.scene.image.Image;

public enum ImageController {
    HEAL_HOVER("heal.gif"), HEAL("heal.png"), EMPOWER("empower.png"), EMPOWER_HOVER("empower.gif"), BANISH("banish.png"), BANISH_HOVER("banish.gif"),
    GAME_ICON("icon.png");
    final Image image;
    ImageController(String s) {
        image = new Image(getClass().getResourceAsStream("/images/" + s));
    }
    public Image getImage() {
        return image;
    }
}
