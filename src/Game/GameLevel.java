/**
 * @author Ziv Elbaz
 * @version 1
 * @since 14/06/2023
 */
package Game;

import Levels.LevelInformation;
import Logic.*;
import Objects.*;
import Logic.Block;
import Objects.Point;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import Listeners.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The GameLevel class represents a single level in the game. It implements the Animation interface
 * and is responsible for managing the sprites, collidables, and game logic specific to the level.
 * It uses the AnimationRunner and KeyboardSensor to control the animation and user input.
 * The class also handles initializing the level, running the game loop, and keeping track of game state.
 * It contains methods to add and remove sprites and collidables, initialize the level elements,
 * handle animations and collisions, and more.
 * The GameLevel class relies on LevelInformation, GameEnvironment, and various listeners and objects
 * from the Logic and Objects packages to implement the game functionality.
 */
public class GameLevel implements Animation {
    private final SpriteCollection sprites;
    private Counter remainingBlocks;
    private Counter remainingBalls;
    private Counter score;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    private LevelInformation levelInformation;
    private final GameEnvironment environment = new GameEnvironment();
    private final int BALL_RADIUS = 5;
    private final Color BALL_COLOR = Color.WHITE;

    /**
     * Constructs a new GameLevel with the given LevelInformation, KeyboardSensor,
     * AnimationRunner, and game score Counter.
     *
     * @param li        the LevelInformation of the level
     * @param ks        the KeyboardSensor for capturing keyboard inputs
     * @param ar        the AnimationRunner for controlling the game's animations
     * @param gameScore the Counter for keeping track of the game score
     */
    public GameLevel(LevelInformation li, KeyboardSensor ks,
                     AnimationRunner ar, Counter gameScore) {
        this.sprites = new SpriteCollection();
        this.remainingBlocks =
                new Counter(li.numberOfBlocksToRemove());
        this.remainingBalls = new Counter(li.numberOfBalls());
        this.score = gameScore;
        this.running = false;
        this.runner = ar;
        this.keyboard = ks;
        this.levelInformation = li;
    }

    /**
     * Returns the number of remaining balls in the level.
     *
     * @return the number of remaining balls
     */
    public int getRemainingBalls() {
        return this.remainingBalls.getValue();
    }

    /**
     * Adds a collidable object to the game environment.
     *
     * @param c the collidable object to add
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Removes a collidable object from the game environment.
     *
     * @param c the collidable object to remove
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * Adds a sprite to the game.
     *
     * @param s the sprite to add
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Removes a sprite from the game.
     *
     * @param s the sprite to remove
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * Initializes the game level by creating and adding the level elements,
     * such as balls, borders, blocks, score indicator, and paddle.
     */
    public void initialize() {
        initializeBalls();
        initializedBoarders();
        List<HitListener> blocksListeners = initializeBlocksListeners();
        initializeBlocks(blocksListeners);
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.score);
        this.addSprite(scoreIndicator);
        initializePaddle();
    }

    /**
     * Initializes the balls in the level.
     */
    public void initializeBalls() {
        Point p = new Point(runner.getGameWidth() / 2,
                runner.getGameHeight() - 45);
        for (Velocity v : this.levelInformation.initialBallVelocities()) {
            Ball b = new Ball(p, BALL_RADIUS, BALL_COLOR,
                    this.environment);
            b.setVelocity(v);
            this.sprites.addSprite(b);
        }
    }

    /**
     * Initializes the borders of the level.
     */
    public void initializedBoarders() {
        Block left = new Block(new Point(0, 30), 570, 30, Color.gray);
        Block top = new Block(new Point(0, 0), 30, 800, Color.gray);
        Block bottom = new Block(new Point(30, 595), 5, 740, Color.gray);
        bottom.addHitListener(new BallRemover(this, this.remainingBalls));
        Block right = new Block(new Point(770, 30), 570, 30, Color.gray);
        Block[] boarders = new Block[]{top, bottom, left, right};
        for (Block block : boarders) {
            block.addToGame(this);
        }
    }

    /**
     * Initializes the blocks in the level.
     * Adds listeners and adds them to the game.
     *
     * @param listeners the list of listeners to add to the blocks
     */
    public void initializeBlocks(List<HitListener> listeners) {
        for (Block b : this.levelInformation.blocks()) {
            for (HitListener hl : listeners) {
                b.addHitListener(hl);
            }
            b.addToGame(this);
        }
    }

    /**
     * Initializes the paddle in the level.
     */
    public void initializePaddle() {
        Point p =
                new Point(this.runner.getGameWidth() / 2 - levelInformation.paddleWidth() / 2,
                        this.runner.getGameHeight() - 35);
        Paddle paddle = new Paddle(this.keyboard,
                this.levelInformation.paddleWidth(),
                this.levelInformation.paddleSpeed(), p);
        paddle.addToGame(this);
    }

    /**
     * Initializes the listeners for the blocks.
     * Returns a list of initialized listeners.
     *
     * @return the list of initialized listeners for the blocks
     */

    private List<HitListener> initializeBlocksListeners() {
        List<HitListener> listeners = new ArrayList<>();
        listeners.add(new BlockRemover(this, new Counter()));
        listeners.add(new ScoreTrackingListener(this.score));
        return listeners;
    }

    /**
     * Checks if the game should stop.
     *
     * @return true if the game should stop, false otherwise
     */
    public boolean shouldStop() {
        return (!this.running);
    }

    /**
     * Performs one frame of the game animation.
     * Draws the background, sprites, and notifies all time passed.
     * Checks if the game should stop based on remaining balls and blocks.
     * Handles the "p" key press to pause the game.
     *
     * @param d the DrawSurface to draw on
     */
    public void doOneFrame(DrawSurface d) {
        this.levelInformation.getBackground().drawOn(d);
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();

        if (this.remainingBalls.getValue() == this.levelInformation.numberOfBalls() * -1) {
            this.running = false;
        }
        if (this.remainingBlocks.getValue() == 0) {
            this.score.increase(100);
            this.running = false;
        }

        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(keyboard, KeyboardSensor.SPACE_KEY,
                    new PauseScreen(keyboard)));
        }
    }

    /**
     * Runs the game.
     * Sets the running state to true, runs the countdown animation, and then runs the game animation.
     */
    public void run() {
        this.running = true;
        this.runner.run(new Countdown(this.sprites, this.levelInformation),
                true);
        this.runner.run(this);
    }

    /**
     * Decreases the remaining blocks counter when a block is removed.
     */
    public void blockRemoved() {
        this.remainingBlocks.decrease(1);
    }
}