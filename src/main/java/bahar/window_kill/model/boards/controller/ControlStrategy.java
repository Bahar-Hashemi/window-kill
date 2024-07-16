package bahar.window_kill.model.boards.controller;

import bahar.window_kill.model.User;
import bahar.window_kill.model.boards.MainBoard;

abstract public class ControlStrategy {
    abstract public void requestUserControl(MainBoard mainBoard, User user);
}
