package Objects;

import Logic.Sprite;
import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * The SpriteCollection class represents a collection of Sprite objects that can be drawn on a DrawSurface
 * and updated with time.
 */
public class SpriteCollection {
    private List<Sprite> collection;

    /**
     * Constructs a new Objects.SpriteCollection object.
     */
    public SpriteCollection() {
        this.collection = new ArrayList<>();
    }

    /**
     * Adds a new sprite to the collection.
     *
     * @param s the sprite to add
     */
    public void addSprite(Sprite s) {
        this.collection.add(s);
    }

    /**
     * Remove a sprite from the collection.
     * @param s the sprite to remove
     */
    public void removeSprite(Sprite s) {
        this.collection.remove(s);
    }

    /**
     * Notifies all sprites in the collection that a unit of time has passed.
     */
    public void notifyAllTimePassed() {
        List<Sprite> spriteList = new ArrayList<Sprite>(this.collection);
        for (Sprite s : spriteList) {
            s.timePassed();
        }
    }

    /**
     * Draws all sprites in the collection on the given DrawSurface.
     *
     * @param d the DrawSurface to draw the sprites on
     */
    public void drawAllOn(DrawSurface d) {
        List<Sprite> spriteList = new ArrayList<Sprite>(this.collection);
        for (Sprite s : spriteList) {
            s.drawOn(d);
        }
    }
}