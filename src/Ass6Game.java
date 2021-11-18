//id 324680438

import biuoop.GUI;
import biuoop.Sleeper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author David Monheit
 * @version 1.0.0  25.4.2021 ass6
 * Ass5Game
 */
public class Ass6Game {
    private static int fPS = 60;
    private static int screenWidth = 800;
    private static int screenHeight = 600;
    private static GUI gui = new GUI("Arkanoid", screenWidth, screenHeight);
    private static biuoop.KeyboardSensor keyboard = gui.getKeyboardSensor();
    private static Sleeper sleeper = new Sleeper();
    private static AnimationRunner runner = new AnimationRunner(gui, fPS, sleeper);
    private static Level1 level1 = new Level1();
    private static Level2 level2 = new Level2();
    private static Level3 level3 = new Level3();
    private static Level4 level4 = new Level4();
    private static int amountOfLevels = 4;

    /**
     * main.
     *
     * @param args are arguments from console
     */
    public static void main(String[] args) {
        //create list for default levels and add all to list
        List<LevelInformation> defaultListOfLevels = new ArrayList<>();
        defaultListOfLevels.add(level1);
        defaultListOfLevels.add(level2);
        defaultListOfLevels.add(level3);
        defaultListOfLevels.add(level4);
        //create custom list for levels
        List<LevelInformation> customListOfLevelsToRun = new ArrayList<>();
        //check if any arguments
            for (String arg : args) {
                int x;
                try {
                    x = Integer.parseInt(arg);
                } catch (Exception e) {
                    x = 0;
                }
                //only apply if in range
                if (0 < x && x <= amountOfLevels) {
                    customListOfLevelsToRun.add(defaultListOfLevels.get(x - 1));
                }
            }

        GameFlow gameFlow = new GameFlow(runner, keyboard, screenWidth, screenHeight, gui, sleeper);
            //if any valid custom levels were added, run them.
            if (customListOfLevelsToRun.size() > 0) {
            //create and run new gameFlow with correct list of levels
            gameFlow.runLevels(customListOfLevelsToRun);
            //otherwise, run default levels
            } else {
            gameFlow.runLevels(defaultListOfLevels);
        }
        //game has ended, close gui
        gui.close();
    }
}