package Objects; /**
 * @author Ziv Elbaz
 * @version 2
 * @since 02/05/2023
 */

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Represents a rectangle in a 2D space.
 */
public class Rectangle {

    private Point upperLeft;
    private final double width;
    private final double height;
    private Color color;
    private java.util.List<Line> lines;

    /**
     * Constructs a new Objects.Rectangle object with the given top-left and bottom-right points and color.
     *
     * @param topLeft the top-left point of the rectangle
     * @param width   the width of the rectangle
     * @param height  the height of the rectangle
     * @param color   the color of the rectangle
     */
    public Rectangle(Point topLeft, double width, double height, Color color) {
        this.upperLeft = topLeft;
        this.width = width;
        this.height = height;
        this.color = color;
        setLines();
    }

    /**
     * Constructs a new Objects.Rectangle object with the given top-left and bottom-right points and color.
     *
     * @param topLeft the top-left point of the rectangle
     * @param width   the width of the rectangle
     * @param height  the height of the rectangle
     */
    public Rectangle(Point topLeft, double width, double height) {
        this.upperLeft = topLeft;
        this.width = width;
        this.height = height;
        this.color = Color.lightGray;
        setLines();
    }

    /**
     * Returns the width of the rectangle.
     *
     * @return the width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Returns the height of the rectangle.
     *
     * @return the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Returns the upper-left point of the rectangle.
     *
     * @return the upper-left point of the rectangle
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Returns the color of the rectangle.
     *
     * @return the color of the rectangle
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Calculates and sets the lines of the rectangle.
     */
    public void setLines() {
        java.util.List<Line> lines = new ArrayList<Line>();

        lines.add(getTopSide());
        lines.add(getBottomSide());
        lines.add(getLeftSide());
        lines.add(getRightSide());

        this.lines = lines;
    }

    /**
     * Sets the upper-left point of the rectangle.
     *
     * @param p the new upper-left point of the rectangle
     */
    public void setUpperLeft(Point p) {
        this.upperLeft = p;
    }

    /**
     * Sets the upper-left point of the rectangle.
     *
     * @param x the x-coordinate of the new upper-left point of the rectangle
     * @param y the y-coordinate of the new upper-left point of the rectangle
     */
    public void setUpperLeft(double x, double y) {
        this.upperLeft = new Point(x, y);
    }

    /**
     * Returns the top side of the rectangle.
     *
     * @return the top side of the rectangle
     */
    public Line getTopSide() {
        Point start = this.upperLeft;
        Point end = new Point(this.upperLeft.getX() + this.width,
                this.upperLeft.getY());
        return (new Line(start, end));
    }

    /**
     * Returns the bottom side of the rectangle as a Objects.Line object.
     *
     * @return the bottom side of the rectangle as a Objects.Line object
     */
    public Line getBottomSide() {
        Point start = new Point(this.upperLeft.getX(),
                this.upperLeft.getY() + this.height);
        Point end = new Point(this.upperLeft.getX() + this.width,
                this.upperLeft.getY() + this.height);
        return (new Line(start, end));
    }

    /**
     * Returns the left side of the rectangle as a Objects.Line object.
     *
     * @return the left side of the rectangle as a Objects.Line object
     */
    public Line getLeftSide() {
        Point start = this.upperLeft;
        Point end = new Point(this.upperLeft.getX(),
                this.upperLeft.getY() + this.height);
        return (new Line(start, end));
    }

    /**
     * Returns the right side of the rectangle as a Objects.Line object.
     *
     * @return the right side of the rectangle as a Objects.Line object
     */
    public Line getRightSide() {
        Point start = new Point(this.upperLeft.getX() + this.width,
                this.upperLeft.getY());
        Point end = new Point(this.upperLeft.getX() + this.width,
                this.upperLeft.getY() + this.height);
        return (new Line(start, end));
    }

    /**
     * Returns a list of all the sides of the rectangle.
     *
     * @return a list of Objects.Line objects representing the sides of the rectangle
     */
    public java.util.List<Line> getLines() {
        return this.lines;
    }

    /**
     * sets the color of the rectangle.
     *
     * @param color the color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Returns a list of intersection points between a given line and the sides of the rectangle.
     *
     * @param line the line to check for intersection points with the rectangle's sides
     * @return a list of intersection points between the given line and the sides of the rectangle,
     * or null if there are no intersection points
     */

    public java.util.List<Point> intersectionPoints(Line line) {
        java.util.List<Point> intersections = new ArrayList<Point>();
        boolean flag = false;
        for (Line l : this.lines) {
            if (line.isIntersecting(l)) {
                intersections.add(line.intersectionWith(l));
                flag = true;
            }
        }
        if (flag) {
            return intersections;
        }
        return null;
    }

}
