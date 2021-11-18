//324680438
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * a class that draws current score on screen.
 */
public class ScoreIndicator implements Sprite {
    private int score;

    /**
     * constructor.
     * @param score is the current score
     */
    public ScoreIndicator(Counter score) {
        this.score = score.getValue();
    }

    /**
     * methot to update score.
     * @param newScore is the new score
     */
    public void updateScore(int newScore) {
        score = newScore;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.white);
        d.drawText(377, 16, "Score: " + score + "", 14);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
