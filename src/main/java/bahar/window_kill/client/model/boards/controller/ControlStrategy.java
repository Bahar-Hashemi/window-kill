package bahar.window_kill.client.model.boards.controller;

import bahar.window_kill.client.model.User;
import bahar.window_kill.client.model.boards.MainBoard;

abstract public class ControlStrategy {
    abstract public void requestUserControl(MainBoard mainBoard, User user);
}
