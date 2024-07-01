package bahar.window_kill.control;

import javafx.scene.paint.Color;
import javafx.stage.Screen;

public class Constants {
    public static final double SCREEN_WIDTH = Screen.getPrimary().getBounds().getWidth();
    public static final double SCREEN_HEIGHT = Screen.getPrimary().getBounds().getHeight() - 10;
    public static final double MINIMUM_WIDTH = Screen.getPrimary().getBounds().getWidth() / 6;
    public static final double MINIMUM_HEIGHT = (Screen.getPrimary().getBounds().getHeight() - 10) / 6;
    public static final Color BACKGROUND_COLOR = Color.BLACK;
    public static final double RESPOND_DURATION = 30;
}
