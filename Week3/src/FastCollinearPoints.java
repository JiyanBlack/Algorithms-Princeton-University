/**
 * Created by anshi on 1/14/2017.
 */
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Objects;
import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
    private final LineSegment[] finalLss;
    private final int finalSegmentsNumber;

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
        StdDraw.show();
    }

    public FastCollinearPoints(Point[] points) {
        testInput(points);
        ArrayList<LineSegment> lss = new ArrayList<LineSegment>();
        int pointsOfSegNum, curPointIndex;

        for(int i = 0; i < points.length; i++)
        {
            int curJ;
            Point[] collinearPoints;
            Point minPoint,maxPoint;
            Point curPoint = points[i];
            Arrays.sort(points, curPoint.slopeOrder());

            for(int j = 0; j < points.length; j++) {
                double curSlope = curPoint.slopeTo(points[j]);
                curJ = j + 1;

                while(curJ < points.length && curPoint.slopeTo(points[curJ]) == curSlope) {
                    curJ++;
                }
                curJ--;
                // if there are four points in a line
                if (curJ >= j + 2) {
                    pointsOfSegNum = 2 + curJ - j;
                    collinearPoints = new Point[pointsOfSegNum];
                    collinearPoints[0] = curPoint;
                    for(int m = j; m <= curJ; m++) {
                        curPointIndex = m-j+1;
                        collinearPoints[curPointIndex] = points[m];
                    }

                    Arrays.sort(collinearPoints);
                    minPoint = collinearPoints[0];
                    maxPoint = collinearPoints[collinearPoints.length-1];
                    LineSegment curLs = new LineSegment(minPoint,maxPoint);

                    if (!isDuplicate(curLs, lss)) {
                        lss.add(curLs);
                    }
                }
            }
        }
        finalSegmentsNumber = lss.size();
        finalLss = new LineSegment[finalSegmentsNumber];
        for(int i = 0; i < lss.size(); i++) {
            finalLss[i] = lss.get(i);
        }
    }

    private boolean isDuplicate(LineSegment ls, ArrayList<LineSegment> lss) {
        String targetStr = ls.toString();
        for(int i = 0; i < lss.size(); i++) {
            if (Objects.equals(targetStr,lss.get(i).toString()))
                return true;
        }
        return false;
    }

    public int numberOfSegments() {
        return finalSegmentsNumber;
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
