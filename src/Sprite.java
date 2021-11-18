//id 324680438
import biuoop.DrawSurface;
/**
 * @author David Monheit
 * @version 1.0.0  25.4.2021 ass5
 * Sprite interface.
 */
public interface Sprite {

    /**
     * draw the sprite to the surface.
     * @param d is the surface to draw on
     */
    void drawOn(DrawSurface d);

    /**
     * keep track of time passed.
     */
    void timePassed();

    /**
     * add the sprite to the game.
     * @param g is the game
     */
    void addToGame(GameLevel g);
}