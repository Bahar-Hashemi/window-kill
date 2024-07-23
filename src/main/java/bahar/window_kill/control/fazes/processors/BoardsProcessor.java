package bahar.window_kill.control.fazes.processors;

import bahar.window_kill.control.Constants;
import bahar.window_kill.control.Deck;
import bahar.window_kill.control.util.GameUtil;

import static bahar.window_kill.control.Deck.*;

public class BoardsProcessor extends GameProcessor {
    @Override
    public void run() {
        shrinkMainBoard();
        updateMainBoardLabel();
        mainBoardInBounds();
    }
    private static void mainBoardInBounds() {
        mainBoard.setLayoutY(Math.max(0, mainBoard.getLayoutY()));
        mainBoard.setLayoutX(Math.max(0, mainBoard.getLayoutX()));
        if (mainBoard.getWidth() + mainBoard.getLayoutX() > Constants.SCREEN_WIDTH)
            mainBoard.lockWidth(Constants.SCREEN_WIDTH - mainBoard.getLayoutX());
        if (mainBoard.getHeight() + mainBoard.getLayoutY() > Constants.SCREEN_HEIGHT)
            mainBoard.lockHeight(Constants.SCREEN_HEIGHT - mainBoard.getLayoutY());
    }
    public static void requestMainBoardChangeInBounds(double x, double y, double width, double height) {
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
    private static void shrinkMainBoard() {
        double minusWidth = Math.min((mainBoard.getWidth() - Constants.MINIMUM_WIDTH) / 2, shrink);
        double minusHeight = Math.min((mainBoard.getHeight() - Constants.MINIMUM_HEIGHT) / 2, shrink);
        mainBoard.setIndependentDimensions(mainBoard.getLayoutX() + minusWidth, mainBoard.getLayoutY() + minusHeight,
                mainBoard.getWidth() - 2 * minusWidth , mainBoard.getHeight() - 2 * minusHeight);
    }
    public static void updateMainBoardLabel() {
        mainBoard.labelsToFront();
        mainBoard.setHP((int) user.getEpsilon().getHP());
        mainBoard.setXP((int) user.getEpsilon().getXp());
        mainBoard.setWave(wave);
        StringBuilder abilitiesString = new StringBuilder();
        if (coolDown / 1000 > 0)
            abilitiesString.append("cooldown: ").append(coolDown / 1000).append("s\n");
        for (int i = 0; i < abilities.size(); i++)
            abilitiesString.append(abilities.get(i).getName()).append("\n");
        mainBoard.setAbilities(abilitiesString.toString());
    }
}
