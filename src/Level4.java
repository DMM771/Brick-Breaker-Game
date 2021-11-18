//324680438
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
/**
 * level 4 class.
 */
public class Level4 implements LevelInformation {
    private final String levelName = "Level: Final Blocks";
    private final int numberOfBalls = 3;
    private final int initialBallVelocities = 6;
    private final int paddleSpeed = 11;
    private final int paddleWidth = 200;
    private List<Velocity> listVelocity;
    private List<Block> listOfBlocks;
    //some new custom colors
    private Color lightBlue = new Color(84, 198, 226);
    private Color lightGreen = new Color(103, 253, 96);
    private Color purple = new Color(190, 125, 255);
    private Color orange = new Color(255, 128, 64);
    private Color darkBlue3 = new Color(10, 20, 150);

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
        return new Level4BackGround();
    }

    @Override
    public List<Block> blocks() {
        BlockCreator firstRowOfBlocks = new BlockCreator(new Point(25, 75), 50, 25, 15, lightBlue);
        BlockCreator secondRowOfBlocks = new BlockCreator(new Point(25, 100), 50, 25, 15, lightGreen);
        BlockCreator thirdRowOfBlocks = new BlockCreator(new Point(25, 125), 50, 25, 15, Color.yellow);
        BlockCreator fourthRowOfBlocks = new BlockCreator(new Point(25, 150), 50, 25, 15, Color.pink);
        BlockCreator fifthRowOfBlocks = new BlockCreator(new Point(25, 175), 50, 25, 15, purple);
        BlockCreator sixthRowOfBlocks = new BlockCreator(new Point(25, 200), 50, 25, 15, orange);
        BlockCreator seventhRowOfBlocks = new BlockCreator(new Point(25, 225), 50, 25, 15,
                darkBlue3.brighter().brighter().brighter());
        listOfBlocks = new ArrayList<>();
        listOfBlocks.addAll(firstRowOfBlocks.getListOfBlocks());
        listOfBlocks.addAll(secondRowOfBlocks.getListOfBlocks());
        listOfBlocks.addAll(thirdRowOfBlocks.getListOfBlocks());
        listOfBlocks.addAll(fourthRowOfBlocks.getListOfBlocks());
        listOfBlocks.addAll(fifthRowOfBlocks.getListOfBlocks());
        listOfBlocks.addAll(sixthRowOfBlocks.getListOfBlocks());
        listOfBlocks.addAll(seventhRowOfBlocks.getListOfBlocks());
        return this.listOfBlocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.listOfBlocks.size();
    }
}
