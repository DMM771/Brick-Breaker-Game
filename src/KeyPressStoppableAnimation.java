//324680438

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * this is a class for key stoppable animations.
 */
public class KeyPressStoppableAnimation implements Animation {
    private boolean isAlreadyPressed = true;
    private boolean shouldStop = false;
    private biuoop.KeyboardSensor keyboardSensor;
    private String key;
    private Animation animation;

    /**
     * constructor.
     *
     * @param sensor    is the keyboard
     * @param key       is the key to stop the animation
     * @param animation is the animation to run
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.keyboardSensor = sensor;
        this.key = key;
        this.animation = animation;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        //draw frame
        animation.doOneFrame(d);
        //set already pressed to false if key is pressed, and avoid already pressed bug.
       if (keyboardSensor.isPressed(this.key) && this.isAlreadyPressed) {
               this.shouldStop = false;
               return;
           }
        if (keyboardSensor.isPressed(this.key) && !this.isAlreadyPressed) {
            this.shouldStop = true;
            return;
        }
        this.isAlreadyPressed = false;





        /*if (keyboardSensor.isPressed(this.key)) {

            this.isAlreadyPressed = false;
        }
        if ((keyboardSensor.isPressed(this.key))) {
            if (!isAlreadyPressed) {
                this.shouldStop = true;
            }
        }*/
    }


    @Override
    public boolean shouldStop() {
        return this.shouldStop;
    }
}