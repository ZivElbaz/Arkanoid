/**
 * @author Ziv Elbaz
 * @version 1
 * @since 14/06/2023
 */
package Logic;

import Listeners.HitListener;
import Listeners.HitNotifier;
import Objects.Ball;
import Objects.Line;
import Objects.Rectangle;
import Objects.Velocity;
import Game.GameLevel;
import biuoop.DrawSurface;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The Logic.Block class represents a rectangle-shaped block that can be hit and collided
 * with by other objects in the game.
 * It implements the Objects.Logic.Collidable and Objects.Logic.Sprite interfaces, allowing it to be added to the game
 * environment as a collidable object and a sprite that can be drawn on the
 * game screen.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    /**
     * The rectangle shape of the block.
     */
    private final Objects.Rectangle rect;
    private List<HitListener> hitListeners;
    /**
     * A flag indicating whether the block was hit by another object.
     */
    private boolean ifHit;

    /**
     * Constructs a new Logic.Block with a given rectangle shape.
     *
     * @param rect the rectangle shape of the block.
     */
    public Block(Rectangle rect) {
        this.rect = rect;
        this.ifHit = false;
        this.hitListeners = new ArrayList<HitListener>();

    }

    /**
     * Constructs a new Logic.Block with a given rectangle shape and hit flag.
     *
     * @param rect  the rectangle shape of the block.
     * @param ifHit a flag indicating whether the block was hit by another object.
     */
    public Block(Objects.Rectangle rect, boolean ifHit) {
        this.rect = rect;
        this.ifHit = ifHit;
        this.hitListeners = new ArrayList<HitListener>();

    }

    /**
     * Constructs a new Logic.Block with a given position, size, and color.
     *
     * @param topLeft the position of the top-left corner of the block.
     * @param height  the height of the block.
     * @param width   the width of the block.
     * @param color   the color of the block.
     */
    public Block(Objects.Point topLeft, double height, double width, Color color) {
        this.hitListeners = new ArrayList<HitListener>();
        this.rect = new Objects.Rectangle(topLeft, width, height, color);
        this.ifHit = false;
    }

    /**
     * Sets the color of the block.
     *
     * @param color the color to set.
     */
    public void setColor(Color color) {
        this.rect.setColor(color);
    }

    /**
     * Returns the collision rectangle of the block.
     *
     * @return the collision rectangle of the block.
     */
    public Objects.Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * Returns the collision line of the block at a given collision point.
     *
     * @param p the collision point.
     * @return the collision line of the block at the given collision point.
     */
    public Line getCollisionLine(Objects.Point p) {
        java.util.List<Line> lines = this.rect.getLines();
        for (int i = 0; i < 4; i++) {
            if (lines.get(i).isOn(p)) {
                return lines.get(i);
            }
        }
        return null;
    }

    /**
     * Calculates and returns the new velocity of an object that hit the block
     * at a given collision point and with a given current velocity.
     *
     * @param collisionPoint  the point of collision.
     * @param currentVelocity the current velocity of the object.
     * @param hitter the hitter ball
     * @return the new velocity of the object after the collision with the block.
     */
    public Velocity hit(Ball hitter, Objects.Point collisionPoint,
                        Velocity currentVelocity) {
        java.util.List<Line> lines = this.rect.getLines();
        Velocity newV = currentVelocity;
        for (int i = 0; i < 4; i++) {
            if (lines.get(i).isOn(collisionPoint)) {
                // hits one of the horizontal sides of the block
                if (i == 0) {
                    newV.setVelocity(currentVelocity.getDx(),
                            Math.abs(currentVelocity.getDy()) * -1);
                } else if (i == 1) {
                    newV.setVelocity(currentVelocity.getDx(),
                            Math.abs(currentVelocity.getDy()));
                } else if (i == 2) {
                    // hits one of the vertical sides of the block
                    newV.setVelocity(Math.abs(currentVelocity.getDx()) * -1,
                            currentVelocity.getDy());
                } else if (i == 3) {
                    newV.setVelocity(Math.abs(currentVelocity.getDx()),
                            currentVelocity.getDy());
                }
            }
        }
        this.notifyHit(hitter);
        this.ifHit = true;
        return newV;
    }

    /**
     * Draws a rectangle on a given DrawSurface object.
     * The rectangle's color is set using this.rect.getColor().
     * The rectangle's position and size are determined by the rect object's properties.
     * The rectangle is filled and then a black border is drawn around it.
     *
     * @param d the drawing surface to draw the block on
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.rect.getColor());
        d.fillRectangle((int) this.rect.getUpperLeft().getX(),
                (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());
        d.setColor(Color.black);
        d.drawRectangle((int) this.rect.getUpperLeft().getX(),
                (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());
    }

    /**
     * Adds this object as both a collidable and a sprite to the given game.
     *
     * @param game the game to add this object to
     */
    public void addToGame(GameLevel game) {
        game.addCollidable(this);
        game.addSprite(this);
    }

    /**
     * needs to be implemented by the sprite interface will probably get
     * defined later in the project.
     */
    public void timePassed() {
    }

    /**
     * Notifies all registered hit listeners about a hit event.
     *
     * @param hitter the ball that hit the block.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
}

