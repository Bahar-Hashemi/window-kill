package bahar.window_kill.communications.model.boards.controller;

import bahar.window_kill.client.control.GameController;
import bahar.window_kill.communications.model.User;
import bahar.window_kill.communications.model.boards.MainBoard;
import javafx.scene.input.KeyCode;

public class KIJLStrategy extends ControlStrategy {

    @Override
    public void requestUserControl(MainBoard mainBoard, User user) {
        mainBoard.getView().requestFocus();
        mainBoard.getView().setOnKeyPressed(keyEvent -> {
            switch (keyEvent.getCode()) {
                case KeyCode.I -> user.setUpRequest(true);
                case KeyCode.J -> user.setLeftRequest(true);
                case KeyCode.K -> user.setDownRequest(true);
                case KeyCode.L -> user.setRightRequest(true);
                case KeyCode.Q -> {
                    if (keyEvent.isControlDown())
                        user.setKillWish(true);
                }
            }
        });
        mainBoard.getView().setOnKeyReleased(keyEvent -> {
            switch (keyEvent.getCode()) {
                case KeyCode.I -> user.setUpRequest(false);
                case KeyCode.J -> user.setLeftRequest(false);
                case KeyCode.K -> user.setDownRequest(false);
                case KeyCode.L -> user.setRightRequest(false);
                case KeyCode.O -> user.setPauseRequest(!user.hasPauseRequest());
                case KeyCode.Q -> user.setKillWish(false);
                case KeyCode.H -> {
                    if (GameController.user.coolDown <= 0 && GameController.user.getDevelopment().getActiveDefense() != null)
                        user.abilityRequests.add(GameController.user.getDevelopment().getActiveDefense());
                } //heal
                case KeyCode.U -> {
                    if (GameController.user.coolDown <= 0 && GameController.user.getDevelopment().getActiveAttack() != null)
                        user.abilityRequests.add(GameController.user.getDevelopment().getActiveAttack());

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