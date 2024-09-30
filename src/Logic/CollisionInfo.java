/**
 * @author Ziv Elbaz
 * @version 1
 * @since 02/05/2023
 */
package Logic;

import Objects.Point;

/**
 * This class represents a collision between a ball and a collidable object.
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collidable;

    /**
     * Creates a new Logic.CollisionInfo object with the given collision point and collidable object.
     *
     * @param collisionPoint the point at which the collision occurred
     * @param collidable     the collidable object involved in the collision
     */
    public CollisionInfo(Point collisionPoint, Collidable collidable) {
        this.collisionPoint = collisionPoint;
        this.collidable = collidable;
    }

    /**
     * Returns the point at which the collision occurred.
     *
     * @return the collision point
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * Returns the collidable object involved in the collision.
     *
     * @return the collidable object
     */
    public Collidable collisionObject() {
        return this.collidable;
    }

    /**
     * Sets the collision point of this Logic.CollisionInfo object to the given point.
     *
     * @param collisionPoint the new collision point
     */
    public void setCollisionPoint(Point collisionPoint) {
        this.collisionPoint = collisionPoint;
    }

    /**
     * Sets the collidable object of this Logic.CollisionInfo object to the given collidable.
     *
     * @param collidable the new collidable object
     */
    public void setCollidable(Collidable collidable) {
        this.collidable = collidable;
    }
}