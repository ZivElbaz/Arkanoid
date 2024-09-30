package Objects; /**
 * @author Ziv Elbaz
 * @version 1
 * @since 02/05/2023
 */

import Game.GameLevel;

import Logic.Collidable;
import Logic.Sprite;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * A class representing a Objects.Paddle object.
 * The Objects.Paddle is controlled by the user and can move horizontally on the screen.
 */
public class Paddle implements Sprite, Collidable {
    private final Color DEFAULT_PADDLE_COLOR = Color.orange;
    private final int HEIGHT = 20;
    private biuoop.KeyboardSensor keyboard;
    private Rectangle paddle;
    private int moveMargin;

    /**
     * Creates a new Paddle.
     *
     * @param ks      the keyboard sensor for controlling the paddle
     * @param width   the width of the paddle
     * @param speed   the speed of the paddle's movement
     * @param topLeft the top left point of the paddle's position
     */
    public Paddle(KeyboardSensor ks, int width, int speed, Point topLeft) {
        this.paddle = new Rectangle(topLeft, width, HEIGHT, DEFAULT_PADDLE_COLOR);
        this.moveMargin = speed;
        this.keyboard = ks;
    }

    /**
     * Changes the paddle's position to the left by 10 pixels.
     * The function checks if the paddle reaches the limit, so it won't leave the screen.
     */
    public void moveLeft() {
        if (this.paddle.getUpperLeft().getX() - this.moveMargin <= 30) {
            this.paddle.setUpperLeft(30, this.paddle.getUpperLeft().getY());
        } else {
            this.paddle.setUpperLeft(this.paddle.getUpperLeft().getX() - this.moveMargin,
                    this.paddle.getUpperLeft().getY());
        }
    }

    /**
     * Changes the paddle's position to the right by 10 pixels.
     * The function checks if the paddle reaches the limit, so it won't leave the screen.
     */
    public void moveRight() {
        if (this.paddle.getUpperLeft().getX() + this.paddle.getWidth() + this.moveMargin >= 770) {
            this.paddle.setUpperLeft(770 - this.paddle.getWidth(), this.paddle.getUpperLeft().getY());
        } else {
            this.paddle.setUpperLeft(this.moveMargin + this.paddle.getUpperLeft().getX(),
                    this.paddle.getUpperLeft().getY());
        }
    }

    /**
     * Implementation of the timePassed method from the Objects.Logic.Sprite interface.
     * The function is called every frame and checks if the player pressed the arrow keys.
     * If the player did, it calls moveLeft or moveRight accordingly.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
            this.paddle.setLines();
        } else if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
            this.paddle.setLines();
        }
    }

    /**
     * Draws the Objects.Paddle on the given DrawSurface.
     *
     * @param d The DrawSurface to draw on.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.paddle.getColor());
        d.fillRectangle((int) this.paddle.getUpperLeft().getX(), (int) this.paddle.getUpperLeft().getY(),
                (int) this.paddle.getWidth(), (int) this.paddle.getHeight());
        d.setColor(Color.black);
        d.drawRectangle((int) this.paddle.getUpperLeft().getX(), (int) this.paddle.getUpperLeft().getY(),
                (int) this.paddle.getWidth(), (int) this.paddle.getHeight());
    }

    /**
     * Implementation of the getCollisionRectangle method from the Objects.Logic.Collidable interface.
     *
     * @return The Objects.Rectangle that defines the Objects.Paddle's collision boundary.
     */
    public Rectangle getCollisionRectangle() {
        return this.paddle;
    }

    /**
     * Returns the collision line of the paddle at the given point.
     *
     * @param p the point to check for a collision line.
     * @return the collision line of the paddle at the given point, or null if there
     * is no collision.
     */
    public Line getCollisionLine(Point p) {
        java.util.List<Line> lines = this.paddle.getLines();
        for (int i = 0; i < 4; i++) {
            if (lines.get(i).isOn(p)) {
                return lines.get(i);
            }
        }
        return null;
    }

