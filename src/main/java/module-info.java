module learningJavaFX {
    requires  javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    exports bahar.window_kill.view;
    exports bahar.window_kill.control;
    opens bahar.window_kill.view to javafx.fxml;
    exports bahar.window_kill.model;
    exports bahar.window_kill.view.controller;
    exports bahar.window_kill.model.boards;
    exports bahar.window_kill.control.fazes.processors;
    exports bahar.window_kill.control.fazes;
    exports bahar.window_kill.control.loader;
}