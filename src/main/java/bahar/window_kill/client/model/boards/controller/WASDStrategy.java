package bahar.window_kill.client.model.boards.controller;

import bahar.window_kill.client.model.User;
import bahar.window_kill.client.model.boards.MainBoard;

public class WASDStrategy extends ControlStrategy {
    public WASDStrategy() {
    }

    public void requestUserControl(MainBoard mainBoard, User user) {
        mainBoard.requestFocus();
        mainBoard.setOnKeyPressed(keyEvent -> {
            switch (keyEvent.getCode()) {
                case W -> user.setUpRequest(true);
                case A -> user.setLeftRequest(true);
                case S -> user.setDownRequest(true);
                case D -> user.setRightRequest(true);
                case Q -> {
                    if (keyEvent.isControlDown())
                        user.setKillWish(true);
                }
            }
        });
        mainBoard.setOnKeyReleased(keyEvent -> {
            switch (keyEvent.getCode()) {
                case W -> user.setUpRequest(false);
                case A -> user.setLeftRequest(false);
                case S -> user.setDownRequest(false);
                case D -> user.setRightRequest(false);
                case E -> user.setPauseRequest(!user.hasPauseRequest());
                case Q -> user.setKillWish(false);
                case R -> user.setDefenseRequest(true); //resist
                case F -> user.setAttackRequest(true); //fight
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
