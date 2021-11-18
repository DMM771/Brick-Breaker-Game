//id 324680438

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.List;

/**
 * @author David Monheit
 * @version 1.0.0  25.4.2021 ass5
 * Create level.
 */
public class GameLevel implements Animation {
    private LevelInformation levelInformation;
    private biuoop.KeyboardSensor keyboard;
    private Counter blockCounter;
    private Counter ballCounter;
    private Counter scoreCounter;
    private BlockRemover blockRemover;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private ScoreTrackingListener scoreTracker;
    private int screenWidth;
    private int screenHeight;
    private static int fPS = 60;
    private Sleeper sleeper;
    private AnimationRunner runner;
    private boolean running = true;
    private final int paddleHeight = 15;
    private static int ballSize = 7;
    private String currentLevel;
    private Sprite backGround;

    /**
     * constructor.
     * @param runner is the animation runner
     * @param screenWidth is the screen width
     * @param screenHeight is the screen height
     * @param gui is the gui
     * @param sleeper is the sleeper
     * @param levelInformation is the give level info
     * @param currentScoreCounter current score to count from
     */
    public GameLevel(AnimationRunner runner, int screenWidth, int screenHeight,
                     GUI gui, Sleeper sleeper, LevelInformation levelInformation, Counter currentScoreCounter) {
        this.levelInformation = levelInformation;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.gui = gui;
        this.keyboard = gui.getKeyboardSensor();
        this.sleeper = sleeper;
        this.runner = runner;
        this.scoreCounter = currentScoreCounter;
        this.currentLevel = levelInformation.levelName();
    }

    /**
     * accessor.
     * @return the block counter
     */
    public Counter getBlockCounter() {
        return blockCounter;
    }

    /**
     * accessor.
     *
     * @return the score tracker
     */
    public ScoreTrackingListener getScoreTracker() {
        return scoreTracker;
    }
    /**
     * accessor.
     *
     * @return the score tracker
     */
    public biuoop.KeyboardSensor getKeyboardSensor() {
        return this.keyboard;
    }

    /**
     * accessor.
     *
     * @return the block remover
     */
    public BlockRemover getBlockRemover() {
        return blockRemover;
    }

    /**
     * accessor.
     *
     * @return the environment
     */
    public GameEnvironment getEnvironment() {
        return environment;
    }

    /**
     * add new collidable to the environment.
     *
     * @param c is the collidable o add
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * add new sprite to list of sprites.
     *
     * @param s is the new sprite to add
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * methot to remove Collidable.
     *
     * @param c is the Collidable object to remove
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * method to remove sprite.
     *
     * @param s is the sprite to remove
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);

    }

    /**
     * Initialize a new game.
     * Create the Blocks and Ball (and Paddle)
     * and finally, add them to the game.
     */
    public void initialize() {
        List<Block> listOfBlocks;
        runner = new AnimationRunner(this.gui, this.fPS, this.sleeper);
        keyboard = gui.getKeyboardSensor();
        sprites = new SpriteCollection();
        environment = new GameEnvironment();
        blockCounter = new Counter();
        ballCounter = new Counter();
        ScoreIndicator scoreIndicator = new ScoreIndicator(scoreCounter);
        LevelIndicator levelIndicator = new LevelIndicator(currentLevel);
        scoreTracker = new ScoreTrackingListener(scoreCounter, scoreIndicator);
        blockRemover = new BlockRemover(this, blockCounter);
        BallRemover ballRemover = new BallRemover(this, ballCounter);
        backGround = this.levelInformation.getBackground();


        //some new custom colors
        Color metalGrey = new Color(150, 150, 159);
        Color lightMetalGrey = new Color(200, 200, 200);
        //ball colors
        Color darkBlue1 = new Color(47, 79, 79);

        //create paddle
        Paddle paddle = new Paddle(new Rectangle(new Point((screenWidth - levelInformation.paddleWidth()) / 2,
     580), levelInformation.paddleWidth(), paddleHeight), metalGrey, keyboard, levelInformation.paddleSpeed());
        //create background
        //Block background = new Block(new Rectangle(new Point(20, 20), 760, 590), lightMetalGrey);
        //create border blocks
        Block topBlock = new Block(new Rectangle(new Point(20, 0), 780, 20), Color.darkGray);
        Block bottomBlock = new Block(new Rectangle(new Point(20, 595), 780, 15), lightMetalGrey);
        Block rightBlock = new Block(new Rectangle(new Point(0, 0), 20, 600), Color.darkGray);
        Block leftBlock = new Block(new Rectangle(new Point(780, 0), 20, 600), Color.darkGray);

        //bottom block is "death range" to delete balls
        bottomBlock.addHitListener(ballRemover);
        bottomBlock.addToGame(this);
        this.addSprite(backGround);
        //add all objects to game

        //background.addToGame(this);
        paddle.addToGame(this);
        topBlock.addToGame(this);
        rightBlock.addToGame(this);
        leftBlock.addToGame(this);
        scoreIndicator.addToGame(this);
        levelIndicator.addToGame(this);
        for (int i = 0; i < levelInformation.numberOfBalls(); i++) {
            int xPos = -((levelInformation.numberOfBalls() - 1) / 2 - i) * 3 * ballSize + screenWidth
                    / 2 - ballSize / 2;
            int yPos = screenHeight - 15 - paddleHeight - 10;
            Ball ball = new Ball(xPos, yPos, ballSize, darkBlue1, environment);
            ball.setVelocity(levelInformation.initialBallVelocities().get(i));
            ball.addToGame(this);
            environment.addBall(ball);
            ballCounter.increase(1);
            paddle.addBallToList(ball);
        }


        listOfBlocks = levelInformation.blocks();
        for (Block block : listOfBlocks) {
            block.addToGame(this);
            block.addHitListener(blockRemover);
            block.addHitListener(scoreTracker);
            blockCounter.increase(1);
        }

    }

    /**
     * run the game.
     * start animation loop.
     */
    public void run() {
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        this.running = true;
        this.runner.run(this);
    }

    /**
     * accessor.
     * @return amount of balls remaining
     */
    public int ballsLeft() {
        return this.ballCounter.getValue();
    }

    /**
     * accessor.
     * @return the score
     */
    public int finalScore() {
        return this.scoreCounter.getValue();
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (ballCounter.getValue() <= 0) {
            this.running = false;
        }
        if (blockCounter.getValue() <= 0) {
            scoreCounter.increase(100);
            this.running = false;
        }

        if (this.keyboard.isPressed("p")) {
            KeyPressStoppableAnimation pauseScreen = new KeyPressStoppableAnimation(keyboard, KeyboardSensor.SPACE_KEY,
                    new PauseScreen());
            this.runner.run(pauseScreen);
        }
        sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
    }
}