//324680438
import java.util.List;

/**
 * this class holds level information.
 */
public interface LevelInformation {
    /**
     * a class to get number of balls in level.
     * @return the number of balls
     */
    int numberOfBalls();

    /**
     * get initial balls speed.
     * @return the velocity
     */
    List<Velocity> initialBallVelocities();

    /**\
     * get the paddle speed.
     * @return the paddle speed
     */
    int paddleSpeed();

    /**
     * get the width of paddle in level.
     * @return the width of paddle
     */
    int paddleWidth();

    /**
     * get the level name.
     * @return level name
     */
    String levelName();

    /**
     * get the background for level.
     * @return the background
     */
    Sprite getBackground();

    /**
     * get the blocks for level.
     * @return a list of blocks
     */
    List<Block> blocks();


    /**
     * get number of blocks to remove before finishing level.
     * @return int representing number of blocks
     */
    int numberOfBlocksToRemove();
}