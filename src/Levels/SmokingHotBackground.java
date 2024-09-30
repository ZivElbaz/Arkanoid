/**
 * @author Ziv Elbaz
 * @version 1
 * @since 14/06/2023
 */
package Levels;

import Logic.Sprite;
import biuoop.DrawSurface;

import java.awt.*;

/**
 * SmokingHotBackground is a class that implements the Sprite interface and
 * represents the background of the "Smoking Hot" level.
 */
public class SmokingHotBackground implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        // Draw the body
        d.setColor(Color.BLACK);
        d.fillRectangle(250, 250, 40, 100);

        // Draw the legs
        d.drawLine(250, 350, 250, 400);
        d.drawLine(290, 350, 290, 400);

        // Draw the shoes
        d.setColor(Color.DARK_GRAY);
        d.fillRectangle(230, 400, 30, 10); // Left shoe
        d.fillRectangle(280, 400, 30, 10); // Right shoe

        // Draw the arms
        d.drawLine(250, 280, 200, 320);
        d.drawLine(290, 280, 340, 320);

        // Draw the head
        d.setColor(Color.PINK);
        d.fillOval(230, 180, 80, 80);

        // Draw the eyes
        d.setColor(Color.WHITE);
        d.fillOval(250, 210, 15, 15); // Left eye
        d.fillOval(280, 210, 15, 15); // Right eye

        // Draw the pupils
        d.setColor(Color.BLACK);
        d.fillOval(255, 215, 5, 5); // Left pupil
        d.fillOval(285, 215, 5, 5); // Right pupil

        // Draw the mouth
        d.drawLine(250, 240, 290, 240);

        // Draw the cigarette
        d.setColor(Color.LIGHT_GRAY);
        d.fillRectangle(340, 315, 20, 5);

        // Draw the burning part of cigarette
        d.setColor(Color.ORANGE);
        d.fillRectangle(355, 315, 5, 5);

        // Draw the smoke
        d.setColor(Color.GRAY);
        for (int i = 0; i < 5; i++) {
            d.fillOval(360 + i * 5, 310 - i * 5, 10, 10);
        }
    }

    @Override
    public void timePassed() {

    }
}
