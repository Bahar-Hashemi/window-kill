package bahar.window_kill.control.loader;

import javafx.scene.image.Image;

public enum ImageLoader {
    HEAL_HOVER("heal.gif"), HEAL("heal.png"), EMPOWER("empower.png"), EMPOWER_HOVER("empower.gif"), BANISH("banish.png"), BANISH_HOVER("banish.gif"),
    GAME_ICON("icon.png"), BARRICADOS("barricados.jpg");
    final Image image;
    ImageLoader(String s) {
        image = new Image(getClass().getResourceAsStream("/images/" + s));
    }
    public Image getImage() {
        return image;
    }
}
