//324680438
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * level 1 class.
 */
public class Level1 implements LevelInformation {
    private final String levelName = "Level: Direct Hit Block";
    private final int numberOfBalls = 1;
    private final int initialBallVelocities = 6;
    private final int paddleSpeed = 7;
    private final int paddleWidth = 100;
    private List<Velocity> listVelocity;
    private List<Block> listOfBlocks;
    //some new custom colors

    @Override
    public int numberOfBalls() {
        return this.numberOfBalls;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        listVelocity = new ArrayList<>();
        //loop to set initial speed of balls
        for (int i = 0; i < numberOfBalls; i++) {
            int angle = (i - numberOfBalls / 2) * 7;
            Velocity temp = new Velocity(Velocity.fromAngleAndSpeed(angle, initialBallVelocities).getDx(),
                    Velocity.fromAngleAndSpeed(1, initialBallVelocities).getDy());
            listVelocity.add(temp);
        }
        return listVelocity;
    }

    @Override
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    @Override
    public int paddleWidth() {
        return this.paddleWidth;
    }

    @Override
    public String levelName() {
        return this.levelName;
    }

    @Override
    public Sprite getBackground() {
        return new Level1BackGround();
    }

    @Override
    public List<Block> blocks() {
        BlockCreator firstRowOfBlocks = new BlockCreator(new Point(380, 130), 40, 40, 1, Color.red);
        listOfBlocks = new ArrayList<>();
        listOfBlocks.addAll(firstRowOfBlocks.getListOfBlocks());
        return this.listOfBlocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.listOfBlocks.size();
    }
}
