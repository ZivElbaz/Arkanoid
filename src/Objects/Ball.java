/**
 * @author Ziv Elbaz
 * @version 2
 * @since 02/05/2023
 */
package Objects;

import Game.GameLevel;
import Game.GameEnvironment;
import Logic.CollisionInfo;
import biuoop.DrawSurface;
import Logic.Sprite;


/**
 * Class for representing ball on a 2-D surface.
 */
public class Ball implements Sprite {
    private Point center;
    private final int r;
    private final java.awt.Color color;
    private Velocity v;
    private final GameEnvironment game;

    /**
     * Constructor - creates a new ball with a center point, radius, and color.
     * The ball's velocity is initialized to 0.
     *
     * @param center the center point of the ball
     * @param r      the radius of the ball
     * @param color  the color of the ball
     * @param game   the game of the ball
     */
    public Ball(Point center, int r, java.awt.Color color,
                GameEnvironment game) {
        Velocity v = new Velocity(0, 0); // initialize velocity to 0
        this.center = center;
        this.r = r;
        this.color = color;
        this.v = v;
        this.game = game;
    }

    /**
     * Constructor - creates a new ball with a center point, radius, and color.
     * The ball's velocity is initialized to 0.
     *
     * @param x     the x coordinate of the center point of the ball
     * @param y     the y coordinate of the center point of the ball
     * @param r     the radius of the ball
     * @param color the color of the ball
     * @param game  the game of the ball
     */
    public Ball(double x, double y, int r, java.awt.Color color,
                GameEnvironment game) {
        Point p = new Point(x, y); // create a new point object
        Velocity v = new Velocity(0, 0); // initialize velocity to 0
        this.center = p;
        this.r = r;
        this.color = color;
        this.v = v;
        this.game = game;
    }

    /**
     * Returns the x coordinate of the center point of the ball.
     *
     * @return the x coordinate of the center point of the ball
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Returns the y coordinate of the center point of the ball.
     *
     * @return the y coordinate of the center point of the ball
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Returns the radius of the ball.
     *
     * @return the radius of the ball
     */
    public int getSize() {
        return this.r;
    }

    /**
     * Gets the color of the ball.
     *
     * @return the color of the ball
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Draws the ball on the given DrawSurface.
     *
     * @param surface the DrawSurface to draw the ball on
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.getSize());
    }

    /**
     * sprites must have, calls the moveOneStep function.
     */
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * Sets the velocity of the ball.
     *
     * @param dx the x-component of the velocity
     * @param dy the y-component of the velocity
     */
    public void setVelocity(double dx, double dy) {
        this.v.setVelocity(dx, dy);
    }

    /**
     * Sets the velocity of the ball.
     *
     * @param v the velocity to set
     */
    public void setVelocity(Velocity v) {
        this.v = v;
    }

    /**
     * Gets the velocity of the ball.
     *
     * @return the velocity of the ball
     */
    public Velocity getVelocity() {
        return this.v;
    }

    /**
     * Checks for collisions with other objects after applying the ball's new velocity
     * due to a previous collision.
     */
    private void checkAfterApply() {
        Point trajEndP = this.v.applyToPoint(this.center);
        Line trajectory = new Line(this.center, trajEndP);
        CollisionInfo collision = this.game.getClosestCollision(trajectory);
        if (collision != null) {
            Line collisionLine = collision.collisionObject().getCollisionLine(collision.collisionPoint());
            Rectangle collisionRect = collision.collisionObject().getCollisionRectangle();
            Point collisionPoint = collision.collisionPoint();

            // collision line is the top side of the collidable
            if (collisionLine.equals(collisionRect.getTopSide())) {
                this.center = new Point(collisionPoint.getX(),
                        collisionPoint.getY() + 1);
            } else if (collisionLine.equals(collisionRect.getBottomSide())) {
                // collision line is the bottom side of the collidable
                this.center = new Point(collisionPoint.getX(),
                        collisionPoint.getY() - 1);
            } else if (collisionLine.equals(collisionRect.getLeftSide())) {
                // collision line is the left side of the collidable
                this.center = new Point(collisionPoint.getX() - 1,
                        collisionPoint.getY());
            } else if (collisionLine.equals(collisionRect.getRightSide())) {
                // collision line is the right side of the collidable
                this.center = new Point(collisionPoint.getX() + 1,
                        collisionPoint.getY());
            }
            this.setVelocity(collision.collisionObject().hit(this,
                    collision.collisionPoint(),
                    this.v));
        }
    }

    /**
     * Moves the ball one step according to its velocity and the game environment, and checks for collisions
     * with other objects.
     */
    public void moveOneStep() {
        Point trajEndP = this.v.applyToPoint(this.center);
        Line trajectory = new Line(this.center, trajEndP);
        CollisionInfo collision = this.game.getClosestCollision(trajectory);
        if (collision != null) {
            Line collisionLine = collision.collisionObject().getCollisionLine(collision.collisionPoint());
            Rectangle collisionRect = collision.collisionObject().getCollisionRectangle();
            Point collisionPoint = collision.collisionPoint();

            // collision line is the top side of the collidable
            if (collisionLine.equals(collisionRect.getTopSide())) {
                this.center = new Point(collisionPoint.getX(), collisionPoint.getY() + 1);
            } else if (collisionLine.equals(collisionRect.getBottomSide())) {
                // collision line is the bottom side of the collidable
                this.center = new Point(collisionPoint.getX(), collisionPoint.getY() - 1);
            } else if (collisionLine.equals(collisionRect.getLeftSide())) {
                // collision line is the left side of the collidable
                this.center = new Point(collisionPoint.getX() - 1, collisionPoint.getY());
            } else if (collisionLine.equals(collisionRect.getRightSide())) {
                // collision line is the right side of the collidable
                this.center = new Point(collisionPoint.getX() + 1, collisionPoint.getY());
            }
            this.setVelocity(collision.collisionObject().hit(this,
                    collision.collisionPoint(), this.v));

            // check for additional collisions after applying the new velocity
            checkAfterApply();
        }
        this.center = this.v.applyToPoint(this.center);
    }

    /**
     * Removes the ball from the specified game.
     *
     * @param g the game to remove the ball from
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }

}

