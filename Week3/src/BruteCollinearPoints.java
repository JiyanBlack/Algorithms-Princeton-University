/**
 * Created by anshi on 1/14/2017.
 */
import javax.sound.sampled.Line;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.ArrayList;
public final class BruteCollinearPoints {

    private final int finalSegmentsNumber;
    private final LineSegment[] finalLss;
    private ArrayList<LineSegment> lss;

    public static void main(String[] args) {
        Point[] ps = {new Point(2,2), new Point(1,31),new Point(1,2),new Point(1,3),new Point(1,14), new Point(3,3),new Point(1,1),  new Point(4,4),new Point(3,1)};
        BruteCollinearPoints bcp = new BruteCollinearPoints(ps);
        for(LineSegment l : bcp.segments()) {
            System.out.println(l);
        }
    }

    public BruteCollinearPoints(Point[] points) {
        testInput(points);
        Arrays.sort(points);
        lss = new ArrayList<LineSegment>();
        for(int i = 0; i < points.length - 3; i++)
            for(int j = i + 1; j < points.length - 2; j++)
                for(int m = j + 1; m < points.length -1 ; m++)
                    for(int n = m + 1; n < points.length; n++) {
                        double ij = points[i].slopeTo(points[j]);
                        double im = points[i].slopeTo(points[m]);
                        double in = points[i].slopeTo(points[n]);
                        if (ij == im && ij == in)
                            addSegment(i, n, points);
                    }
        // copy all found lineSegments to the final array;
        finalSegmentsNumber = lss.size();
        finalLss = new LineSegment[finalSegmentsNumber];
        for(int i=0;i < lss.size(); i++) {
            finalLss[i] = lss.get(i);
        }
    }

    private void addSegment(int i, int n, Point[] points) {
        LineSegment curseg = new LineSegment(points[i],points[n]);
        lss.add(curseg);
    }

    public int numberOfSegments() {
        int result = finalSegmentsNumber;
        return result;
    }

    public LineSegment[] segments() {
        return finalLss;
    }

    private void testInput (Point[] points) {
        if (points == null)
            throw new java.lang.NullPointerException();
        for(int i = 0; i < points.length; i++){
            if (points[i] == null)
                throw new java.lang.NullPointerException();

            for(int j = i+1; j < points.length; j++) {
                if (points[i].compareTo(points[j]) == 0)
                    throw new java.lang.IllegalArgumentException();
            }
        }
    }

}
