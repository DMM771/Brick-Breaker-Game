//324680438
import biuoop.DrawSurface;

import java.awt.Color;
/**
 * level 3 background class.
 */
public class Level3BackGround implements Sprite {
    private static Color backGroundColor = new Color(151, 208, 225);
    private static Color building = new Color(100, 100, 100);
    private static Color thickAntenna = new Color(140, 140, 140);
    private static Color thinAntenna = new Color(180, 180, 180);
    private static Color windows = new Color(215, 252, 255);
    private Color blinkerMid = new Color(255, 128, 64);
    private Color blinkerCenter = new Color(200, 200, 200);
    private Color blinkerOuter = new Color(47, 79, 79);

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(backGroundColor);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(building);
        d.fillRectangle(50, 400, 99, 800);
        d.setColor(windows);
        // loop top draw windows
        for (int i = 53; i <= 140; i += 12) {
            for (int t = 404; t <= 796; t += 24) {
                d.fillRectangle(i, t, 10, 20);
            }
        }
        d.setColor(thickAntenna);
        d.fillRectangle(83, 340, 34, 60);
        d.setColor(thinAntenna);
        d.fillRectangle(97, 200, 6, 140);
        d.setColor(blinkerOuter);
        d.fillCircle(100, 210, 10);
        d.setColor(blinkerMid);
        d.fillCircle(100, 210, 6);
        d.setColor(blinkerCenter);
        d.fillCircle(100, 210, 4);

    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
