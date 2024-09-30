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

import java.util.ArrayList;
import java.util.List;

import java.awt.Color;
import java.util.LinkedList;

/**
 * SmokingHot is a class that implements the LevelInformation interface
 * and represents a specific level called "Smoking Hot".
 */
public class SmokingHot implements LevelInformation {
    private List<Block> blocks = new LinkedList();

    /**
     * Constructs a new SmokingHot object.
     * Initializes the blocks list and creates the blocks for the level.
     */
    public SmokingHot() {
        List blocks = new ArrayList();
        for (int i = 0, j = 220; i < 11; i++) {
            Block block = new Block(new Point(j, 140), 25, 50, Color.gray);
            this.blocks.add(block);
            j += 50;
        }
        for (int i = 0, j = 270; i < 10; i++) {
            Block block = new Block(new Point(j, 165), 25, 50, Color.red);
            this.blocks.add(block);
            j += 50;
        }
        for (int i = 0, j = 320; i < 9; i++) {
            Block block = new Block(new Point(j, 190), 25,
                    50, Color.YELLOW);
            this.blocks.add(block);
            j += 50;
        }
        for (int i = 0, j = 370; i < 8; i++) {
            Block block = new Block(new Point(j, 215), 25, 50, Color.BLUE);
            this.blocks.add(block);
            j += 50;
        }
        for (int i = 0, j = 420; i < 7; i++) {
            Block block = new Block(new Point(j, 240), 25, 50, Color.PINK);
            this.blocks.add(block);
            j += 50;
        }
    }

    /**
     * Returns the number of balls in the level.
     *
     * @return the number of balls
     */
    public int numberOfBalls() {
        return 2;
    }

    /**
     * Returns a list of the initial velocities of the balls.
     *
     * @return the list of initial ball velocities
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> v = new ArrayList<>();
        v.add(Velocity.fromAngleAndSpeed(40, -6));
        v.add(Velocity.fromAngleAndSpeed(-40, -6));
        return v;
    }

    /**
     * Returns the speed of the paddle in pixels per frame.
     *
     * @return the paddle speed
     */
    public int paddleSpeed() {
        return 10;
    }

    /**
     * Returns the width of the paddle in pixels.
     *
     * @return the paddle width
     */
    public int paddleWidth() {
        return 85;
    }

    /**
     * Returns the name of the level.
     *
     * @return the level name
     */
    public String levelName() {
        return "Smoking Hot";
    }

    @Override
    public Sprite getBackground() {
        return new SmokingHotBackground();
    }

    /**
     * Returns a list of the blocks in the level.
     *
     * @return the list of blocks
     */
    public List<Block> blocks() {
        return this.blocks;
    }

    /**
     * Returns the number of blocks that need to be removed to complete the level.
     *
     * @return the number of blocks to remove
     */
    public int numberOfBlocksToRemove() {
        return this.blocks.size();
    }
}
