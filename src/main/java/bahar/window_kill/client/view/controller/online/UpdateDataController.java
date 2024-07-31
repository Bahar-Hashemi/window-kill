package bahar.window_kill.client.view.controller.online;

import bahar.window_kill.client.control.connection.TCPClient;
import bahar.window_kill.client.model.User;
import bahar.window_kill.communications.data.TableUser;
import bahar.window_kill.server.control.TCPWorker;

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
        User.getInstance().development = user.getDevelopment();
    }
}
