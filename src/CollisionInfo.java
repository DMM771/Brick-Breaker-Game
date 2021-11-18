//id 324680438
/**
 * Collision info.
 * @author David Monheit
 * @version 1.0.0  25.4.2021 ass5
 */
public class CollisionInfo {
    private Collidable collidableObject;
    private Point collisionPoint;


    /**
     * accessor.
     * @param collidable the collidable object
     * @param collisionPoint pont of collision
     */
    public CollisionInfo(Collidable collidable, Point collisionPoint) {
        this.collidableObject = collidable;
        this.collisionPoint = collisionPoint;
    }

    /**
     * accessor.
     * @return point of collision
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * accessor.
     * @return the collidable object
     */
    public Collidable collisionObject() {
        return this.collidableObject;
    }
}