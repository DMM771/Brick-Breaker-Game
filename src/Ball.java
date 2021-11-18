//id 324680438
import biuoop.DrawSurface;

/**
 * @author David Monheit
 * @version 1.0.0  14.3.2021 ass5
 * create balls
 */
public class Ball implements Sprite {
    //ball radius
    private int radius;
    //ball center
    private Point center;
    //ball color
    private java.awt.Color color;
    //ball speed
    private Velocity velocity;
    //game environment
    private GameEnvironment gameEnvironment;
    private int timePassed = 0;

    /**
     * this is a constructor.
     * @param center is the ball center
     * @param r      is the ball radius
     * @param color  is the ball color
     * @param gameEnvironment is the current game environment
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment gameEnvironment) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * this is a constructor.
     *
     * @param x     is the ball center x value
     * @param y     is the ball center y value
     * @param r     is the ball radius
     * @param color is the ball color
     * @param gameEnvironment is the current game environment
     */
    public Ball(int x, int y, int r, java.awt.Color color, GameEnvironment gameEnvironment) {
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * this is an accessor.
     *
     * @return center x value
     */
    public int getX() {
        return (int)  Math.round(center.getX());
    }

    /**
     * this is an accessor.
     *
     * @return center y value
     */
    public int getY() {
        return (int) Math.round(center.getY());
    }

    /**
     * this is an accessor.
     *
     * @return radius
     */
    public int getSize() {
        return Math.round(this.radius);
    }

    /**
     * this is an accessor.
     *
     * @return color
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * this is an accessor.
     *
     * @return center point
     */
    public Point getCenter() {
        return this.center;
    }

    /**
     * method to draw the balls.
     * @param surface is the drawing surface
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillCircle((int)  center.getX(), (int)  center.getY(), radius);
        surface.setColor(color.darker());
        surface.drawCircle((int)  center.getX(), (int)  center.getY(), radius);
        //this.moveOneStep();
    }

    @Override
    public void timePassed() {
        timePassed++;
        this.moveOneStep();
    }

    /**
     * add ball to game.
     * @param g is the current game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * constructor to set velocity of ball.
     * @param v is velocity
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * constructor to set velocity of ball.
     *
     * @param dx is x axis speed
     * @param dy is y axis speed
     */
    public void setVelocity(double dx, double dy) {
        velocity = new Velocity(dx, dy);
    }

    /**
     * constructor.
     * @param x is x axis value
     * @param y is y axis value
     */
    public void setCenter(double x, double y) {
        this.center = new Point(x, y);
    }

    /**
     * accessor.
     *
     * @return speed
     */
    public Velocity getVelocity() {
        return velocity;
    }

    /**
     * method to remove ball from game.
     * @param g is the game
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }

    /**
     * method to apply speed to ball.
     */
    public void moveOneStep() {
        if (this.velocity == null) {
            return;
        }
        double nextXPos = this.center.getX() + 1.4 * velocity.getDx();
        double nextYPos = this.center.getY() + 1.4 * velocity.getDy();
        Point nextPoint = new Point(nextXPos, nextYPos);
        //create line from current point to next point
        Line trajectory = new Line(this.center.getX(), this.center.getY(), nextPoint.getX(), nextPoint.getY());
        //check if collided with
        Point collisionPoint;
        Object collideWith = this.gameEnvironment.getClosestCollision(trajectory);
        if (collideWith != null) {
            collisionPoint = this.gameEnvironment.getClosestCollision(trajectory).collisionPoint();
            Velocity newVelocity = this.gameEnvironment.getClosestCollision(trajectory)
                    .collisionObject().hit(this, collisionPoint, velocity);

            //check new point is not inside another block
            Point currentPointOffset = new Point(collisionPoint.getX() - 1 * velocity.getDx(),
                    collisionPoint.getY() - 1 * velocity.getDy());
            Point nextPotentialPoint = new Point(collisionPoint.getX() + newVelocity.getDx(),
                    collisionPoint.getY() + newVelocity.getDy());
            Line nextPotentialTrajectory = new Line(currentPointOffset, nextPotentialPoint);
            Object nextCollision = this.gameEnvironment.getClosestCollision(nextPotentialTrajectory);
            //check if new trajectory is inside another block, if yes, must be a corner,send ball in opposite direction
            if (nextCollision != null) {
                newVelocity = new Velocity(-velocity.getDx(), -velocity.getDy());
            }
            velocity = newVelocity;
        }
        //increment time has passed
        //timePassed(); TODO
            //move ball according to velocity
            Point target = this.getVelocity().applyToPoint(this.center);
            this.center = target;
    }
}


