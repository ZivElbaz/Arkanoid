/**
 * @author Ziv Elbaz
 * @version 1
 * @since 14/06/2023
 */
package Game;

import Levels.LevelInformation;
import Logic.Counter;
import Objects.SpriteCollection;
import biuoop.DrawSurface;

import java.awt.*;

/**
 * The Countdown class represents a countdown animation that displays a countdown
 * from a specified number to zero on a DrawSurface. It implements the Animation interface.
 * The countdown animation is used to introduce a level and provide a countdown before
 * gameplay begins.
 * The class uses a Counter to keep track of the countdown value and a SpriteCollection
 * to display the sprites during the countdown.
 */
public class Countdown implements Animation {
    private Counter counter;
    private boolean finish;
    private SpriteCollection sprites;
    private LevelInformation level;

    /**
     * Constructs a Countdown animation with the specified sprites and level information.
     *
     * @param sprites the SpriteCollection containing the sprites to display
     * @param level   the LevelInformation of the level
     */
    public Countdown(SpriteCollection sprites, LevelInformation level) {
        this.counter = new Counter(3);
        this.finish = false;
        this.sprites = sprites;
        this.level = level;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.level.getBackground().drawOn(d);
        this.sprites.drawAllOn(d);

        d.setColor(Color.WHITE);
        d.drawText(d.getWidth() / 2, d.getHeight() / 2 + 50,
                "" + this.counter.getValue(), 60);
        this.counter.decrease(1);

        if (this.counter.getValue() == 0) {
            this.finish = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.finish;
    }
}
