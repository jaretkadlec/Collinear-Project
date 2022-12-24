import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import java.util.Arrays;
import java.lang.IllegalArgumentException;
public class FastCollinearPoints {
    private LineSegment[] segmentArr;
    private int numSegments = 0;
    public FastCollinearPoints(Point[] points) {
        // finds all line segments containing 4 or more points
        if (points == null) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) {
                throw new IllegalArgumentException();
            }
            for (int j = i + 1; j < points.length; j++) {
                if (points[i] == points[j]) {
                    throw new IllegalArgumentException();
                }
            }
        }
        segmentArr = new LineSegment[points.length];
        Point[] segmentPoints = new Point[points.length * 2];
        int idx = 0;
        Point[] pointsBySlope = new Point[points.length];
        // Arrays.sort(points);
        Point p = new Point(0, 0);
        Point endpoint = new Point(0, 0);
        for (int i = 0; i < points.length; i++) {
            p = points[i];
            pointsBySlope = points.clone();
            Arrays.sort(pointsBySlope, p.slopeOrder());
            // Point q1 = pointsBySlope[i+1];
            // Point q2 = pointsBySlope[i+2];
            // Point q3 = pointsBySlope[i+3];
            int numPoints = 0;
            for (int j = 0; j < pointsBySlope.length - 2; j++) {
                Point q1 = pointsBySlope[j];
                Point q2 = pointsBySlope[j+1];
                Point q3 = pointsBySlope[j+2];
                // StdOut.println("What the heck");
                if (p.slopeTo(q1) == p.slopeTo(q2) && p.slopeTo(q2) == p.slopeTo(q3)) {
                    // StdOut.println("What the heck");
                    numPoints = 4;
                    Point[] lsArr = new Point[4];
                    lsArr[0] = p;
                    lsArr[1] = q1;
                    lsArr[2] = q2;
                    lsArr[3] = q3;
                    Arrays.sort(lsArr);
                    p = lsArr[0];
                    endpoint = lsArr[3];
                }
            }
            if (numPoints == 4) {
            LineSegment seg = new LineSegment(p, endpoint);
            // StdOut.println("SEGMENT FOUND: " + seg.toString());
            // for (int j = 0; j < segmentPoints.length; j++) {
            //     if (segmentPoints[j] == p || segmentPoints[j] == endpoint) {
            //         pointContained = true;
            //     }
            // }
            boolean segmentContained = false;
            for (int j = 0; j < segmentPoints.length - 1; j++) {
                if (segmentPoints[j] == p && segmentPoints[j+1] == endpoint) {
                    segmentContained = true;
                }
            }
            if (!segmentContained) {
                // StdOut.println("SEGMENT FOUND: " + seg.toString());
                segmentArr[numSegments] = seg;
                numSegments += 1;
                segmentPoints[idx] = p;
                idx += 1;
                segmentPoints[idx] = endpoint;
                idx += 1;
            }
        }
            // StdOut.println("Comparing to origin point: " + p.toString());
            // for (int j = 0; j < pointsBySlope.length; j++) {
            //     Point q = pointsBySlope[j];
            //     if (p == q) {
            //         StdOut.println(q.toString() + ", 0");
            //     }
            //     else {
            //         StdOut.println(q.toString() + ", " + p.slopeTo(q));
            //     }
            // }
            // StdOut.println("----------------------");
            // StdOut.println("number of collinear points: " + numPoints);
            // StdOut.println(pointsBySlope[i].toString());
            // StdOut.println("collinear points for " + p.toString());
            // for (int k = 0; k < pArr.length; k++) {
            //     StdOut.println(pArr[k]);
            // }
            // if (numPoints >= 4) {
            //     LineSegment seg = new LineSegment(p, endpoint);
            //     segmentArr[i] = seg;
            //     numSegments += 1;
            // }
            // if (p.slopeTo(q1) == p.slopeTo(q2) && p.slopeTo(q2) == p.slopeTo(q3)) {
            //     LineSegment seg = new LineSegment(p, q3);
            //     segmentArr[i] = seg;
            //     numSegments += 1;
            // }
            // StdOut.println("Search range: " + p1.toString() + " - " + p4.toString());
            // LineSegment testSeg12 = new LineSegment(p1, p2);
            // StdOut.println(testSeg12.toString() + "Slope: " + p1.slopeTo(p2));
            // LineSegment testSeg13 = new LineSegment(p1, p3);
            // StdOut.println(testSeg13.toString() + "Slope: " + p1.slopeTo(p3));
            // LineSegment testSeg14 = new LineSegment(p1, p4);
            // StdOut.println(testSeg14.toString() + "Slope: " + p1.slopeTo(p4));
            // StdOut.println("----------------");
            // if (p1.slopeTo(p2) == p1.slopeTo(p3) && p1.slopeTo(p3) == p1.slopeTo(p4)) {
            //     LineSegment ls = new LineSegment(p1, p4);
            //     segmentArr[i] = ls;
            //     numSegments += 1;
            // }
        }
    }
    public int numberOfSegments() {
        // the number of line segments
        return numSegments;
    }
    public LineSegment[] segments() {
        // the line segments
        LineSegment[] ls = new LineSegment[numSegments];
        int idx = 0;
        for (int i = 0; i < segmentArr.length; i++) {
            if (segmentArr[i] != null) {
                ls[idx] = segmentArr[i];
                idx += 1;
            }
        }
        return ls;
    }
    public static void main(String[] args) {

        // read the n points from a file
        // In in = new In(args[0]);
        // int n = in.readInt();
        // Point[] points = new Point[n];
        // // StdOut.println("Points array before sort");
        // for (int i = 0; i < n; i++) {
        //     int x = in.readInt();
        //     int y = in.readInt();
        //     points[i] = new Point(x, y);
        //     // Point test = points[i];
        //     // StdOut.println(test.toString());
        // }
        // FastCollinearPoints collinear = new FastCollinearPoints(points);
        // // // StdOut.println("Points array after sort");
        // StdOut.println("Number of Segments: " + collinear.numSegments);
        // LineSegment[] segArr = collinear.segments();
        // for (int i = 0; i < segArr.length; i++) {
        //     LineSegment test = segArr[i];
        //     StdOut.println(test.toString());
        // }
        // for (int i = 0; i < points.length; i++) {
        //     Point test = points[i];
        //     StdOut.println(test.toString());
        // }

        // // draw the points
        // StdDraw.enableDoubleBuffering();
        // StdDraw.setXscale(0, 32768);
        // StdDraw.setYscale(0, 32768);
        // for (Point p : points) {
        //     p.draw();
        // }
        // StdDraw.show();

        // // print and draw the line segments
        // FastCollinearPoints collinear = new FastCollinearPoints(points);
        // for (LineSegment segment : collinear.segments()) {
        //     StdOut.println(segment);
        //     segment.draw();
        // }
        // StdDraw.show();
    }
}