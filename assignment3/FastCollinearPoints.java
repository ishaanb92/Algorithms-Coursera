import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class FastCollinearPoints {
    
    private Point[] points;
    private LineSegment[] lines;
    private List<LineSegment> linesList;
    private Point[] sortedPoints;
    private int N;
    
    public FastCollinearPoints(Point[] points) {
        this.points = points;
        checkConditions();
        N = points.length;
        this.linesList = new ArrayList<LineSegment>();
        // Sort the points
        sortedPoints = this.points.clone();
        // Sort based on x-y co-ordinates (natural order)
        Arrays.sort(sortedPoints);
        
    } // finds all line segments containing 4 or more points
    public int numberOfSegments() {
        return lines.length;
        
    } // the number of line segments
    public LineSegment[] segments() {
        findLines();
        lines = new LineSegment[linesList.size()];
        lines = linesList.toArray(lines);
        return lines;
        
    } // the line segments
    
    private void findLines() { // order of n^2 * lgN number of compares
        double slope1,slope2,slope3;
        int count=0,j,k;
        for (int i = 0; i < N; i++) {
            Point[] aux = sortedPoints.clone();
            System.out.println("Original Array : "+Arrays.toString(aux));
            Arrays.sort(aux,sortedPoints[i].slopeOrder());
            Point origin = aux[0];
            System.out.println("Re-sorted array : " + Arrays.toString(aux));
            j = 1;
            while(j < N-1) {
                System.out.println("Origin : "+origin.toString()+" Point considered : "+aux[j]);
                k = j+1;
                while(origin.slopeTo(aux[j]) == origin.slopeTo(aux[k])) {
                    k++;
                    if (k>N-1)
                        break;
                }
                if (k-j>=3 && k <= N-1) {
                    linesList.add(new LineSegment(origin,aux[k-1]));
                    System.out.println(origin.toString() + aux[j].toString() + aux[k-1].toString() + " are collinear");
                }

                j = k;                
            }
        }
         
    } // Sorting based approach to find lines
    
    private void checkConditions() {
        
        // Check if points array is null
        if (this.points == null) {
            throw new java.lang.IllegalArgumentException();
        }
        // Check if any point is null
        for (int i = 0; i < this.points.length; i++) {
            if (this.points[i] == null)
                throw new java.lang.IllegalArgumentException();
        }
        
        // Check if any 2 distinct points are identical
        for (int i = 0; i < this.points.length; i++) {
            for (int j = i+1; j < this.points.length;j++ ) {
                if (this.points[i].compareTo(this.points[j]) == 0)
                    throw new java.lang.IllegalArgumentException();          
            }
        }
    }
    
    // Client
    public static void main(String[] args) {

        // read the n points from a file
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
}