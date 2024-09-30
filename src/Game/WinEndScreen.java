/**
 * @author Ziv Elbaz
 * @version 1
 * @since 14/06/2023
 */
package Game;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * WinEndScreen is an animation that represents the end screen displayed when the player wins the game.
 */
public class WinEndScreen implements Animation {
    private KeyboardSensor keyboard;
    private int score;
    private boolean stop;

    /**
     * Constructor for WinEndScreen.
     *
     * @param k the KeyboardSensor used to detect key presses
     * @param score    the player's score
     */
    public WinEndScreen(KeyboardSensor k, int score) {
        this.keyboard = k;
        this.score = score;
        this.stop = false;
    }

    /**
     * Performs one frame of the animation.
     * Draws the win end screen text and checks if the SPACE key is pressed to stop the animation.
     *
     * @param d the DrawSurface to draw on
     */
    public void doOneFrame(DrawSurface d) {
        d.drawText(120, d.getHeight() / 2,
                "You WON! - your score was " + score, 40);
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