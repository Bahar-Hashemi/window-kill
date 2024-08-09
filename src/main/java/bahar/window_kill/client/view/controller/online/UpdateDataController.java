package bahar.window_kill.client.view.controller.online;

import bahar.window_kill.client.control.GameController;
import bahar.window_kill.client.control.connection.TCPClient;
import bahar.window_kill.client.control.util.SoundUtil;
import bahar.window_kill.client.view.MainStage;
import bahar.window_kill.communications.model.User;
import bahar.window_kill.communications.data.TableSquad;
import bahar.window_kill.communications.data.TableUser;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class UpdateDataController extends OnlineController {
    @Override
    public void initialize() {
        updateData();
    }

    @Override
    public void run() {
        updateData();
    }
    private void updateData() {
        TableUser user = new TCPClient().getMe(GameController.user.getUsername());
        TableSquad squad = new TCPClient().mySquad(GameController.user.getUsername());
        if (user != null && GameController.user.getTableUser() != null && user.getState().equals("busy") && GameController.user.getTableUser().getState().equals("online")) {
            System.out.println("hello");
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> SoundUtil.HIT.play()));
            timeline.setCycleCount(3);
            timeline.setOnFinished(event -> {
                MainStage.newScene();
                new GameController().newOnlineGame();
            });
            timeline.play();
        }
        GameController.user.setTableUser(user);
        GameController.user.development = user.getDevelopment();
        GameController.user.tableSquad = squad;
    }
}
