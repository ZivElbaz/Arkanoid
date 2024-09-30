/**
 * @author Ziv Elbaz
 * @version 1
 * @since 14/06/2023
 */
package Listeners;

import Logic.Block;
import Objects.Ball;

/**
 * HitListener is an interface that represents a listener for hit events.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     *
     * @param beingHit the block that got hit
     * @param hitter   the hitter ball
     */
    void hitEvent(Block beingHit, Ball hitter);
}