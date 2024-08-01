package bahar.window_kill.client.view.controller.online;

import bahar.window_kill.client.control.connection.TCPClient;
import bahar.window_kill.client.model.User;
import bahar.window_kill.communications.data.UserMessage;
import bahar.window_kill.communications.data.UserMessageType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class GlobeMenuController extends OnlineController {
    private final ListView<String> globalSquads;

    public GlobeMenuController(ListView<String> globalSquads) {
        this.globalSquads = globalSquads;
    }

    @Override
    public void initialize() {
        writeSquads();
        setUpContextMenu();
    }

    @Override
    public void run() {
        writeSquads();
    }

    private void writeSquads() {
        ArrayList<String> squads = new TCPClient().getGlobe(User.getInstance().getUsername());
        globalSquads.getItems().clear();
        globalSquads.getItems().addAll(squads);
    }

    private void setUpContextMenu() {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem requestToJoin = new MenuItem("Request to join");
        contextMenu.getItems().add(requestToJoin);

        globalSquads.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                contextMenu.show(globalSquads, event.getScreenX(), event.getScreenY());
            } else {
                contextMenu.hide();
            }
        });

        requestToJoin.setOnAction(event -> {
            String selectedSquad = globalSquads.getSelectionModel().getSelectedItem();
            if (selectedSquad != null) {
                String squadName = findSquadName(selectedSquad);
                new TCPClient().sendUserMessage(new UserMessage(UserMessageType.MEMBERSHIP_REQUEST, User.getInstance().getUsername(), squadName));
            }
        });
    }
    private static String findSquadName(String squad) {
        return squad.substring(0, squad.lastIndexOf("(") - 1);
    }
}
