package coffee.godshot.challenges.shapes;


import java.util.ArrayList;
import java.util.List;

public class Shape
{

    private final byte[][] scene;
    private final List<Point> boundary;

    /**
     * Constructs a shape from a NxN scene.
     * @param scene
     */
    public Shape(byte[][] scene) {
        this.scene = scene;
        this.boundary = new ArrayList<Point>();
        findBoundary();
    }


    /**
     * method for finding the boundary of the shape.  We start by finding the First boundary Point
     * using a brute force method.  We then start a quasi-informed heuristic search on the rest of the graph,
     * traversing what we find to be boundary points until we come full circle back to the
     * starting point.  Foreach boundary point that we traverse, we keep a record of it in the boundary list.
     * This boundary list will then be used to derive the values needed for all 3 challenges.
     */
    protected void findBoundary() {
        Point firstPoint = getFirstPoint();
        boundary.add(firstPoint);


        int bestHeuristic;
        int counter = 0;
        int newH;

        Point currentPt = new Point(firstPoint.getX(), firstPoint.getY(), firstPoint.getValue());

        while(true) {
            counter++;
            bestHeuristic = 0;


            List<Point> neighbors = getNeighbors(currentPt);
            if(neighbors.size() == 0) {
                //no more valid neighbors
                break;
            }

            Point newBoundaryPt = new Point(0, 0, (byte)0);

            for(Point point : neighbors) {

                newH = heuristic(point);
                if(newH > bestHeuristic) {
                    newBoundaryPt = point;
                    bestHeuristic = newH;
                }

            }

            if(counter > 3 && newBoundaryPt.isAdjacent(firstPoint)) {
                break;
            }

            currentPt = newBoundaryPt;
            boundary.add(newBoundaryPt);

        }
    }


    /**
     * List of points that exist on the shapes boundary
     * @return
     */
    public List<Point> getBoundary() {
        return boundary;
    }


    /**
     * Helper method for pulling values.  If the point is OutOfBounds, return
     * a empty value
     * @param x
     * @param y
     * @return
     */

    private byte getValue(int x, int y) {
        if(y < 0 || y >= scene.length) return 0;
        if(x < 0 || x >= scene.length) return 0;
        return scene[y][x];
    }


    /**
     * Heuristic function that assists in the search of the graph.
     * The heuristic favours Points that have more 'blank' spaces adjacent
     * to it, hopefully indicating that it is a boundary Point and not
     * an inner Point.  Additionally, if it is at the bounds of the scene, it's adjacent
     * Points that would otherwise be OutOfBounds of the scene are considered blank points,
     * and therefore would contribute to the heuristic value for finding boundary points.
     * @param point
     * @return
     */
    private int heuristic(Point point) {
        int value = 0;
        int x = point.getX();
        int y = point.getY();

        for(int xv = -1; xv <= 1; xv++) {
            for(int yv = -1; yv <= 1; yv++) {
                if(xv == 0 && yv == 0) continue;
                if(getValue(x+xv, y+yv) == 0)
                    value++;
            }
        }

        return value;
    }


    /**
     * Finds the first starting point.  Currently, uses brute force method to search
     * for the first point of the shape.
     * TODO: this can search more intelligently
     * @return The first point in the shape
     */
    private Point getFirstPoint() {
        // find first point, brute force method
        int row, col;
        outside:
        for(row = 0; row < scene.length; row++) {
            for(col = 0; col < scene[row].length; col++) {
                byte value = scene[row][col];
                if(value != 0) {
                    return new Point(col, row, value);
                }
            }
        }
        throw new IllegalStateException("Scene does not contain any shape");
    }


    /**
     * Gets all the neighbor nodes in the graph.  Valid neighbors
     * are ones that 1) have a value and 2) have not already been visited
     * @param currentPt
     * @return
     */
    private List<Point> getNeighbors(Point currentPt) {
        List<Point> points = new ArrayList<Point>();

        Point point = new Point();

        for(int xv = -1; xv <= 1; xv++) {
            for(int yv = -1; yv <= 1; yv++) {

                if(yv == 0 && xv == 0) continue;

                point.setX(currentPt.getX()+xv);
                point.setY(currentPt.getY()+yv);
                if(getValue(point.getX(), point.getY()) != 0 && !boundary.contains(point)) {
                    points.add(point);
                    point = new Point();
                }
            }
        }

        return points;
    }



    /**
     * Solution to Challenge #2.
     * A rather brute force way of comparing
     * the two shapes boundaries to see if they contain adjacent points.
     * Returns true upon the first match, false otherwise.
     * @param shape
     * @return
     */
    public boolean isAdjacentTo(Shape shape) {
        for(Point point : boundary) {

            for(Point shapePoint : shape.getBoundary()) {
                if(point.isAdjacent(shapePoint)) {
                    return true;
                }
            }

        }
        return false;
    }


    /**
     * Solution to Challenge #1
     * Determines if shape is contained within this shape.  To do so,
     * it simply finds the min/max boundaries of each of the shapes
     * and compares them.  If all the dimensions of one shape
     * fit inside the other shape, then this shape contains it.
     * @param shape
     * @return
     */
    public boolean contains(Shape shape) {
        boolean result = true;

        //top boundary
        result &= getMinY().getY() < shape.getMinY().getY();

        //bottom boundary
        result &= getMaxY().getY() > shape.getMaxY().getY();

        //right boundary
        result &= getMaxX().getX() > shape.getMaxX().getX();

        //left boundary
        result &= getMinX().getX() < shape.getMinX().getX();

        return result;
    }



    /**
     * Gets the first Point associated with the right-most pixel in the scene
     * @return
     */
    public Point getMaxX() {
        int maxX = -1;
        Point maxPt = null;
        for(Point point : boundary) {
            if(point.getX() > maxX) {
                maxX = point.getX();
                maxPt = point;
            }
        }
        return maxPt;
    }


    /**
     * Gets the first Point associated with the left-most pixel in the scene.
     * @return
     */
    public Point getMinX() {
        int minX = Integer.MAX_VALUE;
        Point minPt = null;
        for(Point point : boundary) {
            if(point.getX() < minX) {
                minX = point.getX();
                minPt = point;
            }
        }
        return minPt;
    }


    /**
     * Gets the first Point associated with the bottom-most pixel in the scene.
     * @return
     */
    public Point getMaxY() {
        int maxY = -1;
        Point maxPt = null;
        for(Point point : boundary) {
            if(point.getY() > maxY) {
                maxY = point.getY();
                maxPt = point;
            }
        }

        return maxPt;
    }


    /**
     * Gets the first Point associated with the top-most pixel in the scene.
     * @return
     */
    public Point getMinY() {
        int minY = Integer.MAX_VALUE;
        Point minPt = null;
        for(Point point : boundary) {
            if(point.getY() < minY) {
                minPt = point;
                minY = point.getY();
            }
        }

        return minPt;
    }
}