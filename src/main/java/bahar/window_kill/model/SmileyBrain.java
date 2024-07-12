package bahar.window_kill.model;

import bahar.window_kill.control.Constants;
import bahar.window_kill.model.bosswatch.ComingInWatch;
import bahar.window_kill.model.entities.SmileyFace;
import bahar.window_kill.model.entities.SmileyHand;

import java.util.ArrayList;

public class SmileyBrain {
    private final SmileyFace face;
    private final SmileyHand leftHand, rightHand;
    ArrayList<Watch> watches = new ArrayList<>();
    public SmileyBrain(SmileyFace face, SmileyHand leftHand, SmileyHand rightHand) {
        this.face = face;
        this.leftHand = leftHand;
        this.rightHand = rightHand;
        face.setSceneLocation(Constants.SCREEN_WIDTH / 2, -100);
        leftHand.setSceneLocation(-100, Constants.SCREEN_HEIGHT / 2);
        rightHand.setSceneLocation(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT / 2);
        watches.add(new ComingInWatch(face, leftHand, rightHand));
    }
    public void act() {
        for (Watch watch: watches)
            watch.call();
        for (int i = watches.size() - 1; i >= 0; i--)
            if (watches.get(i).getCycleCount() == 0)
                watches.remove(i);
    }
}
