/**
 * Created by anshi on 1/14/2017.
 */
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Objects;
import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPointsOld {
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
//            System.out.println(points[i].toString());
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
        FastCollinearPointsOld collinear = new FastCollinearPointsOld(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        System.out.println("Total LS number is " + collinear.numberOfSegments());
        StdDraw.show();
    }

    public FastCollinearPointsOld(Point[] points) {
        testInput(points);
        ArrayList<LineSegment> lss = new ArrayList<LineSegment>();
        int pointsOfSegNum, curPointIndex;
        Point[] originPoints = points.clone();

        for(int i = 0; i < points.length; i++)
        {
            int curJ;
            Point[] collinearPoints;
            Point minPoint,maxPoint;
            Point curPoint = originPoints[i];
            Arrays.sort(points, curPoint.slopeOrder());
            for(int j = 1; j < points.length; j++) {
                double curSlope = curPoint.slopeTo(points[j]);
                curJ = j;
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
                    addLS(minPoint,maxPoint,lss);
                }
            }
        }

        finalSegmentsNumber = lss.size();
        finalLss = new LineSegment[finalSegmentsNumber];

        for(int i = 0; i < lss.size(); i++) {
            finalLss[i] = lss.get(i);
        }

    }

    private void addLS(Point minPoint, Point maxPoint, ArrayList<LineSegment> lss) {
        LineSegment curls = new LineSegment(minPoint,maxPoint);
        String targetStr = curls.toString();
        for(int i = 0; i < lss.size(); i++) {
            if (Objects.equals(targetStr,lss.get(i).toString()))
                return;
        }
        lss.add(curls);
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
