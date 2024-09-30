/**
 * @author Ziv Elbaz
 * @version 1
 * @since 14/06/2023
 */
package Game;

import Levels.LevelInformation;
import Logic.Counter;
import biuoop.KeyboardSensor;

import java.util.List;

/**
 * The GameFlow class handles the flow of the game, including running levels and managing the game score.
 * It utilizes the AnimationRunner and KeyboardSensor to control the game's animations and keyboard inputs.
 * The runLevels method runs a list of levels sequentially and displays
 * appropriate end screens based on the outcome.
 */
public class GameFlow {
    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;
    private Counter gameScore;

    /**
     * Constructs a new GameFlow object with the specified AnimationRunner and KeyboardSensor.
     *
     * @param ar the AnimationRunner for controlling the game's animations
     * @param ks the KeyboardSensor for capturing keyboard inputs
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        gameScore = new Counter();
    }

    /**
     * Runs a list of levels sequentially and displays the appropriate end screens based on the outcome.
     *
     * @param levels the list of LevelInformation objects representing the levels to run
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor,
                    this.animationRunner, this.gameScore);

            level.initialize();
            level.run();
            if (level.getRemainingBalls() == levelInfo.numberOfBalls() * -1) {
                this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor,
                        KeyboardSensor.SPACE_KEY,
                        new LoseEndScreen(this.keyboardSensor,
                                this.gameScore.getValue())));
                System.exit(0);
            }
        }
        this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor,
                KeyboardSensor.SPACE_KEY,
                new WinEndScreen(this.keyboardSensor,
                        this.gameScore.getValue())));
        System.exit(0);
    }
}