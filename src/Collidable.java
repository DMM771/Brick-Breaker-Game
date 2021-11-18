//id 324680438
/**
 * @author David Monheit
 * @version 1.0.0  25.4.2021 ass5
 * Collidable interface.
 */
public interface Collidable {
    /**
     * ask the object of it was collided with.
     * @return collision "shape of object
     */
    Rectangle getCollisionRectangle();

    /**
     * notify object it has been collided with.
     * @param hitter is the ball that is hitting
     * @param collisionPoint point of collision
     * @param currentVelocity velocity of impact
     * @return new velocity after impact
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}