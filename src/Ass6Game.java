import Game.AnimationRunner;
import Game.GameFlow;
import Levels.DirectHit;
import Levels.LevelInformation;
import Levels.SmokingHot;
import Levels.WideEasy;
import biuoop.GUI;
import Objects.Point;

import java.util.ArrayList;
import java.util.List;

public class Ass6Game {
    private static final int DIRECT_HIT = 1;
    private static final int WIDE_EASY = 2;
    private static final int SMOKING_HOT = 3;

    public static void main(String[] args) {
        GUI gui = new GUI("Arknoid", 800, 600);
        AnimationRunner ar = new AnimationRunner(gui);

        GameFlow gf = new GameFlow(ar, gui.getKeyboardSensor());
        List<Integer> levelNumbers = new ArrayList<>();
        for (String arg : args) {
            try {
                if (Integer.parseInt(arg) > 0
                        && Integer.parseInt(arg) <= 3) {
                    levelNumbers.add(Integer.parseInt(arg));
                }
            } catch (NumberFormatException ignored) {

            }
        }
        List<LevelInformation> levels = new ArrayList<>();
        if (levelNumbers.size() == 0) {
            levels.add(new DirectHit());
            levels.add(new WideEasy());
            levels.add(new SmokingHot());
        } else {
            for (int levelNum : levelNumbers) {
                switch (levelNum) {
                    case DIRECT_HIT:
                        levels.add(new DirectHit());
                        break;
                    case WIDE_EASY:
                        levels.add(new WideEasy());
                        break;
                    case SMOKING_HOT:
                        levels.add(new SmokingHot());
                        break;
                    default:
                        break;
                }
            }
        }

        gf.runLevels(levels);
        gui.close();
    }
}
