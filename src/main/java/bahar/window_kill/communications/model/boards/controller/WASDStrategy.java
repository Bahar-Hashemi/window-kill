package bahar.window_kill.communications.model.boards.controller;

import bahar.window_kill.client.control.GameController;
import bahar.window_kill.communications.model.User;
import bahar.window_kill.communications.model.boards.MainBoard;
import javafx.scene.input.KeyCode;

public class WASDStrategy extends ControlStrategy {

    public void requestUserControl(MainBoard mainBoard, User user) {
        mainBoard.getView().requestFocus();
        mainBoard.getView().setOnKeyPressed(keyEvent -> {
            switch (keyEvent.getCode()) {
                case KeyCode.W -> user.setUpRequest(true);
                case KeyCode.A -> user.setLeftRequest(true);
                case KeyCode.S -> user.setDownRequest(true);
                case KeyCode.D -> user.setRightRequest(true);
                case KeyCode.Q -> {
                    if (keyEvent.isControlDown())
                        user.setKillWish(true);
                }
            }
        });
        mainBoard.getView().setOnKeyReleased(keyEvent -> {
            switch (keyEvent.getCode()) {
                case KeyCode.W -> user.setUpRequest(false);
                case KeyCode.A -> user.setLeftRequest(false);
                case KeyCode.S -> {
                    if (keyEvent.isShiftDown())
                        user.setSummonRequest(true);
                    user.setDownRequest(false);
                }
                case KeyCode.D -> user.setRightRequest(false);
                case KeyCode.E -> user.setPauseRequest(!user.hasPauseRequest());
                case KeyCode.Q -> user.setKillWish(false);
                case KeyCode.R -> {
                    if (GameController.user.getDevelopment().getActiveDefense() != null)
                        user.abilityRequests.add(user.getDevelopment().getActiveDefense());
                }
                case KeyCode.F -> {
                    if (GameController.user.getDevelopment().getActiveAttack() != null)
                        user.abilityRequests.add(user.getDevelopment().getActiveAttack());
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
