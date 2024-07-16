package bahar.window_kill.model.boards.controller;

import bahar.window_kill.model.User;
import bahar.window_kill.model.boards.MainBoard;

public class KIJLStrategy extends ControlStrategy {

    @Override
    public void requestUserControl(MainBoard mainBoard, User user) {
        mainBoard.requestFocus();
        mainBoard.setOnKeyPressed(keyEvent -> {
            switch (keyEvent.getCode()) {
                case I -> user.setUpRequest(true);
                case J -> user.setLeftRequest(true);
                case K -> user.setDownRequest(true);
                case L -> user.setRightRequest(true);
                case Q -> {
                    if (keyEvent.isControlDown())
                        user.setKillWish(true);
                }
            }
        });
        mainBoard.setOnKeyReleased(keyEvent -> {
            switch (keyEvent.getCode()) {
                case I -> user.setUpRequest(false);
                case J -> user.setLeftRequest(false);
                case K -> user.setDownRequest(false);
                case L -> user.setRightRequest(false);
                case O -> user.setPauseRequest(!user.hasPauseRequest());
                case Q -> user.setKillWish(false);
                case H -> user.setDefenseRequest(true); //heal
                case U -> user.setAttackRequest(true); //fight
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