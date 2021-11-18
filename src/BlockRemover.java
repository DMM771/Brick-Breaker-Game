//324680438
// a BlockRemover is in charge of removing blocks from the game, as well as keeping count
// of the number of blocks that remain.
/**
 * this class removes blocks.
 */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * constructor.
     * @param gameLevel is the game
     * @param removedBlocks are the blocks that are removed
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = removedBlocks;
    }

@Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(gameLevel);
        remainingBlocks.decrease(1);
    }
}