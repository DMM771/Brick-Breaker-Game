//id 324680438
import biuoop.DrawSurface;
import biuoop.GUI;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author David Monheit
 * @version 1.0.0  25.4.2021 ass5
 * Create game Paddle.
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle rec;
    private java.awt.Color color;
    private GUI gui;
    //Threshold for comparing doubles
    private final double doubleThreshold = 0.00000000001;
    private int timePassed = 0;
    private List<Ball> listOfBalls = new ArrayList<>();
    private final int paddleSpeed;
    private final int maxRight = 780;
    private final int minLeft = 20;


    /**
     * constructor.
     * @param rec is the rectangle that paddle is based on
     * @param color is the color of paddle
     * @param keyboard is the keyboard for inputs
     * @param paddleSpeed is the paddle speed
     */
    public Paddle(Rectangle rec, java.awt.Color color, biuoop.KeyboardSensor keyboard, int paddleSpeed) {
        this.rec = rec;
        this.color = color;
        this.keyboard = keyboard;
        this.paddleSpeed = paddleSpeed;
    }

    /**
     * take in new ball ball to list of balls.
     * @param ball is the ball to add
     */
    public void addBallToList(Ball ball) {
        this.listOfBalls.add(ball);
    }

    /**
     * move paddle left.
     */
    public void moveLeft() {
        //loop to check that paddle will not overlap ball. if yes, correct ball accordingly
        for (Ball ball : this.listOfBalls) {
            //check that ball is in range
            boolean inRangeX = ball.getX() - this.rec.getUpperLeft().getX() - paddleSpeed >= doubleThreshold
                    && ball.getX() - this.rec.getUpperLeft().getX() - this.rec.getWidth() <= doubleThreshold;
            if (ball.getY() >= this.rec.getUpperLeft().getY()) {
                if (inRangeX) {
                    if (ball.getVelocity().getDx() < 0) {
                        ball.setVelocity(ball.getVelocity().getDx(), -ball.getVelocity().getDy());
                        ball.setCenter(ball.getX() - 0.5 * ball.getVelocity().getDx(), ball.getY()
                                - this.rec.getHeight());
                    } else {
                        ball.setVelocity(-ball.getVelocity().getDx(), -ball.getVelocity().getDy());
                        ball.setCenter(ball.getX(), ball.getY() - this.rec.getHeight());
                    }
                }
            }
        }
        //stop paddle at limit
        //make sure paddle doesnt leave borders if not perfect modular size
        if (this.rec.getUpperLeft().getX() <= minLeft) {
            Point newPoint = new Point(minLeft, this.rec.getUpperLeft().getY());
            this.rec = new Rectangle(newPoint, this.rec.getWidth(), this.rec.getHeight());
            return;
        }
        Point newPoint = new Point(this.rec.getUpperLeft().getX() - paddleSpeed, this.rec.getUpperLeft().getY());
        this.rec = new Rectangle(newPoint, this.rec.getWidth(), this.rec.getHeight());
    }

    /**
     * move paddle right.
     */
    public void moveRight() {
        //loop to check that paddle will not overlap ball. if yes, correct ball accordingly
        for (Ball ball : this.listOfBalls) {
            //check that ball is in range
            boolean inRangeX = ball.getX() - (this.rec.getUpperLeft().getX() + this.rec.getWidth()
                    + paddleSpeed) <= doubleThreshold
                    && ball.getX() - this.rec.getUpperLeft().getX() >= doubleThreshold;
            if (ball.getY() >= this.rec.getUpperLeft().getY()) {
                if (inRangeX) {
                    if (ball.getVelocity().getDx() > 0) {
                        ball.setVelocity(ball.getVelocity().getDx(), -ball.getVelocity().getDy());
                        ball.setCenter(ball.getX(), ball.getY() - this.rec.getHeight());
                    } else {
                        ball.setVelocity(-ball.getVelocity().getDx(), -ball.getVelocity().getDy());
                        ball.setCenter(ball.getX(), ball.getY() - this.rec.getHeight());
                    }
                    }
                }
            }
        //make sure paddle doesnt leave borders if not perfect modular size
        if (this.rec.getUpperLeft().getX() >= maxRight - rec.getWidth()) {
            Point newPoint = new Point(maxRight - this.rec.getWidth(), this.rec.getUpperLeft().getY());
            this.rec = new Rectangle(newPoint, this.rec.getWidth(), this.rec.getHeight());
            return;
        }
        Point newPoint = new Point(this.rec.getUpperLeft().getX() + paddleSpeed, this.rec.getUpperLeft().getY());
        this.rec = new Rectangle(newPoint, this.rec.getWidth(), this.rec.getHeight());
    }


    /**
     * keep track of time passed as key is pressed.
     * move paddle accordingly
     */
    public void timePassed() {
        if (keyboard.isPressed(keyboard.LEFT_KEY)) {
            moveLeft();
            timePassed++;
        }
        if (keyboard.isPressed(keyboard.RIGHT_KEY)) {
            moveRight();
            timePassed--;
        }
    }

    /**
     * draw self on surface.
     * @param d is th egiven surface
     */
    public void drawOn(DrawSurface d) {
        int topLeftX = (int)  rec.getUpperLeft().getX();
        int topLeftY = (int)  rec.getUpperLeft().getY();
        d.setColor(color);
        d.fillRectangle(topLeftX, topLeftY, (int) rec.getWidth(), (int) rec.getHeight());
        d.setColor(Color.black);
        d.drawRectangle(topLeftX, topLeftY, (int) rec.getWidth(), (int) rec.getHeight());
    }

    /**
     * accessor.
     * @return the rectangle
     */
    public Rectangle getCollisionRectangle() {
        return rec;
    }

    /**
     * check if hit.
     * @param hitter is the ball hitting. (unused for paddle block)
     * @param collisionPoint point of collision
     * @param currentVelocity velocity of impact
     * @return new velocity to ball
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // set default newVelocity to current velocity
        Velocity newVelocity = currentVelocity;
        double dX = currentVelocity.getDx();
        double dY = currentVelocity.getDy();
        Line trajectory = new Line(collisionPoint.getX(), collisionPoint.getY(), currentVelocity.getDx(),
        currentVelocity.getDy());
        //if collision exist, check what type of collision will occur, and set new velocity accordingly
        if (trajectory.closestIntersectionToStartOfLine(this.rec) != null) {
            Point closestCollision = trajectory.closestIntersectionToStartOfLine(this.rec);
            //set all boolean values if in x and y range and if contact exists
            double yContact = closestCollision.getY();
            double xContact = closestCollision.getX();
            double upperLeftX = rec.getUpperLeft().getX();
            double upperLeftY = rec.getUpperLeft().getY();
            double bottomLeftY = upperLeftY + rec.getHeight();
            boolean withinXRange = upperLeftX - xContact <= doubleThreshold && doubleThreshold <= upperLeftX
                    + getCollisionRectangle().getWidth() - xContact;
            boolean withinYRange = bottomLeftY - yContact >= doubleThreshold && yContact - upperLeftY
                    >= doubleThreshold;
            //divide paddle into five different sections
            double paddlePart1 = rec.getUpperLeft().getX() + rec.getWidth() / 5;
            double paddlePart2 = rec.getUpperLeft().getX() + (2 * rec.getWidth()) / 5;
            double paddlePart3 = rec.getUpperLeft().getX() + (3 * rec.getWidth()) / 5;
            double paddlePart4 = rec.getUpperLeft().getX() + (4 * rec.getWidth()) / 5;
            double paddlePart5 = rec.getUpperLeft().getX() + (5 * rec.getWidth()) / 5;
            //if middle of top hit
            if (yContact - rec.getUpperLeft().getY() <= doubleThreshold && rec.getUpperLeft().getY() - yContact
                    <= doubleThreshold && withinXRange) {
                Double currentSpeed = Math.sqrt(Math.pow(dX, 2) + Math.pow(dY, 2));
                //otherwise other sides of paddle have been hit. return new corresponding velocity
                if (rec.getUpperLeft().getX() - xContact <= doubleThreshold && xContact - paddlePart1
                        <= doubleThreshold) {
                    return currentVelocity.fromAngleAndSpeed(-60, currentSpeed);
                } else if (paddlePart1 - xContact <= doubleThreshold && xContact - paddlePart2
                        <= doubleThreshold) {
                    return currentVelocity.fromAngleAndSpeed(-30, currentSpeed);
                } else if (paddlePart3 - xContact <= doubleThreshold && xContact - paddlePart4
                        <= doubleThreshold) {
                    return currentVelocity.fromAngleAndSpeed(30, currentSpeed);
                } else if (paddlePart4 - xContact <= doubleThreshold && xContact - paddlePart5
                        <= doubleThreshold) {
                    return currentVelocity.fromAngleAndSpeed(60, currentSpeed);
                } else {
                    newVelocity = new Velocity(dX, -dY);
                }
            }
            if (yContact - (rec.getUpperLeft().getY() + rec.getHeight()) <= doubleThreshold && rec.getUpperLeft().getY()
                    + rec.getHeight() - yContact <= doubleThreshold && withinXRange) {
                newVelocity = new Velocity(dX, -dY);
            }
            if (xContact == rec.getUpperLeft().getX() && withinYRange) {
                newVelocity = new Velocity(-dX, dY);
            }
            if (xContact == rec.getUpperLeft().getX() + rec.getWidth() && withinYRange) {
                newVelocity = new Velocity(-dX, dY);
            }
        }
        return newVelocity;
    }

    /**
     * add the paddle to the game.
     * @param g is th current game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);

    }
}