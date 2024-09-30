package Objects; /**
 * @author Ziv Elbaz
 * @version 2
 * @since 02/05/2023
 */

/**
 * Represents a velocity with a direction and magnitude.
 */
public class Velocity {

    /**
     * The horizontal component of the velocity.
     */
    private double dx;

    /**
     * The vertical component of the velocity.
     */
    private double dy;

    /**
     * Constructs a new Objects.Velocity object with the given horizontal and vertical components.
     *
     * @param dx the horizontal component of the velocity
     * @param dy the vertical component of the velocity
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Returns a new Objects.Velocity object with the given angle and speed.
     *
     * @param angle the angle of the velocity in degrees
     * @param speed the magnitude of the velocity
     * @return a new Objects.Velocity object with the given angle and speed
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = Math.sin(Math.toRadians(angle)) * speed;
        double dy = Math.cos(Math.toRadians(angle)) * speed;
        return new Velocity(dx, dy);
    }

    /**
     * Applies this velocity to the given point and returns a new point.
     *
     * @param p the point to apply the velocity to
     * @return a new point with the velocity applied to it
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }

    /**
     * Sets the horizontal and vertical components of this velocity to the given values.
     *
     * @param dx the new horizontal component of the velocity
     * @param dy the new vertical component of the velocity
     */
    public void setVelocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Returns the horizontal component of this velocity.
     *
     * @return the horizontal component of the velocity
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Returns the vertical component of this velocity.
     *
     * @return the vertical component of the velocity
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Returns the angle of this velocity.
     *
     * @return the angle of this velocity.
     */
    public double getAngle() {
        return (Math.atan(this.dy / this.dx) * 180 / Math.PI);
    }

    /**
     * Calculates and returns the speed of this velocity.
     *
     * @return the speed of this velocity.
     */
    public double getSpeed() {
        return Math.sqrt(Math.pow(this.dx, 2) + Math.pow(this.dy, 2));
    }
}
