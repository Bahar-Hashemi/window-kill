package bahar.window_kill.communications.model.entities;

import bahar.window_kill.communications.model.boards.GameBoard;

public interface BoardOwner {
    public void byBoard();
    public GameBoard getBoard();
}
