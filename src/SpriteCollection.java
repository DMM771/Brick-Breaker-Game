//id 324680438
import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;
/**
 * @author David Monheit
 * @version 1.0.0  25.4.2021 ass5
 * Sprite collection class.
 */
public class SpriteCollection {
    private List<Sprite> listOfSprites;

    /**
     * constructor.
     */
    public SpriteCollection() {
        listOfSprites = new ArrayList<>();
    }

    /**
     * add new sprite to list of sprites.
     * @param s the sprite
     */
    public void addSprite(Sprite s) {
        listOfSprites.add(s);
    }

    /**
     * method to remove sprite.
     * @param s is the sprite to remove
     */
    public void removeSprite(Sprite s) {
        listOfSprites.remove(s);
    }

    /**
     * tell all sprites that time has passed.
     */
    public void notifyAllTimePassed() {
        List<Sprite> temp = new ArrayList<>(this.listOfSprites);
        // Notify all listeners about a hit event:
        for (Sprite s : temp) {
            s.timePassed();
        }
    }

    /**
     * tell all sprites to draw themselves.
     * @param d is the surface to draw on
     */
    public void drawAllOn(DrawSurface d) {
        List<Sprite> temp = new ArrayList<>(this.listOfSprites);
        for (Sprite s : temp) {
            s.drawOn(d);
        }
    }
}