package bahar.window_kill.control.loader;

import javafx.scene.image.Image;

public enum ImageLoader {
    HEAL_HOVER("heal.gif"), HEAL("heal.png"), EMPOWER("empower.png"), EMPOWER_HOVER("empower.gif"), BANISH("banish.png"), BANISH_HOVER("banish.gif"),
    GAME_ICON("icon.png"), BARRICADOS("barricados.jpg"), RIGHT_HAND("right_hand.png"), LEFT_HAND("left_hand.png"), RIGHT_PUNCH("right_punch.png"), LEFT_PUNCH("left_punch.png"),;
    final Image image;
    ImageLoader(String s) {
        image = new Image(getClass().getResourceAsStream("/images/" + s));
    }
    public Image getImage() {
        return image;
    }
}
