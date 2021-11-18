//324680438
/**
 * this is the hit listener interface.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * @param beingHit is the brick being hit
     * @param hitter is the ball making the hit
     */
    void hitEvent(Block beingHit, Ball hitter);
}