package bahar.window_kill.client.control.util.reader;

import bahar.window_kill.client.model.Deck;

import java.util.Scanner;

public class SaveUtil extends ModelUtil {

    @Override
    public String write(Object object) {
        return null;
    }

//    public String write() {
//        StringBuilder sb = new StringBuilder();
//        sb.append(wave).append("\n");
//        sb.append(clock).append("\n");
//        sb.append(coolDown).append(" ").append(isLocked).append(" ").append(shrink).append("\n");
//        sb.append(mainBoard.getLayoutX()).append(" ").append(mainBoard.getLayoutY()).append("\n");
//        sb.append(mainBoard.getWidth()).append(" ").append(mainBoard.getHeight()).append("\n");
//        sb.append(user.getEpsilon().getHP()).append("\n");
//        sb.append(user.getEpsilon().getLayoutX()).append(" ").append(user.getEpsilon().getLayoutY()).append("\n");
//        sb.append(abilities.size()).append("\n");
//        for (AbilityWatch abilityWatch: abilities)
//            sb.append((new WatchUtil()).write(abilityWatch)).append("\n");
//        sb.append(countEntities()).append("\n");
//        for (Entity entity: entities)
//            if (!(entity instanceof BlackOrbLaser) && !(entity instanceof Bullet) && !(entity instanceof Collectable))
//                sb.append((new EntityUtil()).write(entity)).append("\n"); //todo correct here
//        return sb.toString();
//    }
//    private int countEntities() {
//        int count = 0;
//        for (Entity entity: entities)
//            if (!(entity instanceof BlackOrbLaser) && !(entity instanceof Bullet) && !(entity instanceof Collectable))
//                count++; //todo correct here!
//        return count;
//    }
//    protected static void addEntity(Entity entity) { //hopefully this will work
//        if (entity instanceof BoardOwner) {
//            BoardOwner boardOwner = (BoardOwner) entity;
//            MainStage.add(boardOwner.getBoard()); gameBoards.add(boardOwner.getBoard());
//            MainStage.add(entity.getView()); entities.add(entity);
//            boardOwner.getBoard().toBack();
//        } else {
//            mainBoard.add(entity.getView());
//            entities.add(entity);
//        }
//    }

    @Override
    public Object read(Scanner sc) {
//        Deck deck = new Deck();
//        deck.wave = sc.nextInt();
//        deck.clock = sc.nextLong();
//        coolDown = sc.nextLong(); isLocked = sc.nextBoolean(); shrink = sc.nextDouble();
//        //empty containers
//        MainStage.newScene();
//        //read mainBoard and user
//        double layoutX = sc.nextDouble(), layoutY = sc.nextDouble(), width = sc.nextDouble(), height = sc.nextDouble();
//        user.getEpsilon().setHP(sc.nextDouble());
//        user.getEpsilon().setLayoutX(sc.nextDouble()); user.getEpsilon().setLayoutY(sc.nextDouble());
//        // make mainBoard
//        mainBoard.setIndependentDimensions(layoutX, layoutY, width, height);
//        MainStage.add(mainBoard);
//        mainBoard.setXP(user.getEpsilon().getXp()); mainBoard.setHP((int) user.getEpsilon().getHP());
//        mainBoard.clear();
//        mainBoard.add(user.getEpsilon().getView());
//        mainBoard.requestFocus();
//        //read abilities
//        abilities = new ArrayList<>();
//        int n = sc.nextInt();
//        for (int i = 0; i < n; i++) {
//            AbilityWatch abilityWatch = (AbilityWatch) (new WatchUtil()).read(sc);
//            abilities.add(abilityWatch);
//            abilityWatch.call();
//        }
//        n = sc.nextInt();
//        entities = new ArrayList<>();
//        ArrayList<BlackOrb> blackOrbs = new ArrayList<>();
//        for (int i = 0; i < n; i++) {
//            Entity entity = (new EntityUtil()).read(sc);
//            addEntity(entity);
//            if (entity instanceof BlackOrb)
//                blackOrbs.add((BlackOrb) entity);
//        }
//        for (int i = 0; i < blackOrbs.size(); i++)
//            for (int j = 0; j < i; j++)
//                addEntity(new BlackOrbLaser(blackOrbs.get(i), blackOrbs.get(j)));
        return null;
    }
}
