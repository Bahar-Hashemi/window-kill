package bahar.window_kill.client.view.controller.online;

import bahar.window_kill.client.control.connection.TCPClient;
import bahar.window_kill.client.model.User;
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
        TableUser user = new TCPClient().getMe(User.getInstance().getUsername());
        TableSquad squad = new TCPClient().mySquad(User.getInstance().getUsername());
        User.getInstance().development = user.getDevelopment();
        User.getInstance().tableSquad = squad;
    }
}
