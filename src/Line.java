//id 324680438

import java.util.List;

/**
 * a line.
 *
 * @author David Monheit
 * @version 1.0.0  14.3.2021 ass5
 */
public class Line {
    //Threshold for comparing doubles
    private final double doubleThreshold = 0.00000000001;
    //staring and ending point
    private Point start;
    private Point end;

    /**
     * constructor.
     *
     * @param start is beginning of line
     * @param end   is end of line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * constructor.
     *
     * @param x1 is x value of beginning of line
     * @param y1 is y value beginning of line
     * @param x2 is end of line x value
     * @param y2 is end of line y value
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * accessor.
     *
     * @return length of line
     */
    public double length() {
        return start.distance(end);
    }

    /**
     * accessor.
     *
     * @return middle of line
     */
    public Point middle() {
        double xMid = (this.start.getX() + this.end.getX()) / 2;
        double yMid = (this.start.getY() + this.end.getY()) / 2;
        Point middle;
        middle = new Point(xMid, yMid);

        return middle;
    }

    /**
     * accessor.
     *
     * @return start of line
     */
    public Point start() {
        return this.start;
    }

    /**
     * accessor.
     *
     * @return end of line
     */
    public Point end() {
        return this.end;
    }

    /**
     * check if line intersects with other line.
     *
     * @param other is other line
     * @return true or false
     */
    public boolean isIntersecting(Line other) {
        if (this.intersectionWith(other) != null) {
            return true;
        }
        return false;
    }

    /**
     * check intersection position with other line or false if no intersection.
     *
     * @param other the other line
     * @return return intersection position or false if no intersection
     */
    public Point intersectionWith(Line other) {
        double interX;
        double interY;
        //get delta x1 value
        double deltaX1 = this.start.getX() - this.end.getX();
        //get delta x2 value
        double deltaX2 = other.start.getX() - other.end.getX();
        //calculate slope
        double m1 = (this.start.getY() - this.end.getY()) / deltaX1;
        double m2 = (other.start.getY() - other.end.getY()) / deltaX2;
        //check if both are infinite
        if (Double.isInfinite(m1) && Double.isInfinite(m2)) {
            boolean yStartInRange = other.start.getY() - this.start.getY() <= doubleThreshold && this.start.getY()
                    - other.end.getY() <= doubleThreshold;
            boolean yEndInRange = other.start.getY() <= this.end.getY() && this.end.getY() <= other.end.getY();
            if (yStartInRange) {
                interX = this.start.getX();
                interY = this.start.getY();
            } else {
                return null;
            }
        } else if (Double.isInfinite(m1) && Double.isFinite(m2)) { //using this!!
            interX = this.start.getX();
            double secondYIntersect = other.start.getY() - m2 * other.start.getX();
            interY = m2 * interX + secondYIntersect;
        } else if (Double.isFinite(m1) && Double.isInfinite(m2)) {
            interX = other.start.getX();
            double firstYIntersect = this.start.getY() - m1 * this.start.getX();
            interY = m1 * interX + firstYIntersect;
        } else {
            //get intersection point
            double firstYIntersect = this.start.getY() - m1 * this.start.getX();
            double secondYIntersect = other.start.getY() - m2 * other.start.getX();
            interX = (firstYIntersect - secondYIntersect) / (m2 - m1);
            interY = m1 * interX + firstYIntersect;
        }
        //create new point of intersection
        Point meet;
        meet = new Point(interX, interY);
        //check that intersection point is within both line ranges
        boolean firstY1RangeCheck = this.start.getY() - meet.getY() <= doubleThreshold && meet.getY()
                - this.end.getY() <= doubleThreshold;
        boolean secondY1RangeCheck = this.end.getY() - meet.getY() <= doubleThreshold && meet.getY()
                - this.start.getY() <= doubleThreshold;
        boolean firstX1RangeCheck = this.start.getX() - meet.getX() <= doubleThreshold && meet.getX()
                - this.end.getX() <= doubleThreshold;
        boolean secondX1RangeCheck = this.end.getX() - meet.getX() <= doubleThreshold && meet.getX()
                - this.start.getX() <= doubleThreshold;
        boolean firstY2RangeCheck = other.start.getY() - meet.getY() <= doubleThreshold && meet.getY()
                - other.end.getY() <= doubleThreshold;
        boolean secondY2RangeCheck = other.end.getY() - meet.getY() <= doubleThreshold && meet.getY()
                - other.start.getY() <= doubleThreshold;
        boolean firstX2RangeCheck = other.start.getX() - meet.getX() <= doubleThreshold && meet.getX()
                - other.end.getX() <= doubleThreshold;
        boolean secondX2RangeCheck = other.end.getX() - meet.getX() <= doubleThreshold && meet.getX()
                - other.start.getX() <= doubleThreshold;
        //check boolean results
        boolean inRangeY1 = firstY1RangeCheck || secondY1RangeCheck;
        boolean inRangeX1 = firstX1RangeCheck || secondX1RangeCheck;
        boolean inRangeY2 = firstY2RangeCheck || secondY2RangeCheck;
        boolean inRangeX2 = firstX2RangeCheck || secondX2RangeCheck;
        //if in range, return meeting point otherwise return false
        if (inRangeY1 && inRangeX1 && inRangeY2 && inRangeX2) {
            return meet;
        }
        return null;
    }

    /**
     * check if line are equal.
     *
     * @param other is the other line
     * @return boolean value if equal or not
     */
    public boolean equals(Line other) {
        if (this.start == other.start && this.end == other.end) {
            return true;
        }
        if (this.start == other.end && this.end == other.start) {
            return true;
        }
        return false;
    }

    /**
     * find closest intersection to start of line.
     *
     * @param rect is the rectangle to check
     * @return such a closest point
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        Line line = new Line(start, end);
        Point closestPoint = null;
        List<Point> pointsOfContact = rect.intersectionPoints(line);
        if (!pointsOfContact.isEmpty()) {
            double distance = Double.MAX_VALUE;
            for (Point i : pointsOfContact) {
                if (start.distance(i) - distance <= doubleThreshold) {
                    distance = start.distance(i);

                    closestPoint = i;
                }
                if (start.distance(i) - 0 <= doubleThreshold) {
                    closestPoint = i;
                }
            }
        }
        return closestPoint;
    }
}
