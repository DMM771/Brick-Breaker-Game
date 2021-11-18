//324680438

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * this is a class that creates rows of blocks.
 */
public class BlockCreator {
    private Point firstBlockTopLeft;
    private int width;
    private int height;
    private int amount;
    private Color color;
    private List<Block> listOfBlocks;

    /**
     * constructor.
     *
     * @param tl     is the top left of first block in row
     * @param width  is the width of each block
     * @param height is the height of each block
     * @param amount is the amount of blocks in row
     * @param color  is the color of row
     */
    public BlockCreator(Point tl, int width, int height, int amount, Color color) { //, GameLevel gameLevel) {
        this.firstBlockTopLeft = tl;
        this.width = width;
        this.height = height;
        this.amount = amount;
        this.color = color;
        listOfBlocks = new ArrayList<>();
        applyNewBlocks();
    }

    /**
     * method to add blocks to current game.
     */
    public void applyNewBlocks() {
        for (double i = 0; i < amount * width; i = i + width) {
            Block newBlock = new Block(new Rectangle(new Point(i + firstBlockTopLeft.getX(),
                    firstBlockTopLeft.getY()), width, height), color);

            listOfBlocks.add(newBlock);
        }
    }

    /**
     * accessor.
     * @return a list of all blocks created
     */
    public List<Block> getListOfBlocks() {
        return this.listOfBlocks;
    }
}
