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
        // Sort based on x-y co-ordinates
        //Arrays.sort(sortedPoints);
        
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
        int j=1,count=0,k;
        for (int i = 0; i < N; i++) {
            // Sort the points ahead of points[i] by the slope they make with points[i]
            Point[] aux = sortedPoints.clone();
            Arrays.sort(aux,aux[i].slopeOrder());
            j = 1;
            count = 0;
            for (j = 1 ; j < N; j++) {
                if (count != 0)
                    j += count;
                k = j+1;
                count = 0;
                while(k < N-1 && j < N) {
                    if (aux[i].slopeTo(aux[j]) != aux[i].slopeTo(aux[k]))
                        break;
                    k++;
                    count++;
                }                
                if (count>=2) {
                    linesList.add(new LineSegment(aux[i],aux[j]));
                }
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