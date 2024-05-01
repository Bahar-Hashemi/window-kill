module learningJavaFX {
    requires  javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    exports bahar.window_kill.view;
    opens bahar.window_kill.view to javafx.fxml;
}