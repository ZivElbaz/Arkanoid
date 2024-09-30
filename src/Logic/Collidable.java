/**
 * @author Ziv Elbaz
 * @version 1
 * @since 14/06/2023
 */
package Logic;

import Objects.*;
import Game.GameLevel;

import biuoop.DrawSurface;


/**
 * This interface defines the methods necessary for an object to be considered a collidable in a game.
 */
public interface Collidable {
    // Return the "collision shape" of the object.

    /**
     * Returns the Rectangle object that defines the collision shape of this collidable.
     *
     * @return the collision rectangle of this collidable
     */
    Rectangle getCollisionRectangle();

    /**
     * Returns a Line object that represents the collision line between this collidable and a given point.
     *
     * @param p the point at which to get the collision line
     * @return the collision line between this collidable and the given point
     */
    Line getCollisionLine(Point p);

    /**
     * Draws this collidable on the given DrawSurface object.
     *
     * @param d the DrawSurface object to draw on
     */
    void drawOn(DrawSurface d);

    /**
     * Adds this collidable object to the given Game object as both a collidable and a sprite.
     *
     * @param game the game to add this object to
     */
    void addToGame(GameLevel game);

    /**
     * Calculates the new velocity of an object after colliding with this collidable object.
     *
     * @param collisionPoint  the point at which the collision occurred
     * @param currentVelocity the current velocity of the object
     * @param hitter the ball that hit the collidable
     * @return the new velocity of the object after the collision
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
