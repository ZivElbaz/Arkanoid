package Objects; /**
 * @author Ziv Elbaz
 * @version 1
 * @since 24/04/2023
 */

/**
 * Represents a point in 2D space with an x and y coordinate.
 */
public class Point {
    private final double x;
    private final double y;
    private static final double EPSILON = Math.pow(10, -7);

    /**
     * Creates a new point with the given x and y coordinates.
     *
     * @param x the x-coordinate of the point
     * @param y the y-coordinate of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Calculates and returns the distance between this point and the given point.
     *
     * @param pointToMeasure the point to measure the distance to
     * @return the distance between this point and the given point
     */
    public double distance(Point pointToMeasure) {
        double disX = Math.pow(this.x - pointToMeasure.getX(), 2);
        double disY = Math.pow(this.y - pointToMeasure.getY(), 2);
        return Math.sqrt(disX + disY);
    }

    /**
     * Compares this point with the given point and returns true if
     * they are within a small error margin, false otherwise.
     *
     * @param pointToCompare the point to compare to
     * @return true if this point is equal to the given point, false otherwise
     */
    public boolean equals(Point pointToCompare) {
        double marginX = Math.abs(this.x - pointToCompare.getX());
        double marginY = Math.abs(this.y - pointToCompare.getY());
        return (marginX <= EPSILON && marginY <= EPSILON);
    }

    /**
     * Returns the x-coordinate of this point.
     *
     * @return the x-coordinate of this point
     */
    public double getX() {
        return this.x;
    }

    /**
     * Returns the y-coordinate of this point.
     *
     * @return the y-coordinate of this point
     */
    public double getY() {
        return this.y;
    }

}
