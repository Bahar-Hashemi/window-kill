package bahar.window_kill.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public enum SceneBuilder {
    MAIN_MENU_SCENE("MainMenu.fxml"), GAME_SCENE("Game.fxml"), PAUSE_SCENE("PauseMenu.fxml"), GAME_OVER_SCENE("GameOver.fxml");
    String name;
    SceneBuilder(String name) {
        this.name = name;
    }
    public Scene generateScene() {
        try {
            Pane root = FXMLLoader.load(GameLauncher.class.getResource("/FXML/" + name));
            return new Scene(root);
        } catch (Exception e) {
            System.err.println(name + "can't be made!");
            System.out.println(e.getStackTrace());
        }
        return null;
    }

}
