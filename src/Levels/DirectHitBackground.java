/**
 * @author Ziv Elbaz
 * @version 1
 * @since 14/06/2023
 */
package Levels;

import biuoop.DrawSurface;
import Logic.Block;
import Logic.Sprite;

import java.awt.*;

/**
 * DirectHitBackground is a class that represents the background sprite for the "Direct Hit" level.
 * It implements the Sprite interface.
 */
public class DirectHitBackground implements Sprite {
    private Block block;

    /**
     * the block to draw
     *
     * @param block
     */
    public DirectHitBackground(Block block) {
        this.block = block;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, 800, 600);

        d.setColor(Color.BLUE);

        Point middleTopLine =
                new Point((int) this.block.getCollisionRectangle().getLines().get(0).middle().getX(),
                        (int) this.block.getCollisionRectangle().getLines().get(0).middle().getY());
        Point middleBottomLine =
                new Point((int) this.block.getCollisionRectangle().getLines().get(1).middle().getX(),
                        (int) this.block.getCollisionRectangle().getLines().get(1).middle().getY());
        Point middleLeftLine =
                new Point((int) this.block.getCollisionRectangle().getLines().get(2).middle().getX(),
                        (int) this.block.getCollisionRectangle().getLines().get(2).middle().getY());
        Point middleRightLine =
                new Point((int) this.block.getCollisionRectangle().getLines().get(2).middle().getX(),
                        (int) this.block.getCollisionRectangle().getLines().get(2).middle().getY());

        d.drawCircle((int) middleTopLine.getX(), (int) middleLeftLine.getY(),
                80); //smallest circle
        d.drawCircle((int) middleTopLine.getX(), (int) middleLeftLine.getY(),
                100); //middle circle
        d.drawCircle((int) middleTopLine.getX(), (int) middleLeftLine.getY(),
                120); //biggest circle

        d.drawLine((int) middleTopLine.getX(), (int) middleTopLine.getY(), (int) middleTopLine.getX(),
                (int) middleTopLine.getY() - 110);
        d.drawLine((int) middleBottomLine.getX(), (int) middleBottomLine.getY(), (int) middleBottomLine.getX(),
                (int) middleBottomLine.getY() + 110);
        d.drawLine((int) middleLeftLine.getX(), (int) middleLeftLine.getY(), (int) middleLeftLine.getX() - 110,
                (int) middleLeftLine.getY());
        d.drawLine((int) middleRightLine.getX(), (int) middleRightLine.getY(), (int) middleRightLine.getX() + 110,
                (int) middleRightLine.getY());
    }

    @Override
    public void timePassed() {
    }
}