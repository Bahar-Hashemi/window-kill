package bahar.window_kill.client.model.entities;

import bahar.window_kill.client.model.boards.GameBoard;

public interface BoardOwner {
    public void byBoard();
    public GameBoard getBoard();
    public void setSceneX(double x);
    public void setSceneY(double y);
    public void setLayoutX(double x);
    public void setLayoutY(double y);
}
