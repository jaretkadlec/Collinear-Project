import java.util.Arrays;
import edu.princeton.cs.algs4.StdOut;
public class BruteCollinearPoints {
    private LineSegment[] segmentArr;
    private int numSegments = 0;
    public BruteCollinearPoints(Point[] points) {
        // finds all line segments containing 4 points
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
        // Point arb = points[0];
        Point[] newPoints = points.clone();
        Arrays.sort(newPoints);
        for (int i = 0; i < newPoints.length - 3; i++) {
            Point p = newPoints[i];
            // boolean segExists = false;
            // boolean startPoint = false;
            Point endpoint = new Point(0, 0);
            // Point arb1 = new Point(10000, 0);
            // Point arb2 = new Point(30000, 0);
            // if (p.compareTo(arb1) == 0) {
            //     startPoint = true;
            // }
            for (int j = i+1; j < points.length - 2; j++) {
                Point q = newPoints[j];
                for (int k = j+1; k < points.length - 1; k++) {
                    Point r = newPoints[k];
                    for (int l = k+1; l < points.length; l++) {
                        Point s = newPoints[l];
                        double slopePQ = p.slopeTo(q);
                        double slopePR = p.slopeTo(r);
                        double slopePS = p.slopeTo(s);
                        // if (startPoint && s.compareTo(arb2) == 0) {
                        //     // StdOut.println("At desired segment");
                        //     StdOut.println(slopePQ);
                        //     StdOut.println(slopePR);
                        //     StdOut.println(slopePS);
                        // }
                        if (slopePQ == slopePR && slopePR == slopePS) {
                            // segExists = true;
                            endpoint = s;
                            LineSegment seg = new LineSegment(p, endpoint);
                            // StdOut.println("SEGMENT FOUND: " + seg.toString());
                            segmentArr[numSegments] = seg;
                            numSegments += 1;
                        }
                    }
                }
            }
        }
    }
    public int numberOfSegments() {
        // the number of line segments
        return numSegments;
    }
    public LineSegment[] segments()  {
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

        // // read the n points from a file
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
        // BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        // LineSegment[] lineSegments = collinear.segments();
        // for (int i = 0; i < lineSegments.length; i++) {
        //     LineSegment ls = lineSegments[i];
        //     StdOut.println(ls.toString());
        // }
        // StdOut.println("Number of line segments: " + collinear.numSegments);
        // StdOut.println("Points array after sort");
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