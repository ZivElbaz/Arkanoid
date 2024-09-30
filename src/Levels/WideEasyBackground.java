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
 * WideEasyBackground is a class that implements the Sprite interface and
 * represents the background of the "Wide Easy" level.
 */
public class WideEasyBackground implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.LIGHT_GRAY);
        d.fillRectangle(0, 20, d.getWidth(), d.getHeight());
        d.setColor(new Color(225, 235, 100));
        d.fillCircle(150, 150, 50);
        int lines = 120;
        int start = 25;
        int end = 775;

        for (int i = 1; i <= lines; ++i) {
            d.drawLine(150, 150, (end - start) / lines * i, 250);
        }

        d.setColor(new Color(240, 220, 80));
        d.fillCircle(150, 150, 50);
        d.setColor(new Color(250, 200, 50));
        d.fillCircle(150, 150, 40);

    }

    @Override
    public void timePassed() {

    }
}
