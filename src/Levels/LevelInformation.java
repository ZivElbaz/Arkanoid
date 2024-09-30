/**
 * @author Ziv Elbaz
 * @version 1
 * @since 14/06/2023
 */
package Levels;

import Logic.Block;
import Logic.Sprite;
import Objects.Velocity;

import java.util.List;

/**
 * LevelInformation is an interface that represents the information for a specific level in the game.
 * It provides methods to retrieve various properties of the level.
 */
public interface LevelInformation {
    /**
     * Returns the number of balls in the level.
     *
     * @return the number of balls
     */
    int numberOfBalls();

    /**
     * Returns a list of the initial velocities of the balls in the level.
     *
     * @return the list of initial ball velocities
     */
    List<Velocity> initialBallVelocities();

    /**
     * Returns the speed of the paddle in the level.
     *
     * @return the paddle speed
     */
    int paddleSpeed();

    /**
     * Returns the width of the paddle in the level.
     *
     * @return the paddle width
     */
    int paddleWidth();

    /**
     * Returns the name of the level.
     *
     * @return the level name
     */
    String levelName();

    /**
     * Returns the background sprite of the level.
     *
     * @return the background sprite
     */
    Sprite getBackground();

    /**
     * Returns a list of the blocks in the level.
     *
     * @return the list of blocks
     */
    List<Block> blocks();

    /**
     * Returns the number of blocks that should be removed to complete the level.
     *
     * @return the number of blocks to remove
     */
    int numberOfBlocksToRemove();
}
