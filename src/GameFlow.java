//324680438

import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

import java.util.List;

/**
 * class to run the game levels.
 */
public class GameFlow {
    private int screenWidth;
    private int screenHeight;
    private GUI gui;
    private biuoop.KeyboardSensor keyboard;
    private Sleeper sleeper;
    private AnimationRunner runner;
    private Counter scoreCounter = new Counter();

    /**
     * constructor.
     *
     * @param runner       is the animation runner
     * @param keyboard     is the keyboard
     * @param screenWidth  is the screen width
     * @param screenHeight is the screen height
     * @param gui          is the gui
     * @param sleeper      is the sleeper
     */
    public GameFlow(AnimationRunner runner, biuoop.KeyboardSensor keyboard, int screenWidth, int screenHeight,
                    GUI gui, Sleeper sleeper) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.gui = gui;
        this.keyboard = keyboard;
        this.sleeper = sleeper;
        this.runner = runner;
    }

    /**
     * method to run all levels in given list.
     *
     * @param levels is the list of levels
     */
    public void runLevels(List<LevelInformation> levels) {
        //run each level in list

        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(runner, screenWidth, screenHeight, gui, sleeper, levelInfo,
                    scoreCounter);
            level.initialize();
            //keep running if level doest return should stop is true
            while (!level.shouldStop()) {
                level.run();
            }
            //if no balls left, break, game over
            if (level.ballsLeft() <= 0) {
                KeyPressStoppableAnimation gameOver = new KeyPressStoppableAnimation(keyboard,
                        KeyboardSensor.SPACE_KEY, new GameOverAnimation(scoreCounter.getValue()));
                this.runner.run(gameOver);
                return;
            }
        }
        //if got here, player won!
        KeyPressStoppableAnimation winAnimation = new KeyPressStoppableAnimation(keyboard, KeyboardSensor.SPACE_KEY,
                new WinAnimation(scoreCounter.getValue()));
        runner.run(winAnimation);
    }

}