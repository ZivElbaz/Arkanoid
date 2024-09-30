/**
 * @author Ziv Elbaz
 * @version 1
 * @since 05/06/2023
 */
package Listeners;

import Game.GameLevel;
import Logic.Block;
import Logic.Counter;
import Objects.Ball;

/**
 * a BlockRemover is in charge of removing blocks from the game, as well as
 * keeping count
 * of the number of blocks that remain.
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter counter;

    /**
     * Removes the ball from the game.
     *
     * @param game the game
     * @param counter the pointer for the ball counter
     */
    public BallRemover(GameLevel game, Counter counter) {
        this.game = game;
        this.counter = counter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        this.counter.decrease(1);
    }
}