//324680438
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * level 2 class.
 */
public class Level2 implements LevelInformation {
    private final String levelName = "Level: Pancake Blocks";
    private final int numberOfBalls = 10;
    private final int initialBallVelocities = 6;
    private final int paddleSpeed = 11;
    private final int paddleWidth = 600;
    private List<Velocity> listVelocity;
    private List<Block> listOfBlocks;
    //some new custom colors
    private Color metalGrey = new Color(150, 150, 159);
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
        return new Level2Background();
    }

    @Override
    public List<Block> blocks() {
        BlockCreator chunk1 = new BlockCreator(new Point(25, 225), 50, 30, 2, lightBlue);
        BlockCreator chunk2 = new BlockCreator(new Point(125, 225), 50, 30, 2, lightGreen);
        BlockCreator chunk3 = new BlockCreator(new Point(225, 225), 50, 30, 2, Color.yellow);
        BlockCreator chunk4 = new BlockCreator(new Point(325, 225), 50, 30, 3, Color.pink);
        BlockCreator chunk5 = new BlockCreator(new Point(475, 225), 50, 30, 2, purple);
        BlockCreator chunk6 = new BlockCreator(new Point(575, 225), 50, 30, 2, orange);
        BlockCreator chunk7 = new BlockCreator(new Point(675, 225), 50, 30, 2, darkBlue3);
        listOfBlocks = new ArrayList<>();
        listOfBlocks.addAll(chunk1.getListOfBlocks());
        listOfBlocks.addAll(chunk2.getListOfBlocks());
        listOfBlocks.addAll(chunk3.getListOfBlocks());
        listOfBlocks.addAll(chunk4.getListOfBlocks());
        listOfBlocks.addAll(chunk5.getListOfBlocks());
        listOfBlocks.addAll(chunk6.getListOfBlocks());
        listOfBlocks.addAll(chunk7.getListOfBlocks());
        return this.listOfBlocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.listOfBlocks.size();
    }
}
