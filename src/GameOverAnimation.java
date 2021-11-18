//324680438

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * class to draw the game over animation animation.
 */
public class GameOverAnimation implements Animation {
    private boolean stop = false;
    private int score;

    /**
     * constructor.
     *
     * @param score is the score
     */
    public GameOverAnimation(int score) {
        this.score = score;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        for (int i = 0; i < 800; i = i + 20) {
            Color shade = new Color(i / 6, 40 + i / 4, 60 + i / 6);
            d.setColor(shade);
            for (int j = 0; j < 600; j = j + 27) {
                d.fillCircle(i, j, 30);
            }
        }
        int size = 30;
        d.drawText(d.getWidth() / 2 - 320, d.getHeight() / 2, "Game Over", 120);
        d.drawText(d.getWidth() / 2 - 290, d.getHeight() / 2 + 60, "Your Score Is: ", size);
        d.drawText(d.getWidth() / 2 - 50, d.getHeight() / 2 + 100, "" + score, 100);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}

