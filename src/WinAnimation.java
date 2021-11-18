//324680438

import biuoop.DrawSurface;
import biuoop.Sleeper;

import java.awt.Color;

/**
 * a class to animate winning screen.
 */
public class WinAnimation implements Animation {
    private int score;
    private boolean started = false;
    private Sleeper sleeper = new Sleeper();
    private boolean stop = false;

    /**
     * constructor.
     *
     * @param score is the score to display
     */
    public WinAnimation(int score) {
        this.score = score;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        int size = 60;
        for (int i = 2; i < 800; i = i + 89) {
            Color shade = new Color(i / 6, 40 + i / 4, 60 + i / 6);
            d.setColor(shade);
            for (int j = 0; j < 600; j = j + 27) {
                d.drawText(i, j, "You Win!", 20 - i / 300);
            }
        }
        d.setColor(Color.black);
        d.drawText(d.getWidth() / 2 - 250, d.getHeight() / 2, "You Win!", size);
        d.drawText(d.getWidth() / 2 - 250, d.getHeight() / 2 + size, "Your Score Is: " + score, size);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}

