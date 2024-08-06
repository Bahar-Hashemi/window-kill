package bahar.window_kill.client.model.boards.controller;

import bahar.window_kill.client.model.User;
import bahar.window_kill.client.model.boards.MainBoard;

public class KIJLStrategy extends ControlStrategy {

    @Override
    public void requestUserControl(MainBoard mainBoard, User user) {
        mainBoard.getView().requestFocus();
        mainBoard.getView().setOnKeyPressed(keyEvent -> {
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
        mainBoard.getView().setOnKeyReleased(keyEvent -> {
            switch (keyEvent.getCode()) {
                case I -> user.setUpRequest(false);
                case J -> user.setLeftRequest(false);
                case K -> user.setDownRequest(false);
                case L -> user.setRightRequest(false);
                case O -> user.setPauseRequest(!user.hasPauseRequest());
                case Q -> user.setKillWish(false);
                case H -> {
                    if (User.getInstance().coolDown <= 0 && User.getInstance().getDevelopment().getActiveDefense() != null)
                        user.abilityRequests.add(User.getInstance().getDevelopment().getActiveDefense());
                } //heal
                case U -> {
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