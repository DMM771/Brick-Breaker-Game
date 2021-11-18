//id 324680438
/**
 * point class.
 * @author David Monheit
 * @version 1.0.0  25.4.2021 ass5
 */
public class Point {
    private double x;
    private double y;
    //Threshold for comparing doubles
    private final double doubleThreshold = 0.00000000001;

    /**
     * constructor.
     * @param x is x coordinate
     * @param y is y coordinate
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * return distance between two points.
     * @param other is other point
     * @return the distance
     */
    public double distance(Point other) {
        double distance;
        double xDistance = x - other.x;
        double yDistance = y - other.y;
        if (-doubleThreshold <= xDistance && xDistance <= doubleThreshold) {
            distance = yDistance;
        } else if (-doubleThreshold <= yDistance && yDistance <= doubleThreshold) {
            distance = xDistance;
        } else if (-doubleThreshold <= xDistance && xDistance <= doubleThreshold && -doubleThreshold
                <= yDistance && yDistance <= doubleThreshold) {
            distance = 0;
        } else {
            distance = Math.sqrt(Math.pow(xDistance, 2) + Math.pow(yDistance, 2));
        }
        return distance;
    }

    /**
     * check if same point.
     * @param other other point
     * @return true or false boolean
     */
    public boolean equals(Point other) {
        return x == other.x && y == other.y;
    }

    /**
     * accessor.
     * @return x value
     */
    public double getX() {
        return this.x;
    }
    /**
     * accessor.
     * @return y value
     */
    public double getY() {
        return this.y;
    }
}