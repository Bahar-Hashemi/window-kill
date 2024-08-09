package bahar.window_kill.client.view.controller.online;

import bahar.window_kill.client.control.GameController;
import bahar.window_kill.client.control.connection.TCPClient;
import bahar.window_kill.communications.data.TableUser;
import bahar.window_kill.communications.data.UserMessage;
import bahar.window_kill.communications.data.UserMessageType;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

public class GamesMenuController extends OnlineController {
    private final ListView<String> gameHistoryBox;
    private final ListView<TableUser> enemiesBox;
    public GamesMenuController(ListView<String> gameHistoryBox, ListView<TableUser> enemiesBox) {
        this.gameHistoryBox = gameHistoryBox;
        this.enemiesBox = enemiesBox;
    }
    @Override
    public void initialize() {
        makeData();
        setupEnemiesBox();
    }

    @Override
    public void run() {
        makeData();
    }
    private void makeData() {
        makeEnemiesBox();
        makeGameHistoryBox();
    }
    private void makeEnemiesBox() {
        ArrayList<TableUser> enemies = new TCPClient().getEnemyMembers(GameController.user.getUsername());
        enemiesBox.getItems().setAll(enemies);
    }
    private void makeGameHistoryBox() {

    }
    private void setupEnemiesBox() {
        makeView();
    }
    private void makeView() {
        enemiesBox.setCellFactory(listView -> new ListCell<>() {
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
                    label.setStyle("-fx-font-size: 13px; -fx-text-fill: white;");
                    label.setText(label.getText() + "    xp: " + item.getDevelopment().getXp());
                    hbox.getChildren().addAll(statusCircle, label);
                    setGraphic(hbox);
                    ContextMenu contextMenu = new ContextMenu();
                    MenuItem battleItem = new MenuItem("MONOMACHIA");
                    battleItem.setOnAction(event -> handleMonomachiaAction(item));
                    contextMenu.getItems().add(battleItem);
                    label.setOnMouseClicked(event -> {
                        if (event.getButton() == MouseButton.SECONDARY) {
                            contextMenu.show(enemiesBox, event.getScreenX(), event.getScreenY());
                        }
                    });
                }
            }
        });
    }

    private void handleMonomachiaAction(TableUser tableUser) {
        UserMessage userMessage = new UserMessage(UserMessageType.MONOMACHIA_REQUEST, GameController.user.getUsername(), tableUser.getUsername());
        new TCPClient().sendUserMessage(userMessage);
    }
}
