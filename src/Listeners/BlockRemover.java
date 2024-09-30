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
 * keeping count of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter removedBlocks;

    /**
     * removes the block from the game.
     *
     * @param game          the game
     * @param removedBlocks number of removed blocks
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.removedBlocks = removedBlocks;
    }

    /**
     * Blocks that are hit should be removed
     * from the game. Remember to remove this listener from the block
     * that is being removed from the game.
     *
     * @param beingHit the block that got hit
     * @param hitter the hitter ball
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        this.game.removeSprite(beingHit);
        this.game.removeCollidable(beingHit);
        this.removedBlocks.increase(1);
        this.game.blockRemoved();
    }

}