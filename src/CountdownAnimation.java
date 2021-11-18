//324680438
import biuoop.DrawSurface;
import biuoop.Sleeper;

import java.awt.Color;

/**
 * class to draw the countdown animation.
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private int tempCountFrom;
    private SpriteCollection gameScreen;
    private boolean stop = false;

    /**
     * constructor.
     * @param numOfSeconds is the amount of time to display the count down
     * @param countFrom number to start from
     * @param gameScreen is first game screen list of sprites
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.tempCountFrom = countFrom;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        int size = 100;
        gameScreen.drawAllOn(d);
        Sleeper sleeperForCount = new Sleeper();
        //counting conditions
        if (tempCountFrom != countFrom) {
            sleeperForCount.sleepFor((long) ((1000 * this.numOfSeconds) / this.countFrom));
        }
        if (tempCountFrom == 0) {
            d.setColor(Color.darkGray);
            d.drawText(d.getWidth() / 2 - size / 2 - 34, d.getHeight() / 2 + 3, "GO", size + 10);
        }
        if (tempCountFrom < 0) {
            this.stop = true;
        }
        if (tempCountFrom > 0) {
            d.setColor(Color.darkGray);
            d.drawText(d.getWidth() / 2 - 26, d.getHeight() / 2, "" + tempCountFrom, size);
        }
        tempCountFrom--;
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}

