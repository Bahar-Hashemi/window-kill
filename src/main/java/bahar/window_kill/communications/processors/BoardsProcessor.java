package bahar.window_kill.communications.processors;

import bahar.window_kill.communications.util.Constants;
import bahar.window_kill.client.control.GameController;
import bahar.window_kill.communications.model.Game;
import bahar.window_kill.communications.util.GameUtil;
import bahar.window_kill.communications.model.User;
import bahar.window_kill.communications.model.boards.MainBoard;

public class BoardsProcessor extends GameProcessor {
    public BoardsProcessor(Boolean isViewable, Game game) {
        super(isViewable, game);
    }

    @Override
    public void run() {
        for (User user: game.users) {
            shrinkMainBoard(user.mainBoard);
            updateMainBoardLabel(user.mainBoard, user);
            mainBoardInBounds(user.mainBoard);
        }

    }
    public static void requestMainBoardChangeInBounds(double x, double y, double width, double height, MainBoard mainBoard, Game game) {
        double myX = mainBoard.getX(), myY = mainBoard.getY();
        double myWidth = mainBoard.getWidth(), myHeight = mainBoard.getHeight();
        double left = 0, right = 1;
        while (right - left > 0.001) {
            double mid = (right + left) / 2;
            mainBoard.setDimensions(myX + x * mid,
                    myY + y * mid,
                    myWidth + width * mid,
                    myHeight + height * mid);
            if (GameUtil.nonHoveringBoardsInBounds(mainBoard, game))
                right = mid;
            else
                left = mid;
        }
    }
    private static void mainBoardInBounds(MainBoard mainBoard) {
        mainBoard.setLayoutY(Math.max(0, mainBoard.getY()));
        mainBoard.setLayoutX(Math.max(0, mainBoard.getX()));
        if (mainBoard.getWidth() + mainBoard.getX() > 1536)
            mainBoard.lockBoardWidth(1536 - mainBoard.getX());
        if (mainBoard.getHeight() + mainBoard.getY() > 950)
            mainBoard.lockBoardHeight(950 - mainBoard.getY());
    }
    private void shrinkMainBoard(MainBoard mainBoard) {
        double minusWidth = Math.min((mainBoard.getWidth() - 350) / 2, mainBoard.shrink);
        double minusHeight = Math.min((mainBoard.getHeight() - 200) / 2, mainBoard.shrink);
        mainBoard.setDimensions(mainBoard.getX() + minusWidth, mainBoard.getY() + minusHeight,
                mainBoard.getWidth() - 2 * minusWidth , mainBoard.getHeight() - 2 * minusHeight);
    }
    public void updateMainBoardLabel(MainBoard mainBoard, User user) {
        mainBoard.labelsToFront();
        mainBoard.setHP((int) user.getEpsilon().getHP());
        mainBoard.setXP((int) user.getXp());
        mainBoard.setWave(game.wave);
        StringBuilder abilitiesString = new StringBuilder();
        if (user.coolDown / 1000 > 0)
            abilitiesString.append("cool down: ").append(user.coolDown / 1000).append("s\n");
        for (int i = 0; i < user.abilities.size(); i++)
            abilitiesString.append(user.abilities.get(i).getType()).append("\n");
        mainBoard.setAbilities(abilitiesString.toString());
    }
}
