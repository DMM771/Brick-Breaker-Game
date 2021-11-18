//id 324680438
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;
/**
 * Create Blocks.
 * @author David Monheit
 * @version 1.0.0  25.4.2021 ass5
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private final List<HitListener> hitListeners = new ArrayList<>();
    private final Rectangle rec;
    private final java.awt.Color color;
    private GameEnvironment gameEnvironment;
    private final double doubleThreshold = 0.00000000001;

    /**
     * Constructor.
     * @param rec is the rectangle shape
     * @param color is the color
     */
    public Block(Rectangle rec, java.awt.Color color) {
        this.rec = rec;
        this.color = color;
    }

    /**
     * mothod to remove ball from game.
     * @param gameLevel is th game
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
        hitListeners.remove(this);
    }
    /**
     * method notify that his has occurred.
     * @param hitter is the ball hitting
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }


    @Override
    public Rectangle getCollisionRectangle() {
        return rec;
    }

    /**
     * detect if hit.
     * @param hitter is the ball that has hit the block
     * @param collisionPoint is the point of contact
     * @param currentVelocity is the current velocity before hit
     * @return velocity after contact
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        //get ball current speed
        double dX = currentVelocity.getDx();
        double dY = currentVelocity.getDy();
        // set default newVelocity to current velocity
        Velocity newVelocity = currentVelocity;
        Line trajectory = new Line(collisionPoint.getX(), collisionPoint.getY(), currentVelocity.getDx(),
                currentVelocity.getDy());
        //if collision exist, check what type of collision will occur, and set new velocity accordingly
        if (trajectory.closestIntersectionToStartOfLine(this.rec) != null) {
            Point closestCollision = trajectory.closestIntersectionToStartOfLine(this.rec);
            //get contact and upper left x and y values
            double yContact = closestCollision.getY();
            double xContact = closestCollision.getX();
            double upperLeftX = rec.getUpperLeft().getX();
            double upperLeftY = rec.getUpperLeft().getY();
            double bottomLeftY = upperLeftY + rec.getHeight();
            //check if within x range
            boolean withinXRange = upperLeftX - xContact <= doubleThreshold  &&  doubleThreshold <= upperLeftX
                    + getCollisionRectangle().getWidth() - xContact;
            //check if within y range
            boolean withinYRange = bottomLeftY - yContact >= doubleThreshold  && yContact - upperLeftY
                    >= doubleThreshold;

            if (yContact - rec.getUpperLeft().getY() <= doubleThreshold && rec.getUpperLeft().getY() - yContact
                    <= doubleThreshold && withinXRange) {
                newVelocity = new Velocity(dX, -dY);
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

        //set new speed
        this.notifyHit(hitter);
        return newVelocity;
    }

    /**
     * method to draw self on given surface.
     * @param surface is the given surface
     */
    public void drawOn(DrawSurface surface) {
        //draw the ball
        int topLeftX = (int)  Math.round(rec.getUpperLeft().getX());
        int topLeftY = (int)  Math.round(rec.getUpperLeft().getY());
        surface.setColor(color);
        surface.fillRectangle(topLeftX, topLeftY, (int) rec.getWidth(), (int) rec.getHeight());
        surface.setColor(Color.black);
        surface.drawRectangle(topLeftX, topLeftY, (int) rec.getWidth(), (int) rec.getHeight());

    }

    /**
     * keep track of time passed.
     */
    @Override
    public void timePassed() {

    }

    /**
     * add self to game.
     * @param g is the given game
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
}
