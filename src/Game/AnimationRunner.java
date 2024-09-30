/**
 * @author Ziv Elbaz
 * @version 1
 * @since 14/06/2023
 */
package Game;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * The AnimationRunner class is responsible for running animations on a GUI.
 * It provides methods to run animations and get the dimensions of the game window.
 * It uses a GUI, Sleeper, and DrawSurface for animation control and rendering.
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;

    /**
     * Constructs an AnimationRunner with the specified GUI.
     *
     * @param gui the GUI to run animations on
     */
    public AnimationRunner(GUI gui) {
        this.gui = gui;
        this.sleeper = new Sleeper();
        this.framesPerSecond = 60;
    }

    /**
     * Returns the width of the game window.
     *
     * @return the width of the game window
     */
    public int getGameWidth() {
        return this.gui.getDrawSurface().getWidth();
    }

    /**
     * Returns the height of the game window.
     *
     * @return the height of the game window
     */
    public int getGameHeight() {
        return this.gui.getDrawSurface().getHeight();
    }

    /**
     * Runs the specified animation.
     *
     * @param animation the animation to run
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();

            animation.doOneFrame(d);

            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    /**
     * Runs the specified animation, only relevant for the countdown animation.
     *
     * @param animation the animation to run
     * @param flag      a flag indicating whether to run the animation for
     *                  the countdown animation
     */
    public void run(Animation animation, boolean flag) {
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();

            animation.doOneFrame(d);

            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(1000 - milliSecondLeftToSleep);
            }
        }
    }
}