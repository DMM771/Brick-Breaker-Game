//324680438

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * a class that draws current score on screen.
 */
public class LevelIndicator implements Sprite {
    private String levelName;

    /**
     * constructor.
     *
     * @param levelName is the current level
     */
    public LevelIndicator(String levelName) {
        this.levelName = levelName;
    }


    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.white);
        d.drawText(600, 16, levelName, 14);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
