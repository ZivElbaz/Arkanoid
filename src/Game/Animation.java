/**
 * @author Ziv Elbaz
 * @version 1
 * @since 14/06/2023
 */
package Game;

import biuoop.DrawSurface;

/**
 * The Animation interface represents an animation that can be displayed on a DrawSurface.
 * It provides methods to update the animation and check if it should stop.
 */
public interface Animation {
    /**
     * Perform one frame of the animation.
     *
     * @param d the DrawSurface to draw on
     */
    void doOneFrame(DrawSurface d);

    /**
     * Check if the animation should stop.
     *
     * @return true if the animation should stop, false otherwise
     */
    boolean shouldStop();
}
