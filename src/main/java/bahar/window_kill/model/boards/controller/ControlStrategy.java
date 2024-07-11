package bahar.window_kill.model.boards.controller;

import bahar.window_kill.model.User;
import bahar.window_kill.model.boards.MainBoard;

abstract public class ControlStrategy {
    protected final MainBoard mainBoard;
    public ControlStrategy(MainBoard mainBoard) {
        this.mainBoard = mainBoard;
    }
    abstract public void requestUserControl(User user);
}
