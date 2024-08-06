package bahar.window_kill.client.control.states.offlline.processors;

import bahar.window_kill.client.control.Constants;
import bahar.window_kill.client.model.Game;
import bahar.window_kill.client.control.util.GameUtil;
import bahar.window_kill.client.model.User;
import bahar.window_kill.client.model.boards.MainBoard;

public class BoardsProcessor extends GameProcessor {
    public BoardsProcessor(Game game) {
        super(game);
    }

    @Override
    public void run() {
        for (User user: game.users) {
            shrinkMainBoard(user.mainBoard);
            updateMainBoardLabel(user.mainBoard, user);
            mainBoardInBounds(user.mainBoard);
        }

    }
    public static void requestMainBoardChangeInBounds(double x, double y, double width, double height, MainBoard mainBoard) {
        double myX = mainBoard.getLayoutX(), myY = mainBoard.getLayoutY();
        double myWidth = mainBoard.getWidth(), myHeight = mainBoard.getHeight();
        double left = 0, right = 1;
        while (right - left > 0.001) {
            double mid = (right + left) / 2;
            mainBoard.setIndependentDimensions(myX + x * mid,
                    myY + y * mid,
                    myWidth + width * mid,
                    myHeight + height * mid);
            if (GameUtil.nonHoveringBoardsInBounds(mainBoard))
                right = mid;
            else
                left = mid;
        }
    }
    private static void mainBoardInBounds(MainBoard mainBoard) {
        mainBoard.setLayoutY(Math.max(0, mainBoard.getLayoutY()));
        mainBoard.setLayoutX(Math.max(0, mainBoard.getLayoutX()));
        if (mainBoard.getWidth() + mainBoard.getLayoutX() > Constants.SCREEN_WIDTH)
            mainBoard.lockWidth(Constants.SCREEN_WIDTH - mainBoard.getLayoutX());
        if (mainBoard.getHeight() + mainBoard.getLayoutY() > Constants.SCREEN_HEIGHT)
            mainBoard.lockHeight(Constants.SCREEN_HEIGHT - mainBoard.getLayoutY());
    }
    private void shrinkMainBoard(MainBoard mainBoard) {
        double minusWidth = Math.min((mainBoard.getWidth() - Constants.MINIMUM_WIDTH) / 2, mainBoard.shrink);
        double minusHeight = Math.min((mainBoard.getHeight() - Constants.MINIMUM_HEIGHT) / 2, mainBoard.shrink);
        mainBoard.setIndependentDimensions(mainBoard.getLayoutX() + minusWidth, mainBoard.getLayoutY() + minusHeight,
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
