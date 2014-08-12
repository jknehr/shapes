package coffee.godshot.challenges.shapes;

/**
 * Created by jknehr
 */
public class Point {

    private int x, y;
    private byte value;


    public Point() {

    }

    public Point(int x, int y, byte value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }

    public byte getValue() {
        return this.value;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (x != point.x) return false;
        if (y != point.y) return false;

        return true;
    }

    @Override
    public String toString() {
        return "[" + x +
                "," + y +
                ']';
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }


    /**
     * Checks if point a is adjacent to this point
     * @param a point to compare
     * @return true if point is adjacent to this point
     */
    public boolean isAdjacent(Point a) {
        //0
        if(getX()+1 == a.getX() && getY() == a.getY())
            return true;

        //45
        if(getX()+1 == a.getX() && getY()+1 == a.getY())
            return true;

        //90
        if(getX() == a.getX() && getY()+1 == a.getY())
            return true;

        //135
        if(getX()-1 == a.getX() && getY()+1 == a.getY())
            return true;

        //180
        if(getX()-1 == a.getX() && getY() == a.getY())
            return true;

        //225
        if(getX()-1 == a.getX() && getY()-1 == a.getY())
            return true;

        //270
        if(getX() == a.getX() && getY()-1 == a.getY())
            return true;

        //315
        if(getX()+1 == a.getX() && getY()-1 == a.getY())
            return true;

        return false;
    }
}
