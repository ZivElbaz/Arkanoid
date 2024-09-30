/**
 * @author Ziv Elbaz
 * @version 1
 * @since 14/06/2023
 */
package Logic;

import biuoop.DrawSurface;

/**
 * The Logic.Sprite interface represents objects that can be drawn on a DrawSurface and updated with time.
 */
public interface Sprite {
    /**
     * Draws the sprite on the given DrawSurface.
     *
     * @param d the DrawSurface to draw the sprite on
     */
    void drawOn(DrawSurface d);

    /**
     * Notifies the sprite that a unit of time has passed.
     */
    void timePassed();
}