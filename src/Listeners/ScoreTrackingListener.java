/**
 * @author Ziv Elbaz
 * @version 1
 * @since 05/06/2023
 */
package Listeners;

import Logic.Block;
import Logic.Counter;
import Objects.Ball;

/**
 * ScoreTrackingListener is a class that implements the HitListener interface
 * and is responsible for tracking the score of the player when blocks are hit.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Constractor
     *
     * @param scoreCounter the value of the counter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
    }

}