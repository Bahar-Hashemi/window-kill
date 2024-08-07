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
    opens bahar.window_kill.communications.model to com.google.gson;
    opens bahar.window_kill.client.view to javafx.fxml;
    exports bahar.window_kill.communications.data;
    exports bahar.window_kill.communications.model;
    exports bahar.window_kill.communications.model.boards;
    exports bahar.window_kill.communications.processors;
    exports bahar.window_kill.client.control.states;
    exports bahar.window_kill.client.control.util;
    opens bahar.window_kill.communications.processors.util.abilities to com.google.gson;
    opens bahar.window_kill.communications.messages.client to com.google.gson;
    opens bahar.window_kill.communications.processors.util.abilities.skills to com.google.gson;
    opens bahar.window_kill.communications.messages.server to com.google.gson;
    exports bahar.window_kill.communications.processors.util.abilities.skills to com.google.gson;
    exports bahar.window_kill.communications.processors.util.spawners;
    opens bahar.window_kill.communications.messages.client.register to com.google.gson;
    opens bahar.window_kill.communications.messages.client.data to com.google.gson;
    opens bahar.window_kill.communications.model.boards to  com.google.gson;
    exports bahar.window_kill.communications.model.boards.controller to com.google.gson;
    opens bahar.window_kill.communications.messages.client.squads to com.google.gson;
    opens bahar.window_kill.communications.model.entities.attackers to com.google.gson;
    opens bahar.window_kill.communications.messages.client.game to com.google.gson;
    opens bahar.window_kill.communications.model.entities.generators.shooters to com.google.gson;
    opens bahar.window_kill.communications.model.entities to  com.google.gson;
    opens bahar.window_kill.communications.model.entities.generators to  com.google.gson;
    opens bahar.window_kill.communications.processors.util.strategies.movements to com.google.gson;
    exports bahar.window_kill.communications.processors.util.abilities to com.google.gson;
    exports bahar.window_kill.communications.processors.util.strategies.attacks to com.google.gson;
    exports bahar.window_kill.client.control.states.offlline;
    exports bahar.window_kill.client.view.controller.offline;
    exports bahar.window_kill.client.view.controller.online;
    exports bahar.window_kill.communications.model.entities.additional.data to com.google.gson;
    opens bahar.window_kill.communications.processors.util.strategies.attacks to com.google.gson;
    exports bahar.window_kill.communications.util;
}