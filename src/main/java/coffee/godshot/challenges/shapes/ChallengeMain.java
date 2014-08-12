package coffee.godshot.challenges.shapes;

/**
 * Created by jknehr on 8/11/14.
 */
public class ChallengeMain {

    public static void main(String[] args)
    {


        /**
         *  Challenge 1 - find the min/max boundary points.
         */
        byte[][] scene = {
                //0  1  2  3  4  5  6  7  8  9
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},//0
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 0},//1
                {0, 0, 0, 1, 0, 1, 0, 0, 0, 0},//2
                {0, 0, 1, 0, 0, 0, 1, 0, 0, 0},//3
                {0, 1, 0, 0, 0, 0, 0, 1, 0, 0},//4
                {0, 1, 0, 0, 0, 0, 1, 0, 0, 0},//5
                {0, 1, 0, 0, 0, 0, 1, 0, 0, 0},//6
                {0, 1, 1, 1, 1, 0, 1, 0, 0, 0},//7
                {0, 1, 1, 0, 0, 1, 1, 0, 0, 0},//8
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0} //9
        };
        Shape shape = new Shape(scene);
        System.out.println("boundary pts = " + shape.getBoundary());
        System.out.println("shape.getMaxX() = " + shape.getMaxX());
        System.out.println("shape.getMinX() = " + shape.getMinX());
        System.out.println("shape.getMaxY() = " + shape.getMaxY());
        System.out.println("shape.getMinY() = " + shape.getMinY());



        /**
         * Challenge 2 - see if one shape is contained within another
         */
        byte[][] scene2 = {
                //0  1  2  3  4  5  6  7  8  9
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},//0
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},//1
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},//2
                {0, 0, 0, 0, 1, 1, 0, 0, 0, 0},//3
                {0, 0, 0, 1, 1, 1, 0, 0, 0, 0},//4
                {0, 0, 0, 1, 1, 1, 0, 0, 0, 0},//5
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},//6
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},//7
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},//8
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0} //9
        };
        Shape shape2 = new Shape(scene2);

        boolean contains = shape.contains(shape2);
        System.out.println("contains = " + contains);



        /**
         * Challenge 3 - see if one shape is adjacent to another shape
         */
        byte[][] scene3 = {
                //0  1  2  3  4  5  6  7  8  9
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},//0
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1},//1
                {0, 0, 0, 0, 0, 0, 0, 0, 1, 1},//2
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 1},//3
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},//4
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},//5
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},//6
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},//7
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},//8
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0} //9
        };
        Shape shape3 = new Shape(scene3);

        boolean isAdjacent = shape.isAdjacentTo(shape3);
        System.out.println("isAdjacent = " + isAdjacent);


    }

}
