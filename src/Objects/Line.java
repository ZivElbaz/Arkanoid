package Objects; /**
 * @author Ziv Elbaz
 * @version 2
 * @since 02/05/2023
 */

/**
 * A class representing a line segment in two-dimensional space.
 */
public class Line {
    static final double EPSILON = Math.pow(10, -7);
    private final Point start; // The start point of the line segment
    private final Point end; // The end point of the line segment

    /**
     * Constructs a new Objects.Line object given the start and end points.
     *
     * @param start The start point of the line segment.
     * @param end   The end point of the line segment.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Constructs a new Objects.Line object given the x and y coordinates of the start and end points.
     *
     * @param x1 The x-coordinate of the first point.
     * @param y1 The y-coordinate of the first point.
     * @param x2 The x-coordinate of the second point.
     * @param y2 The y-coordinate of the second point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * Returns the length of the line segment.
     *
     * @return The length of the line segment.
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * Returns the middle point of the line segment.
     *
     * @return The middle point of the line segment.
     */
    public Point middle() {
        double midX = (this.end.getX() + this.start.getX()) / 2;
        double midY = (this.end.getY() + this.start.getY()) / 2;
        return new Point(midX, midY);
    }

    /**
     * Returns the start point of the line segment.
     *
     * @return The start point of the line segment.
     */
    public Point start() {
        return this.start;
    }

    /**
     * Returns the end point of the line segment.
     *
     * @return The end point of the line segment.
     */
    public Point end() {
        return this.end;
    }

    /**
     * Returns whether the line segment is parallel to the Y-axis.
     *
     * @return True if the line segment is parallel to the Y-axis, false otherwise.
     */
    private boolean isVertical() {
        return (this.start.getX() == this.end.getX());
    }

    /**
     * Returns the slope of the line segment.
     *
     * @return The slope of the line segment.
     */
    private double getSlope() {
        // m = (y1 - y2) / (x1 - x2)
        return ((this.end.getY() - this.start.getY())
                / (this.end.getX() - this.start.getX()));
    }

    /**
     * Returns the y-intercept of the line segment.
     *
     * @return The y-intercept of the line segment.
     */
    private double getB() {
        // y - mx = b
        return (this.start.getY() - (this.start.getX() * this.getSlope()));
    }

    // Returns true if the lines are overlapping, false otherwise

