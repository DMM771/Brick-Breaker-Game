//id 324680438
/**
 * @author David Monheit
 * @version 1.0.0  25.4.2021 ass5
 * generate velocity
 */
public class Velocity {
    private double dx;
    private double dy;
    /**
     * constructor.
     * @param dX is speed on x axis
     * @param dY is speed on y axis
     */
    public Velocity(double dX, double dY) {
        this.dx = dX;
        this.dy = dY;
    }
    /**
     * constructor.
     * @param dX is speed on x axis
     */
    public void velocityDX(double dX) {
        this.dx = dX;
    }
    /**
     * constructor.
     * @param dY is speed on y axis
     */
    public void velocityDY(double dY) {
        this.dy = dY;
    }
    /**
     * @param angle is direction
     * @param speed is velocity
     * @return converted angle input as dx and dy values
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double newDx = speed * Math.cos(Math.toRadians(angle - 90));
        double newDy = speed * Math.sin(Math.toRadians(angle - 90));
        return new Velocity(newDx, newDy);
    }
    /**
     * @param p is the point to apply to
     * Take a point with position (x,y)
     * @return a new point with position (x+dx, y+dy)
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }
    /**
     * @return x axis speed
     */
    public double getDx() {
        return this.dx;
    }
    /**
     * @return y axis speed
     */
    public double getDy() {
        return this.dy;
    }
}