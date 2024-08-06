package bahar.window_kill.client.model.boards.controller;

import bahar.window_kill.client.model.User;
import bahar.window_kill.client.model.boards.MainBoard;
import bahar.window_kill.communications.data.Development;

public class WASDStrategy extends ControlStrategy {
    public WASDStrategy() {
    }

    public void requestUserControl(MainBoard mainBoard, User user) {
        mainBoard.getView().requestFocus();
        mainBoard.getView().setOnKeyPressed(keyEvent -> {
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
        mainBoard.getView().setOnKeyReleased(keyEvent -> {
            switch (keyEvent.getCode()) {
                case W -> user.setUpRequest(false);
                case A -> user.setLeftRequest(false);
                case S -> user.setDownRequest(false);
                case D -> user.setRightRequest(false);
                case E -> user.setPauseRequest(!user.hasPauseRequest());
                case Q -> user.setKillWish(false);
                case R -> {
                    if (User.getInstance().coolDown <= 0 && User.getInstance().getDevelopment().getActiveDefense() != null)
                        user.abilityRequests.add(User.getInstance().getDevelopment().getActiveDefense());
                }
                case F -> {
                    if (User.getInstance().coolDown <= 0 && User.getInstance().getDevelopment().getActiveAttack() != null)
                        user.abilityRequests.add(User.getInstance().getDevelopment().getActiveAttack());
                } //fight
            }
        });
        mainBoard.getView().setOnMousePressed(mouseEvent -> {
            user.setShooting(true);
            user.setMouseX(mouseEvent.getX() + mainBoard.getX());
            user.setMouseY(mouseEvent.getY() + mainBoard.getY());
        });
        mainBoard.getView().setOnMouseReleased(mouseEvent -> user.setShooting(false));
        mainBoard.getView().setOnMouseDragged(mouseEvent -> {
            user.setMouseX(mouseEvent.getX() + mainBoard.getX());
            user.setMouseY(mouseEvent.getY() + mainBoard.getY());
        });
    }
}
