//324680438
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * level 1 background class.
 */
public class Level1BackGround implements Sprite {
    private static Color backGroundColor = new Color(35, 35, 35);
    private static Color circleColor = Color.blue;
    private static Color lineColor = Color.blue;

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(backGroundColor);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(circleColor);
        d.drawCircle(d.getWidth() / 2, 150, 60);
        d.drawCircle(d.getWidth() / 2, 150, 100);
        d.drawCircle(d.getWidth() / 2, 150, 140);
        d.setColor(lineColor);
        d.drawLine(d.getWidth() / 2 - 160, 150, d.getWidth() / 2 - 30, 150);
        d.drawLine(d.getWidth() / 2 + 30, 150, d.getWidth() / 2 + 160, 150);
        d.drawLine(d.getWidth() / 2, 150 - 160, d.getWidth() / 2, 150 - 30);
        d.drawLine(d.getWidth() / 2, 150 + 30, d.getWidth() / 2, 150 + 160);

    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
