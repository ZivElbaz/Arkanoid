/**
 * @author Ziv Elbaz
 * @version 1
 * @since 14/06/2023
 */
package Game;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * PauseScreen is an animation that represents the pause screen.
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * Constructor for PauseScreen.
     *
     * @param k the KeyboardSensor used to detect key presses
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }

    /**
     * Performs one frame of the animation.
     * Draws the pause screen text and checks if the SPACE key is pressed to stop the animation.
     *
     * @param d the DrawSurface to draw on
     */
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
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