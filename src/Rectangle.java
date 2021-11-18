//id 324680438
import java.util.ArrayList;
import java.util.List;
/**
 * @author David Monheit
 * @version 1.0.0  25.4.2021 ass5
 * Rectangle class.
 */
public class Rectangle {
    private double width;
    private double height;
    private Point upperLeft;
    private Point upperRight;
    private Point bottomLeft;
    private Point bottomRight;
    private Line leftEdge;
    private Line rightEdge;
    private Line topEdge;
    private Line bottomEdge;
    private List<Line> listOfSides;

    /**
     * constructor.
     * @param upperLeft is the upper left of rectangle
     * @param width is the width of rectangle
     * @param height is the height of rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.upperRight = new Point(upperLeft.getX() + width, upperLeft.getY());
        this.bottomLeft = new Point(upperLeft.getX(), upperLeft.getY() + height);
        this.bottomRight = new Point(upperLeft.getX() + width, upperLeft.getY() + height);
        leftEdge = new Line(upperLeft, bottomLeft);
        rightEdge = new Line(upperRight, bottomRight);
        topEdge = new Line(upperLeft, upperRight);
        bottomEdge = new Line(bottomLeft, bottomRight);
        listOfSides = new ArrayList<>();
        listOfSides.add(leftEdge);
        listOfSides.add(rightEdge);
        listOfSides.add(topEdge);
        listOfSides.add(bottomEdge);
    }
    /**
     * a list of intersection point.
     * @param line is the line to check
     * @return a (possibly empty) List of intersection points
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> listOfIntersections = new ArrayList<>();
        for (Line i : listOfSides) {
            if (i.intersectionWith(line) != null) {
                listOfIntersections.add(i.intersectionWith(line));
            }
        }
        return listOfIntersections;
    }


    /**
     * accessor.
     * @return the width of rectangle
     */
    public double getWidth() {
        return width;
    }
    /**
     * accessor.
     * @return the height of rectangle
     */
    public double getHeight() {
        return height;
    }

    /**
     * accessor.
     * @return the upper left point of rectangle
     */
    public Point getUpperLeft() {
        return upperLeft;
    }
}