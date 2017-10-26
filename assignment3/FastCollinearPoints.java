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
        int k,count;
        for (int i = 0; i < N; i++) {
            // Sort the points ahead of points[i] by the slope they make with points[i] 
            Arrays.sort(sortedPoints,i+1,N-1,sortedPoints[i].slopeOrder());
            for (int j = i+1; j < N; j++) {
                k = j+1;
                count = 0; // Keeps a count a number of collinear points found with i
                while(sortedPoints[i].slopeTo(sortedPoints[j]) != sortedPoints[i].slopeTo(sortedPoints[k])) { // Keep looking ahead
                    k++;
                    count++;
                }
                if (count >= 2) {
                    linesList.add(new LineSegment(sortedPoints[i],sortedPoints[k]));
                }
                
                /*slope1 = sortedPoints[i].slopeTo(sortedPoints[j]);
                slope2 = sortedPoints[i].slopeTo(sortedPoints[j+1]);
                slope3 = sortedPoints[i].slopeTo(sortedPoints[j+2]);
                if ((slope1 == slope2) && (slope2 == slope3)) {
                    // i->(j+2) is a line segment
                    linesList.add(new LineSegment(sortedPoints[i],sortedPoints[j+2]));
                }*/
                
                
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
}