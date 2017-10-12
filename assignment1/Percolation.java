import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    
    private boolean [][] grid;
    private int size;
    private WeightedQuickUnionUF quickUnion;
    private int openSites;
    
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Grid size cannot be negative");
        }
        size = n;
        grid = new boolean[n][n];
        openSites = 0;
        // Create data-structure for quick-union
        quickUnion = new WeightedQuickUnionUF(n*n+2); // +2 for virtual top and bottom
        
        // create virtual top and bottom
        for (int i = 1; i < size+1; i++) {
            quickUnion.union(0,i);
            quickUnion.union(size*size+1, size*size-i);
        }
        // Init to all blocked
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = false; // false => blocked
            }
        }
    } // create n-by-n grid, with all sites blocked
    
    private void validate(int row, int col) {
        if (row < 1 || row > size || col < 1 || col > size)
            throw new IllegalArgumentException("Illegal grid size");
    }
    
    private int xyToidx(int row, int col) {
        
        validate(row, col);
        
        int i = row - 1; // Convert to the correct address
        int j = col - 1;
        int idx = i*size + j + 1; // Add 1 to adjust for virtual top
        return idx;
    }
    
    public    void open(int row, int col) {   
        validate(row, col);
        if (!isOpen(row, col)) {
            grid[row-1][col-1] = true;
            openSites++;
            
            if (row != 1) { // connect to the upper element
                if (isOpen(row-1, col))
                    quickUnion.union(xyToidx(row, col),xyToidx(row-1, col));
            }
            if (row != size) { // Lower
                if (isOpen(row+1,col))
                    quickUnion.union(xyToidx(row, col),xyToidx(row+1, col));
            }
            if (col != 1) { // Left
                if (isOpen(row,col-1))
                    quickUnion.union(xyToidx(row, col),xyToidx(row, col-1));
            }
            if (col != size) { // Right
                if (isOpen(row,col+1))
                    quickUnion.union(xyToidx(row, col),xyToidx(row, col+1));
            }
        }
               
    }// open site (row, col) if it is not open already
    
    public boolean isOpen(int row, int col) {
        
        validate(row,col);
        return grid[row-1][col-1];
    }// is site (row, col) open?
    
    public boolean isFull(int row, int col) {
        // Is row, col connected to virtual top?
        validate(row, col);
        if (!isOpen(row, col))
            return false;
        return quickUnion.connected(0,xyToidx(row, col));
    } // is site (row, col) full?
    
    public     int numberOfOpenSites() {
        return openSites;
    } // number of open sites
    
    public boolean percolates() {
        // Check if virtual top and bottom are connected
        return quickUnion.connected(0, size*size + 1);
    } // does the system percolate?

    // Test-method
    public static void main(String[] args) {
        int size = 200;
        Percolation perc = new Percolation(size);
        while(!perc.percolates()) {
            // Choose a random row,col
            int i = StdRandom.uniform(size) + 1;
            int j = StdRandom.uniform(size) + 1;
            if (!perc.isOpen(i,j)) {
                perc.open(i,j);
            }
        }
        int openSites = perc.numberOfOpenSites();
        double frac = (double) openSites/(double) (size*size);
        System.out.println(frac);
    }
}