//id 324680438
import java.util.Random;
import java.awt.Color;
/**
 * class to generate random color.
 * @version 1.0.0  25.4.2021 ass5
 * @author David Monheit
 */
public class RandomColor {
    private static final int LIGHTEST_COLOR_SHADE = 255;
    //new random variable
    private Random rand = new Random();
    //get shades of red
    private int red = rand.nextInt(LIGHTEST_COLOR_SHADE);
    private int grey = rand.nextInt(LIGHTEST_COLOR_SHADE);
    private int black = rand.nextInt(LIGHTEST_COLOR_SHADE);
    private Color newColor = new Color(red, grey, black);

    /**
     * accessor.
     * @return the generated color
     */
    public Color randomColor() {
        return newColor;
    }
}