    /**
     * Returns the first section of the paddle.
     *
     * @return the first section of the paddle.
     */
    private Line getFirstSection() {
        double sectionWidth = this.paddle.getWidth() / 5;
        Point start = this.paddle.getUpperLeft();
        Point end = new Point(this.paddle.getUpperLeft().getX() + sectionWidth,
                this.paddle.getUpperLeft().getY());
        return new Line(start, end);
    }

    /**
     * Returns the second section of the paddle.
     *
     * @return the second section of the paddle.
     */
    private Line getSecondSection() {
        double sectionWidth = this.paddle.getWidth() / 5;
        Point start = new Point(this.paddle.getUpperLeft().getX() + sectionWidth,
                this.paddle.getUpperLeft().getY());
        Point end = new Point(this.paddle.getUpperLeft().getX() + sectionWidth * 2,
                this.paddle.getUpperLeft().getY());
        return new Line(start, end);
    }

    /**
     * Returns the third section of the paddle.
     *
     * @return the third section of the paddle.
     */
    private Line getThirdSection() {
        double sectionWidth = this.paddle.getWidth() / 5;
        Point start = new Point(this.paddle.getUpperLeft().getX() + sectionWidth * 2,
                this.paddle.getUpperLeft().getY());
        Point end = new Point(this.paddle.getUpperLeft().getX() + sectionWidth * 3,
                this.paddle.getUpperLeft().getY());
        return new Line(start, end);
    }

    /**
     * Returns the fourth section of the paddle.
     *
     * @return the fourth section of the paddle.
     */
    private Line getFourthSection() {
        double sectionWidth = this.paddle.getWidth() / 5;
        Point start = new Point(this.paddle.getUpperLeft().getX() + sectionWidth * 3,
                this.paddle.getUpperLeft().getY());
        Point end = new Point(this.paddle.getUpperLeft().getX() + sectionWidth * 4,
                this.paddle.getUpperLeft().getY());
        return new Line(start, end);
    }

    /**
     * Returns the fifth section of the paddle.
     *
     * @return the fifth section of the paddle.
     */
    private Line getFifthSection() {
        double sectionWidth = this.paddle.getWidth() / 5;
        Point start =
                new Point(this.paddle.getUpperLeft().getX() + sectionWidth * 4,
                        this.paddle.getUpperLeft().getY());
        Point end =
                new Point(this.paddle.getUpperLeft().getX() + sectionWidth * 5,
                        this.paddle.getUpperLeft().getY());
        return new Line(start, end);
    }

    /**
     * Calculates and returns the new velocity of the ball after a collision with the paddle.
     *
     * @param collisionPoint  the point at which the collision occurred
     * @param currentVelocity the velocity of the ball before the collision
     * @param hitter the ball that hit the paddle
     * @return the new velocity of the ball after the collision
     */
    public Velocity hit(Ball hitter, Point collisionPoint,
                        Velocity currentVelocity) {
        Velocity newV = currentVelocity;
        java.util.List<Line> lines = this.paddle.getLines();

        for (int i = 0; i < 4; i++) {
            if (lines.get(i).isOn(collisionPoint)) {
                if (i == 0) {
                    if (this.getFirstSection().isOn(collisionPoint)) {
                        newV = Velocity.fromAngleAndSpeed(240,
                                currentVelocity.getSpeed());
                    } else if (this.getSecondSection().isOn(collisionPoint)) {
                        newV = Velocity.fromAngleAndSpeed(210,
                                currentVelocity.getSpeed());
                    } else if (this.getThirdSection().isOn(collisionPoint)) {
                        newV = Velocity.fromAngleAndSpeed(180,
                                currentVelocity.getSpeed());
                    } else if (this.getFourthSection().isOn(collisionPoint)) {
                        newV = Velocity.fromAngleAndSpeed(150,
                                currentVelocity.getSpeed());
                    } else if (this.getFifthSection().isOn(collisionPoint)) {
                        newV = Velocity.fromAngleAndSpeed(120,
                                currentVelocity.getSpeed());
                    }
                }
                // hits one of the horizontal sides of the block
                if (i == 1) {
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
        return newV;
    }

    /**
     * Adds this paddle to the game by adding it as a collidable and a sprite.
     *
     * @param g the game to add the paddle to
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

}