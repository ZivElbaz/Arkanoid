/**
 * @author Ziv Elbaz
 * @version 1
 * @since 14/06/2023
 */
package Levels;

import Logic.Block;
import Objects.Point;
import Objects.Rectangle;
import Objects.Velocity;
import Logic.Sprite;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

/**
 * DirectHit is a class that represents a specific level called "Direct Hit".
 * It implements the LevelInformation interface.
 */
public class DirectHit implements LevelInformation {
    public DirectHit() {
    }

    @Override
    public int numberOfBalls() {
        return initialBallVelocities().size();
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velList = new LinkedList<Velocity>();
        velList.add(Velocity.fromAngleAndSpeed(0, -4));
        return velList;
    }

    @Override
    public int paddleSpeed() {
        return 16;
    }

    @Override
    public int paddleWidth() {
        return 150;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        return new DirectHitBackground(blocks().get(0));
    }

    @Override
    public List<Block> blocks() {
        List<Block> blockList = new LinkedList<Block>();
        blockList.add(creatGameBlock());
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }

    /**
     * Creates and returns the game block for the level.
     *
     * @return the game block
     */
    private Block creatGameBlock() {
        Point startP = new Point(385, 160);
        Rectangle blockRec = new Rectangle(startP, 30, 30);
        Block gameBlock = new Block(blockRec);
        gameBlock.setColor(Color.RED);
        return gameBlock;
    }
}
