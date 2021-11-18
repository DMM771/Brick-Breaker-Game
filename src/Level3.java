//324680438
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
/**
 * level 3 class.
 */
public class Level3 implements LevelInformation {
    private final String levelName = "Level: Classic Blocks";
    private final int numberOfBalls = 2;
    private final int initialBallVelocities = 6;
    private final int paddleSpeed = 11;
    private final int paddleWidth = 125;
    private List<Velocity> listVelocity;
    private List<Block> listOfBlocks;
    //some new custom colors
    private Color metalGrey = new Color(150, 150, 159);
    private Color lightBlue = new Color(84, 198, 226);
    private Color lightGreen = new Color(103, 253, 96);
    private Color purple = new Color(190, 125, 255);
    private Color orange = new Color(255, 128, 64);
    private Color lightMetalGrey = new Color(200, 200, 200);
    private Color darkBlue1 = new Color(47, 79, 79);
    private Color darkBlue2 = new Color(0, 0, 80);
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
        return new Level3BackGround();
    }

    @Override
    public List<Block> blocks() {
        BlockCreator firstRowOfBlocks = new BlockCreator(new Point(180, 100), 50, 20, 12, lightBlue);
        BlockCreator secondRowOfBlocks = new BlockCreator(new Point(230, 120), 50, 20, 11, lightGreen);
        BlockCreator thirdRowOfBlocks = new BlockCreator(new Point(280, 140), 50, 20, 10, Color.yellow);
        BlockCreator fourthRowOfBlocks = new BlockCreator(new Point(330, 160), 50, 20, 9, Color.pink);
        BlockCreator fifthRowOfBlocks = new BlockCreator(new Point(380, 180), 50, 20, 8, purple);
        BlockCreator sixthRowOfBlocks = new BlockCreator(new Point(430, 200), 50, 20, 7, orange);
        listOfBlocks = new ArrayList<>();
        listOfBlocks.addAll(firstRowOfBlocks.getListOfBlocks());
        listOfBlocks.addAll(secondRowOfBlocks.getListOfBlocks());
        listOfBlocks.addAll(thirdRowOfBlocks.getListOfBlocks());
        listOfBlocks.addAll(fourthRowOfBlocks.getListOfBlocks());
        listOfBlocks.addAll(fifthRowOfBlocks.getListOfBlocks());
        listOfBlocks.addAll(sixthRowOfBlocks.getListOfBlocks());
        return this.listOfBlocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.listOfBlocks.size();
    }
}
