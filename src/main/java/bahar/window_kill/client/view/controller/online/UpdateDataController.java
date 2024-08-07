package bahar.window_kill.client.view.controller.online;

import bahar.window_kill.client.control.GameController;
import bahar.window_kill.client.control.connection.TCPClient;
import bahar.window_kill.communications.model.User;
import bahar.window_kill.communications.data.TableSquad;
import bahar.window_kill.communications.data.TableUser;

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
        GameController.user.setTableUser(user);
        GameController.user.development = user.getDevelopment();
        GameController.user.tableSquad = squad;
    }
}
