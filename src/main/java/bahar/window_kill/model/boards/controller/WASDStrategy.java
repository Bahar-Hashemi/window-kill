package bahar.window_kill.model.boards.controller;

import bahar.window_kill.control.GameController;
import bahar.window_kill.model.User;
import bahar.window_kill.model.boards.MainBoard;

public class WASDStrategy extends ControlStrategy {
    public WASDStrategy(MainBoard mainBoard) {
        super(mainBoard);
    }

    public void requestUserControl(User user) {
        mainBoard.requestFocus();
        mainBoard.setOnKeyPressed(keyEvent -> {
            switch (keyEvent.getCode()) {
                case W -> user.setUpRequest(true);
                case A -> user.setLeftRequest(true);
                case S -> user.setDownRequest(true);
                case D -> user.setRightRequest(true);
                case Q -> user.killRequest();
            }
        });
        mainBoard.setOnKeyReleased(keyEvent -> {
            switch (keyEvent.getCode()) {
                case W -> user.setUpRequest(false);
                case A -> user.setLeftRequest(false);
                case S -> user.setDownRequest(false);
                case D -> user.setRightRequest(false);
                case E -> user.setPauseRequest(!user.hasPauseRequest());
            }
        });
        mainBoard.setOnMousePressed(mouseEvent -> {
            user.setShooting(true);
            user.setMouseX(mouseEvent.getX() + mainBoard.getLayoutX());
            user.setMouseY(mouseEvent.getY() + mainBoard.getLayoutY());
        });
        mainBoard.setOnMouseReleased(mouseEvent -> user.setShooting(false));
        mainBoard.setOnMouseDragged(mouseEvent -> {
            user.setMouseX(mouseEvent.getX() + mainBoard.getLayoutX());
            user.setMouseY(mouseEvent.getY() + mainBoard.getLayoutY());
        });
    }
}
