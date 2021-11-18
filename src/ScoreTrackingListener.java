//324680438

/**
 * a listener class for counting.
 */
public class ScoreTrackingListener implements HitListener {
    private final int pointsForHittingBlock = 5;
    private Counter currentScore;
    private ScoreIndicator scoreIndicator;

    /**
     * constructor.
     * @param scoreCounter is the score counter
     * @param scoreIndicator is the score indicator
     */
    public ScoreTrackingListener(Counter scoreCounter, ScoreIndicator scoreIndicator) {
        this.currentScore = scoreCounter;
        this.scoreIndicator = scoreIndicator;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(pointsForHittingBlock);
        scoreIndicator.updateScore(currentScore.getValue());
    }
}