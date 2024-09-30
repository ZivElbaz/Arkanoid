/**
 * @author Ziv Elbaz
 * @version 1
 * @since 05/06/2023
 */
package Logic;

import biuoop.DrawSurface;

import java.awt.*;

/**
 * The ScoreIndicator class is responsible for displaying the current score on the game screen.
 * It implements the Sprite interface to allow it to be drawn on the game screen.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;
    private static final int DEFAULT_X = 360;
    private static final int DEFAULT_HEIGHT = 18;
    private static final int DEFAULT_FONT_SIZE = 18;

    /**
     * Constructor.
     *
     * @param score Pointer for the score of the game
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        String score = "Score: " + this.score.getValue();
        d.drawText(DEFAULT_X, DEFAULT_HEIGHT, score, DEFAULT_FONT_SIZE);
    }

    @Override
    public void timePassed() {

    }
}
