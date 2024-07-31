package bahar.window_kill.client.view;

import bahar.window_kill.client.control.Constants;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public enum PaneBuilder {
    MAIN_MENU_PANE("MainMenu.fxml", 1), PAUSE_PANE("PauseMenu.fxml", 0.7), GAME_OVER_PANE("GameOver.fxml", 0.6),
    SKILL_TREE_PANE("SkillTree.fxml", 1), SETTINGS_PANE("Settings.fxml", 1), SAVE_PANE("SavePane.fxml", 0.7),
    REGISTER_MENU_PANE("RegisterMenu.fxml", 1), ONLINE_MENU_PANE("OnlineMenu.fxml", 1), SIGNUP_MENU_PANE("SignupMenu.fxml", 1);
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
            BorderStroke borderStroke = new BorderStroke(
                    Color.rgb(255, 255, 255, 0.2), // Border color
                    BorderStrokeStyle.SOLID, // Border style
                    new CornerRadii(2),
                    new BorderWidths(3) // Border width
            );
            pane.setBorder(new Border(borderStroke));
            return pane;
        } catch (Exception e) {
            System.err.println(name + "can't be made!");
            e.printStackTrace();
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
