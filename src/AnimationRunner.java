//324680438
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * a class to run animations.
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;

    /**
     * constructor.
     * @param gui is the given gui
     * @param framesPerSecond are the FPS
     * @param sleeper is the sleeper
     */
    public AnimationRunner(GUI gui, int framesPerSecond, Sleeper sleeper) {
        this.gui = gui;
        this.framesPerSecond = framesPerSecond;
        this.sleeper = sleeper;
    }

    /**
     * run the given animation.
     * @param animation is the animation top run
     */
    public void run(Animation animation) {

        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        //while animation has not said stop, run loop
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d);
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
