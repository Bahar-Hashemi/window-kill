module learningJavaFX {
    requires  javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires com.google.gson;
    requires java.sql;
    requires mysql.connector.j;
    exports bahar.window_kill.client.view;
    exports bahar.window_kill.client.control;
    opens bahar.window_kill.communications.data to com.google.gson;
    opens bahar.window_kill.client.model to com.google.gson;
    opens bahar.window_kill.client.view to javafx.fxml;
    exports bahar.window_kill.communications.data;
    exports bahar.window_kill.client.model;
    exports bahar.window_kill.client.view.controller;
    exports bahar.window_kill.client.model.boards;
    exports bahar.window_kill.client.control.states.processors;
    exports bahar.window_kill.client.control.states;
    exports bahar.window_kill.client.control.util;
    opens bahar.window_kill.client.control.states.processors.abilities to com.google.gson;
    opens bahar.window_kill.communications.messages.client to com.google.gson;
    opens bahar.window_kill.client.control.states.processors.abilities.skills to com.google.gson;
    opens bahar.window_kill.communications.messages.server to com.google.gson;
    exports bahar.window_kill.client.control.states.processors.abilities.skills to com.google.gson;
    exports bahar.window_kill.client.control.states.processors.spawners;
    opens bahar.window_kill.communications.messages.client.register to com.google.gson;
    opens bahar.window_kill.communications.messages.client.data to com.google.gson;
    opens bahar.window_kill.communications.messages.client.squads to com.google.gson;
}