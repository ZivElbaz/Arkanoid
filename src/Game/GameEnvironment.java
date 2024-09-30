/**
 * @author Ziv Elbaz
 * @version 1
 * @since 14/06/2023
 */
package Game;

import Logic.Collidable;
import Logic.CollisionInfo;
import Objects.Line;
import Objects.Point;

import java.util.ArrayList;
import java.util.List;


/**
 * The Game.GameEnvironment class represents the environment of the game, which contains a collection of collidables.
 * It can add a new collidable object to the environment and get the collection of collidables.
 * It can also find the closest collision of an object with the collidables in the environment.
 */
public class GameEnvironment {

    // The list of collidable objects in the environment.
    private List<Collidable> collidables;

    /**
     * Constructor that initializes a new empty Game.GameEnvironment object.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }

    /**
     * Returns the list of collidables in the environment.
     *
     * @return the list of collidables in the environment
     */
    public List<Collidable> getCollidables() {
        return this.collidables;
    }

    /**
     * Adds the given collidable object to the environment.
     *
     * @param c the collidable object to add
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * Removes the given collidable object from the environment.
     *
     * @param c the collidable object to remove
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }

    /**
     * Finds the closest collision point of an object moving along the given trajectory
     * with the collidables in the environment. Returns a Logic.CollisionInfo object that contains
     * information about the closest collision, or null if no collision occurs.
     *
     * @param trajectory the line representing the movement of the object
     * @return a Logic.CollisionInfo object that contains information about the closest collision,
     * or null if no collision occurs
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        CollisionInfo collision = null;
        for (Collidable c : this.collidables) {
            if (c.getCollisionRectangle().intersectionPoints(trajectory) != null) {
                Point cur = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());
                if (collision == null) {
                    collision = new CollisionInfo(cur, c);
                }
                if (cur.distance(trajectory.start())
                        < collision.collisionPoint().distance(trajectory.start())) {
                    collision.setCollisionPoint(cur);
                    collision.setCollidable(c);
                }
            }
        }
        return collision;
    }
}