package bahar.window_kill.client.view.controller.online;

import bahar.window_kill.client.control.connection.TCPClient;
import bahar.window_kill.client.model.User;
import bahar.window_kill.communications.data.TableSquad;
import bahar.window_kill.communications.data.TableUser;
import bahar.window_kill.communications.data.UserMessage;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

public class SquadMenuController extends OnlineController {
    private final Label squadName, squadVault;
    private final ListView<UserMessage> messagesBox;
    private final ListView<TableUser> teamMembersBox;
    public SquadMenuController(Label squadName, Label squadVault, ListView<UserMessage> messagesBox, ListView<TableUser> teamMembersBox) {
        this.squadName = squadName;
        this.squadVault = squadVault;
        this.messagesBox = messagesBox;
        this.teamMembersBox = teamMembersBox;
    }
    @Override
    public void initialize() {
        makeData();
        setupTeamMembersBox();
        setupMessageBox();
        makeBoxes();
    }

    @Override
    public void run() {
        makeData();
        makeBoxes();
    }
    private void makeBoxes() {
        TableSquad squad = User.getInstance().tableSquad;
        if (squad == null)
            teamMembersBox.getItems().clear();
        else
            teamMembersBox.getItems().setAll(new TCPClient().mySquadMembers(User.getInstance().getUsername()));
        if (User.getInstance().getTableUser().getMessages() != null)
            messagesBox.getItems().addAll(User.getInstance().getTableUser().getMessages());
    }
    private void makeData() {
        TableSquad squad = User.getInstance().tableSquad;
        if (squad == null) {
            makeNoSquad();
            return;
        }
        makeSquad(squad);
        return;
    }
    private void makeNoSquad() {
        squadName.setStyle("-fx-font-size: 12px; -fx-text-fill: red;");
        squadName.setText("You are in no squads!!");
        squadVault.setOpacity(0);
    }
    private void makeSquad(TableSquad tableSquad) {
        squadName.setStyle("-fx-font-size: 18px; -fx-text-fill: white");
        squadName.setText(tableSquad.getName());
        squadVault.setOpacity(1);
        squadVault.setText("VAULT: " + tableSquad.getVault());
    }
    private void setupMessageBox() {
        messagesBox.setCellFactory(listView -> new ListCell<>() {
            @Override
            protected void updateItem(UserMessage item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    Label label = new Label(item.toString());
                    label.setStyle("-fx-font-size: 12px; -fx-text-fill: white;");
                    setGraphic(label);

                    // Create context menu
                    ContextMenu contextMenu = new ContextMenu();
                    MenuItem acceptItem = new MenuItem("Accept");
                    MenuItem rejectItem = new MenuItem("Reject");

                    // Add event handlers for context menu items
                    acceptItem.setOnAction(event -> handleAccept(item));
                    rejectItem.setOnAction(event -> handleReject(item));

                    contextMenu.getItems().addAll(acceptItem, rejectItem);

                    // Show context menu on right-click
                    setOnMouseClicked(event -> {
                        if (event.getButton() == MouseButton.SECONDARY) {
                            contextMenu.show(label, event.getScreenX(), event.getScreenY());
                        }
                    });
                }
            }
        });
    }

    // Handle accept action
    private void handleAccept(UserMessage item) {
        messagesBox.getItems().remove(item);
        // Implement accept logic here
        System.out.println("Accepted: " + item);
    }

    // Handle reject action
    private void handleReject(UserMessage item) {
        messagesBox.getItems().remove(item);
    }
    private void setupTeamMembersBox() {
        teamMembersBox.setCellFactory(listView -> new ListCell<>() {
            @Override
            protected void updateItem(TableUser item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    HBox hbox = new HBox(1);
                    hbox.setAlignment(Pos.CENTER_LEFT);
                    Circle statusCircle = new Circle(5);
                    switch (item.getState()) {
                        case "busy":
                            statusCircle.setFill(Color.LIGHTSKYBLUE);
                            break;
                        case "online":
                            statusCircle.setFill(Color.LAWNGREEN);
                            break;
                        case "offline":
                            statusCircle.setFill(Color.RED);
                            break;
                    }
                    Label label = new Label(item.getUsername());
                    label.setStyle("-fx-font-size: 8px; -fx-text-fill: white;");
                    if (item.getUsername().equals(User.getInstance().getUsername())) {
                        label.setText(label.getText() + " (you)");
                        label.setStyle("-fx-font-weight: bold; -fx-text-fill: orange;");
                    }
                    label.setText(label.getText() + "    xp: " + item.getDevelopment().getXp());
                    hbox.getChildren().addAll(statusCircle, label);
                    setGraphic(hbox);
                }
            }
        });
    }
}
