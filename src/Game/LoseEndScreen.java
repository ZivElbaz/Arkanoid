/**
 * @author Ziv Elbaz
 * @version 1
 * @since 14/06/2023
 */
package Game;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * LoseEndScreen is an animation that represents the end screen when the player loses the game.
 */
public class LoseEndScreen implements Animation {
    private KeyboardSensor keyboard;
    private int score;
    private boolean stop;

    /**
     * Constructor for LoseEndScreen.
     *
     * @param k the KeyboardSensor used to detect key presses
     * @param score    the player's score
     */
    public LoseEndScreen(KeyboardSensor k, int score) {
        this.keyboard = k;
        this.score = score;
        this.stop = false;
    }

    /**
     * Performs one frame of the animation.
     * Draws the end screen text and checks if the SPACE key is pressed to stop the animation.
     *
     * @param d the DrawSurface to draw on
     */
    public void doOneFrame(DrawSurface d) {
        d.drawText(120, d.getHeight() / 2,
                "You lost! - your score was " + score, 40);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }

    /**
     * Checks if the animation should stop.
     *
     * @return true if the animation should stop, false otherwise
     */
    public boolean shouldStop() {
        return this.stop;
    }
}