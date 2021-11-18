//324680438
import biuoop.DrawSurface;

import java.awt.Color;
/**
 * level 4 background class.
 */
public class Level4BackGround implements Sprite {
    private static Color backGroundColor = new Color(73, 174, 203);
    private static Color darkCloud = new Color(100, 100, 100);
    private static Color midCloud = new Color(140, 140, 140);
    private static Color lightCloud = new Color(180, 180, 180);
    private static Color rain = new Color(156, 190, 192);

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(backGroundColor);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(rain);
        //loop for rain
        for (int i = 135; i < 230; i += 10) {
            d.drawLine(i, 410, i + 80, 800);
        }
        for (int i = 600; i < 690; i += 10) {
            d.drawLine(i, 510, i + 80, 800);
        }
        d.setColor(lightCloud);
        d.fillCircle(140, 400, 30);
        d.fillCircle(165, 425, 33);
        d.fillCircle(600, 500, 20);
        d.fillCircle(618, 518, 20);
        d.setColor(midCloud);
        d.fillCircle(190, 383, 35);
        d.fillCircle(630, 490, 25);
        d.setColor(darkCloud);
        d.fillCircle(212, 415, 30);
        d.fillCircle(225, 380, 33);
        d.fillCircle(672, 505, 30);
        d.fillCircle(650, 520, 25);


    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
