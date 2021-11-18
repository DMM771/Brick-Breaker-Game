//324680438
import biuoop.DrawSurface;

import java.awt.Color;
/**
 * level 2 background class.
 */
public class Level2Background implements Sprite {
    private static Color backGroundColor = new Color(215, 252, 255);
    private static Color centerSun = new Color(240, 168, 0);
    private static Color midSun = new Color(255, 209, 102);
    private static Color outerSun = new Color(255, 239, 202);
    private static Color shineHue = new Color(255, 239, 150);

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(backGroundColor);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        int heightOfBricks = 225;

        d.setColor(outerSun);
        for (int i = 10; i < 700; i += 6) {
            d.setColor(outerSun);
            d.drawLine(140, 115, i, heightOfBricks);
            d.setColor(shineHue);
            d.drawLine(140, 115, i + 3, heightOfBricks);
        }
        d.setColor(outerSun);
        d.fillCircle(140, 115, 50);
        d.setColor(midSun);
        d.fillCircle(140, 115, 45);
        d.setColor(centerSun);
        d.fillCircle(140, 115, 38);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
