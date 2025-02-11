package bahar.window_kill.client.control.util;

import javafx.scene.image.Image;

public enum ImageUtil {
    HEAL_HOVER("heal.gif"), HEAL("heal.png"), EMPOWER("empower.png"), EMPOWER_HOVER("empower.gif"), BANISH("banish.png"), BANISH_HOVER("banish.gif"),
    GAME_ICON("icon.png"), BARRICADOS("barricados.jpg"), RIGHT_HAND("right_hand.png"), LEFT_HAND("left_hand.png"), RIGHT_PUNCH("right_punch.png"), LEFT_PUNCH("left_punch.png"),
    DISMAY("dismay.png"), DISMAY_HOVER("dismay.gif"), SLUMBER("slumber.png"), SLUMBER_HOVER("slumber.gif"), THUNDER("thunder.png"), THUNDER_HOVER("thunder.gif"),
    GIT_PUSH("git_push.gif");
    final Image image;
    ImageUtil(String s) {
        image = new Image(getClass().getResourceAsStream("/images/" + s));
    }
    public Image getImage() {
        return image;
    }
}
