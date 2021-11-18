//324680438

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * a class to animate pause screen.
 */
public class PauseScreen implements Animation {
    private boolean stop;

    /**
     * constructor.
     */
    public PauseScreen() {
        this.stop = false;
    }

    /**
     * a class to animate one frame.
     *
     * @param d is the surface to draw on
     */
    public void doOneFrame(DrawSurface d) {
        for (int i = 0; i < 800; i = i + 20) {
            Color shade = new Color(i / 6, 40 + i / 4, 100 + i / 6);
            d.setColor(shade);
            for (int j = 0; j < 600; j = j + 27) {
                d.setColor(shade);
                d.fillCircle(i, j, 50);
                d.setColor(Color.black);
            }
        }

        d.drawText(d.getWidth() / 2 - 320, d.getHeight() / 2, "Game Paused", 100);
        d.drawText(d.getWidth() / 2 - 160, d.getHeight() / 2 + 240, "Press pace to continue", 32);
    }

    /**
     * a class to let know animation to stop.
     *
     * @return boolean true or false to stop
     */
    public boolean shouldStop() {
        return this.stop;
    }
}