import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
/**
 * Created by anshi on 1/19/2017.
 */
public class FastCollinearPoints {

    private final LineSegment[] finalLss;
    private final int finalSegmentsNumber;

    public FastCollinearPoints(Point[] points) {
        testInput(points);
        Point curPoint;
        double curSlope;
        int lastJ, numOfGroup;
        Point[] groupPoints;
        Point[] sortedPoints = points.clone();
        Point fromPoint, toPoint;
        ArrayList<Point> fromPoints = new ArrayList<Point>();
        ArrayList<Point> toPoints = new ArrayList<Point>();
        int numOfPoints = points.length;
        for(int i = 0; i < numOfPoints; i++) {
            curPoint = points[i];
            Arrays.sort(sortedPoints, curPoint.slopeOrder());

            for(int j = 0; j < numOfPoints; j++) {
                curSlope = curPoint.slopeTo(sortedPoints[j]);

                lastJ = j+1;
                while(lastJ < numOfPoints && (curPoint.slopeTo(sortedPoints[lastJ]) == curSlope)) {
                    lastJ++;
                }
                lastJ--;

                if(lastJ >= j+2) {
                    numOfGroup = lastJ - j + 2;
                    groupPoints = new Point[numOfGroup];
                    groupPoints[0] = curPoint;
                    for(int m = j; m <= lastJ; m++) {
                        groupPoints[m - j + 1] = sortedPoints[m];
                    }

                    Arrays.sort(groupPoints);
                    fromPoint = groupPoints[0];
                    toPoint = groupPoints[groupPoints.length-1];
                    if (!isDuplicate(fromPoint, toPoint, fromPoints, toPoints)) {
                        fromPoints.add(fromPoint);
                        toPoints.add(toPoint);
                    }
                    j = lastJ;
                }
            }
        }

        assert(fromPoints.size() == toPoints.size());
        finalSegmentsNumber = fromPoints.size();
        finalLss = new LineSegment[finalSegmentsNumber];
        for(int i = 0; i < fromPoints.size();i++) {
            finalLss[i] = new LineSegment(fromPoints.get(i),toPoints.get(i));
        }
    }

    public int numberOfSegments() {
        int result = finalSegmentsNumber;
        return result;
    }

    public LineSegment[] segments() {
        return finalLss.clone();
    }

    private boolean isDuplicate(Point fromPoint, Point toPoint, ArrayList<Point> fromPoints, ArrayList<Point> toPoints) {
        for(int i = 0; i < fromPoints.size(); i++) {
            if (fromPoint.compareTo(fromPoints.get(i)) == 0) {
                for(int j = 0; j < toPoints.size(); j++) {
                    if(toPoint.compareTo(toPoints.get(j)) == 0) return true;
                }
            }
        }
        return false;
    }

    private void testInput(Point[] points) {
        if (points == null)
            throw new java.lang.NullPointerException();
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null)
                throw new java.lang.NullPointerException();

            for (int j = i + 1; j < points.length; j++) {
                if (points[i].compareTo(points[j]) == 0)
                    throw new java.lang.IllegalArgumentException();
            }
        }
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        System.out.println("Total LS number is " + collinear.numberOfSegments());
        StdDraw.show();
    }

}