    /**
     * Determines whether this line overlaps with another line.
     *
     * @param other the other line to check for overlap
     * @return true if the lines overlap, false otherwise
     */
    private boolean isOverlapping(Line other) {
        if (this.isVertical() && other.isVertical()) {
            return isOverlappingVertical(other);
        }
        if (!this.isVertical() && !other.isVertical()) {
            if (this.getSlope() != other.getSlope()) {
                return false;
            }
            if (this.start.distance(other.start()) < this.length()) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method checks if a given point is on the line segment.
     *
     * @param p the point to check if it's on the line segment
     * @return true if the point is on the line segment, false otherwise
     */
    public boolean isOn(Point p) {
        double aToB = this.start.distance(p);
        double bToC = p.distance(this.end);
        double aToC = this.start.distance(this.end);
        if (aToB + bToC - aToC < EPSILON) {
            return true;
        }
        return false;
    }

    /**
     * This method checks if the current line segment has common edge points with another line.
     *
     * @param other the line to check for common edge points
     * @return the point where the two lines intersect, or null if there is no intersection
     */
    private Point isOnEdges(Line other) {
        if (this.start.equals(other.start())
                || this.start.equals(other.end())) {
            return this.start;
        }
        if (this.end.equals(other.end())
                || this.end.equals(other.start())) {
            return this.end;
        }
        return null;
    }

    /**
     * Determines whether this line overlaps with another vertical line.
     *
     * @param other the other line to check for overlap
     * @return true if the lines overlap, false otherwise
     */
    private boolean isOverlappingVertical(Line other) {
        if (this.start.getX() != other.start().getX()) {
            return false;
        }
        // first line starts after the second one ends
        if (this.start.equals(other.end()) || this.end.equals(other.start())) {
            return false;
        }
        double minThis = Math.min(this.start.getY(), this.end.getY());
        double minOther = Math.min(other.start().getY(), other.start().getY());
        double maxThis = Math.max(this.start.getY(), this.end.getY());
        double maxOther = Math.max(other.start().getY(), other.start().getY());

        if (minThis > maxOther || minOther > maxThis) {
            return false;
        }

        return true;
    }

    /**
     * Determines whether this line intersects with another line.
     *
     * @param other the other line to check for intersection
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        // if the lines overlaps each other
        if (this.isOverlapping(other)) {
            return true;
        }
        if (this.isOnEdges(other) != null) {
            return true;
        }
        // if both of the lines are not parallel to the Y column
        if (!this.isVertical() && !other.isVertical()) {
            // x = (b2 - b1) / (m1 - m2)
            double interX = (other.getB() - this.getB())
                    / (this.getSlope() - other.getSlope());
            // if the intersection X value is withing the line
            if (interX >= Math.min(this.start.getX(), this.end.getX())
                    && interX <= Math.max(this.start.getX(), this.end.getX())
                    && interX >= Math.min(other.start().getX(),
                    other.end().getX())
                    && interX <= Math.max(other.start().getX(),
                    other.end().getX())) {
                return true;
            }
            return false;
        }
        if (this.isVertical()) {
            if (this.start.getX() >= Math.min(other.start().getX(),
                    other.end().getX())
                    && this.start.getX() <= Math.max(other.start().getX(),
                    other.end().getX())) {
                if (other.start().getY() >= this.start.getY()
                        && other.end().getY() <= this.end.getY()) {
                    return true;
                }
                if (other.start().getY() <= this.start.getY()
                        && other.end().getY() >= this.end.getY()) {
                    return true;
                }
            }
            return false;
        }
        // other line is vertical
        if (other.start().getX() >= Math.min(this.start.getX(), this.end.getX())
                && other.start().getX() <= Math.max(this.start.getX(),
                this.end.getX())) {
            if (Math.max(other.start().getY(), other.end().getY())
                    >= Math.max(this.start.getY(), this.end.getY())
                    && Math.min(other.start.getY(),
                    other.end().getY()) <= Math.min(this.end.getY(),
                    this.start().getY())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the point of intersection of this line with another line.
     *
     * @param other the other line to find intersection with
     * @return the intersection point if the lines intersect, null otherwise
     */
    public Point intersectionWith(Line other) {
        if (this.isOverlapping(other) || !this.isIntersecting(other)) {
            return null;
        }
        if (this.isOnEdges(other) != null) {
            return this.isOnEdges(other);
        }
        double interX, interY;
        Point interP;
        if (!this.isVertical() && !other.isVertical()) {
            // lines have the same slope
            if (Math.abs(this.getSlope() - other.getSlope()) < EPSILON) {
                if (this.start.equals(other.end())) {
                    return this.start;
                }
                return this.end;
            }
            interX = (this.getB() - other.getB())
                    / (other.getSlope() - this.getSlope());
            // if the intersection point is within the lines
            if (interX >= Math.min(this.start.getX(), this.end.getX())
                    && interX <= Math.max(this.start.getX(), this.end.getX())
                    && interX >= Math.min(other.start().getX(),
                    other.end().getX())
                    && interX <= Math.max(other.start().getX(),
                    other.end().getX())) {
                interY = this.getSlope() * interX + this.getB();
                interP = new Point(interX, interY);
                return interP;
            }
            return null;
        }
        if (this.isVertical()) {
            if (this.start.getX() >= Math.min(other.start().getX(),
                    other.end().getX())
                    && this.start.getX() <= Math.max(other.start().getX(),
                    other.end().getX())) {
                interX = this.start.getX();
                interY = other.getSlope() * interX + other.getB();
                interP = new Point(interX, interY);
                return interP;
            }
            return null;
        }
        if (other.start().getX() >= Math.min(this.start.getX(), this.end.getX())
                && other.start().getX() <= Math.max(this.start.getX(),
                this.end.getX())) {
            interX = other.start().getX();
            interY = this.getSlope() * interX + this.getB();
            interP = new Point(interX, interY);
            return interP;
        }
        return null;
    }

    /**
     * Returns true if the lines are equal (i.e., have the same start and end points), false otherwise.
     *
     * @param other the other line to compare to
     * @return true if the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        if (this.start.equals(other.start()) && this.end.equals(other.end)) {
            return true;
        }
        if (this.start.equals(other.end()) && this.end.equals(other.start())) {
            return true;
        }
        return false;
    }

    /**
     * gets the closest point of intersection of a line with a rectangle.
     *
     * @param rect Objects.Rectangle of collision
     * @return Objects.Point of intersection.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        java.util.List<Point> intersections = rect.intersectionPoints(this);
        if (intersections == null) {
            return null;
        }
        Point minD = null;
        for (Point p : intersections) {
            if (minD == null) {
                minD = p;
            } else {
                if (this.start.distance(minD) > this.start.distance(p)) {
                    minD = p;
                }
            }
        }
        return minD;
    }

}