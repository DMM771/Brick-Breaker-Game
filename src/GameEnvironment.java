//id 324680438
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
/**
 * @author David Monheit
 * @version 1.0.0  25.4.2021 ass5
 * Create game environment.
 */
public class GameEnvironment {
    private List<Collidable> listOfCollidables;
    private final double doubleThreshold = 0.00000000001;
    private final List<Ball> listOfBalls = new ArrayList<>();

    /**
     * Create new linkedList of collidable objects.
     */
    public GameEnvironment() {
        this.listOfCollidables = new LinkedList<>();
    }

    /**
     * accessor.
     * @return the list of collidables
     */
    public List<Collidable> getCollidableList() {
        return this.listOfCollidables;
    }

    /**
     * add new ball to list of balls.
     * @param ball is the ball to add
     */
    public void addBall(Ball ball) {
        listOfBalls.add(ball);
    }

    /**
     * accessor.
     * @return the list of balls
     */
    public List<Ball> getListOfBalls() {
        return this.listOfBalls;
    }

    /**
     * add new collidable to list.
     * @param c is the collidable
     */
    public void addCollidable(Collidable c) {
        listOfCollidables.add(c);
    }

    /**
     * remove a collidable from ist.
     * @param c is the collidable to remove
     */
    public void removeCollidable(Collidable c) {
        listOfCollidables.remove(c);
    }
    /**
     * Get the next closest collision points.
     * @param trajectory  is a Line Assuming an object is moving from line.start() to line.end()
     * @return return collection of such points collection, if non exist, return null.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        CollisionInfo collisionInfo = null;
        if (listOfCollidables == null) {
            return null;
        }
        //set at double max and update if smaller value found
        Double distance = Double.MAX_VALUE;
        for (Collidable collidable : listOfCollidables) {
            Point contact = trajectory.closestIntersectionToStartOfLine(collidable.getCollisionRectangle());
            if (contact != null) {
                if (trajectory.start().distance(contact) - distance <= doubleThreshold) {
                    collisionInfo = new CollisionInfo(collidable, contact);
                }
            }
        }
        return collisionInfo;
    }
}