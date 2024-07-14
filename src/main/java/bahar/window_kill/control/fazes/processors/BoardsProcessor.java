package bahar.window_kill.control.fazes.processors;

import bahar.window_kill.control.Constants;
import bahar.window_kill.control.GameUtil;

import static bahar.window_kill.control.Deck.*;

public class BoardsProcessor extends GameProcessor {

    @Override
    public void run() {
        shrinkMainBoard();
        updateMainBoardLabel();
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
        double minusWidth = Math.min((mainBoard.getWidth() - Constants.MINIMUM_WIDTH) / 2, 0.3);
        double minusHeight = Math.min((mainBoard.getHeight() - Constants.MINIMUM_HEIGHT) / 2, 0.3);
        mainBoard.setIndependentDimensions(mainBoard.getLayoutX() + minusWidth / 2, mainBoard.getLayoutY() + minusHeight / 2, mainBoard.getWidth() - 2 * minusWidth , mainBoard.getHeight() - 2 * minusHeight);
    }
    public static void updateMainBoardLabel() { //todo complete here
        mainBoard.labelsToFront();
        mainBoard.setHP((int) user.getEpsilon().getHP());
        mainBoard.setXP((int) user.getEpsilon().getXp());
        mainBoard.setWave(wave);
        String abilitiesString = "";
        for (int i = 0; i < abilities.size(); i++)
            abilitiesString += abilities.get(i).getName() + "\n";
        mainBoard.setAbilities(abilitiesString);
    }
}
