package bahar.window_kill.view;

import bahar.window_kill.control.Constants;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public enum PaneBuilder {
    MAIN_MENU_PANE("MainMenu.fxml", 1), PAUSE_PANE("PauseMenu.fxml", 0.9), GAME_OVER_PANE("GameOver.fxml", 0.9);
    final String name;
    final double opacity;
    PaneBuilder(String name, double opacity) {
        this.name = name;
        this.opacity = opacity;
    }
    public Pane generatePane() {
        try {
            Pane pane = FXMLLoader.load(getClass().getResource("/FXML/" + name));
            pane.setBackground(Background.fill(Constants.BACKGROUND_COLOR));
            pane.setOpacity(opacity);
            return pane;
        } catch (Exception e) {
            System.err.println(name + "can't be made!");
            System.out.println(e.getMessage());
            return null;
        }
    }
    public Pane generatePane(int x, int y, int width, int height) {
        Pane result = generatePane();
        result.setMinHeight(height); result.setMaxHeight(height);
        result.setMinWidth(width);  result.setMaxWidth(width);
        result.setLayoutX(x);
        result.setLayoutY(y);
        return result;
    }

}
