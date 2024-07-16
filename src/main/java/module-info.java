module learningJavaFX {
    requires  javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires freetts;
    requires com.google.gson;

    exports bahar.window_kill.view;
    exports bahar.window_kill.control;
    opens bahar.window_kill.model.data to com.google.gson;
    opens bahar.window_kill.model to com.google.gson;
    opens bahar.window_kill.view to javafx.fxml;
    exports bahar.window_kill.model.data;
    exports bahar.window_kill.model;
    exports bahar.window_kill.view.controller;
    exports bahar.window_kill.model.boards;
    exports bahar.window_kill.control.fazes.processors;
    exports bahar.window_kill.control.fazes;
    exports bahar.window_kill.control.util;
    opens bahar.window_kill.control.fazes.processors.abilities to com.google.gson;
    opens bahar.window_kill.control.fazes.processors.abilities.skills to com.google.gson;
    exports bahar.window_kill.control.fazes.processors.abilities.skills to com.google.gson;
    exports bahar.window_kill.control.fazes.processors.spawners;
}