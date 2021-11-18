//324680438

import biuoop.DrawSurface;

/**
 * interface for animations.
 */
public interface Animation {
    /**
     * do one animation frame.
     *
     * @param d is the surface to draw on
     */
    void doOneFrame(DrawSurface d);

    /**
     * boolean value that represents if animation should stop.
     * @return boolean tru or false
     */
    boolean shouldStop();
}