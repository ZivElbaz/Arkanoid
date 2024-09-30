/**
 * @author Ziv Elbaz
 * @version 1
 * @since 14/06/2023
 */
package Levels;

import Logic.Block;
import Logic.Sprite;
import Objects.Point;
import Objects.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * WideEasy is a class that implements the LevelInformation interface and
 * represents a specific level called "Wide Easy".
 */
public class WideEasy implements LevelInformation {
    /**
     * Constructs a new instance of the WideEasy class.
     */
    public WideEasy() {
    }

    /**
     * Returns the number of balls in the level.
     *
     * @return the number of balls
     */
    public int numberOfBalls() {
        return 10;
    }

    /**
     * Returns a list of initial velocities for the balls in the level.
     *
     * @return a list of initial velocities
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> v = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            if (i == 5){
                continue;
            }
            v.add(Velocity.fromAngleAndSpeed(50 - (i * 10), -10));
        }
        return v;
    }

    /**
     * Returns the speed of the paddle in pixels per frame.
     *
     * @return the paddle speed
     */
    public int paddleSpeed() {
        return 5;
    }

    /**
     * Returns the width of the paddle.
     *
     * @return the paddle width
     */
    public int paddleWidth() {
        return 600;
    }

    /**
     * Returns the name of the level.
     *
     * @return the level name
     */
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        return new WideEasyBackground();
    }

    /**
     * Returns a list of blocks in the level.
     *
     * @return the list of blocks
     */
    public List<Block> blocks() {
        List<Block> b = new ArrayList<Block>();
        for (int i = 0; i < 15; i++) {
            b.add(new Block(new Point(30 + (i * 49), 250), 20, 49,
                    new Color((int) (Math.random() * 256),
                            (int) (Math.random() * 256),
                            (int) (Math.random() * 256))));
        }
        return b;
    }

    /**
     * Returns the number of blocks that need to be removed to clear the level.
     *
     * @return the number of blocks to remove
     */
    public int numberOfBlocksToRemove() {
        return 15;
    }
}
