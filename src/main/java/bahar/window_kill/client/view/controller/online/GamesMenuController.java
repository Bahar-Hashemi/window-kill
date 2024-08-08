package bahar.window_kill.client.view.controller.online;

import bahar.window_kill.client.control.GameController;
import bahar.window_kill.client.control.connection.TCPClient;
import bahar.window_kill.communications.data.TableUser;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;

import java.util.ArrayList;

public class GamesMenuController extends OnlineController {
    private final ListView<String> GameHistoryBox;
    private final ListView<TableUser> EnemiesBox;
    public GamesMenuController(ListView<String> gameHistoryBox, ListView<TableUser> enemiesBox) {
        GameHistoryBox = gameHistoryBox;
        EnemiesBox = enemiesBox;
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
        EnemiesBox.getItems().setAll(enemies);
    }
    private void makeGameHistoryBox() {

    }
    private void setupEnemiesBox() {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem battleItem = new MenuItem("Monomachia");

        battleItem.setOnAction(event -> handleMonomachiaAction());

        contextMenu.getItems().add(battleItem);

        EnemiesBox.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                contextMenu.show(EnemiesBox, event.getScreenX(), event.getScreenY());
            }
        });
    }

    private void handleMonomachiaAction() {
        System.out.println("monomachia requested!");
    }
}
