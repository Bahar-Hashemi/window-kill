package bahar.window_kill.view;

import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainMenuStage extends Stage {
    public MainMenuStage() {
        getIcons().add(new Image(getClass().getResourceAsStream("/images/icon.png")));
        setScene(SceneBuilder.MAIN_MENU_SCENE.generateScene());
        setResizable(false);
        initStyle(StageStyle.UNDECORATED);
        show();
    }
}
