import java.util.ArrayList;
import java.util.Arrays;
import edu.princeton.cs.algs4.In;

public class Board {
    
    private int dim;
    private int[][] blocks;
    
    public Board(int[][] blocks) {
        
        this.dim = blocks.length;
        this.blocks = new int[this.dim][this.dim];
        for (int i = 0; i < this.dim; i++) {
            for (int j = 0; j < this.dim; j++) {
                this.blocks[i][j] = blocks[i][j];
            }
        }
    
    }// construct a blocks from an n-by-n array of blocks
                                           // (where blocks[i][j] = block in row i, column j)
    public int dimension() {
        return dim;
    } // blocks dimension n
    
    public int hamming() {
        int ham = 0;
        for (int i = 0; i < dim; i++) {
            for (int j = 0 ; j < dim; j++) {
                if (this.blocks[i][j] != 0 && this.blocks[i][j] != (i*dim + j + 1)) {
                    ham++;
                }
            }
        }
        return ham;
    } // number of blocks out of place
    public int manhattan() {
        int man = 0;
        for (int i = 0; i < dim; i++) {
            for (int j = 0 ; j < dim; j++) {
                if (this.blocks[i][j] == 0) { // Ignore blank square
                    continue;
                }
                int goalRow = (this.blocks[i][j] - 1)/dim;
                int goalCol = (this.blocks[i][j] - 1)%dim;
                if (goalRow > i) {
                    man += (goalRow - i);
                }
                else {
                    man += (i - goalRow);
                }
                if (goalCol > j) {
                    man+= (goalCol - j);
                }
                else {
                    man += (j - goalCol);
                }
                
            }
        }
        return man;
    } // sum of Manhattan distances between blocks and goal
    
    public boolean isGoal() {
        return (this.hamming() == 0);
    } // is this blocks the goal blocks?
    
    public Board twin() {
        int [][] twin =  new int[dim][dim];
        for (int i = 0; i < dim; i++) {
            for (int j = 0 ;j < dim; j++) {
                twin[i][j] = this.blocks[i][j];
            }
        }
        
        Board twinB = new Board(twin);
        if (twin[0][0] != 0 && twin[0][1] != 0) 
            twinB.swap(0,0,0,1);
        else
            twinB.swap(1,0,1,1);
        
        return twinB;
        
    } // a blocks that is obtained by exchanging any pair of blocks
    
    public boolean equals(Object y) {
        if (y == this) return true;
        
        if (y == null || y.getClass() != this.getClass()) return false;
        
        if(((Board)y).dimension() != this.dim) return false;
        
        if (Arrays.deepEquals(this.blocks,((Board)y).blocks)) {
            return true;
        }
        else {
            return false;
        }
        
    } // does this blocks equal y?
    
    public Iterable<Board> neighbors() {
        // Find the blank spot
        int blankRow = -1, blankCol = -1;
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if (this.blocks[i][j] == 0) {
                    blankRow = i;
                    blankCol = j;
                    break;
                }
            }
        }
        ArrayList<Board> neighbors = new ArrayList<Board>();
        if (blankRow > 0) {
            Board neighbor = new Board(this.blocks);
            neighbor.swap(blankRow,blankCol,blankRow-1,blankCol);
            neighbors.add(neighbor);
        }
        if (blankRow < dim - 1) {
            Board neighbor = new Board(this.blocks);
            neighbor.swap(blankRow,blankCol,blankRow+1,blankCol);
            neighbors.add(neighbor);
        }
        if (blankCol > 0) {
            Board neighbor = new Board(this.blocks);
            neighbor.swap(blankRow,blankCol,blankRow,blankCol-1);
            neighbors.add(neighbor);
        }
        if (blankCol < dim - 1) {
            Board neighbor = new Board(this.blocks);
            neighbor.swap(blankRow,blankCol,blankRow,blankCol+1);
            neighbors.add(neighbor);    
        }
        return neighbors;    
    }
    
    private void swap (int i, int j, int k, int l) {
        int temp = this.blocks[i][j];
        this.blocks[i][j] = this.blocks[k][l];
        this.blocks[k][l] = temp;
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(dim).append('\n');
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++)
                sb.append(' ').append(blocks[i][j]).append(' ');
            sb.append('\n');
        }
        return sb.toString();
        
    } // string representation of this blocks (in the output format specified below)

    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                blocks[i][j] = in.readInt();
            }
        }
        
        Board initial = new Board(blocks);
        System.out.println(initial.toString());
        
        // Test neighbors
        for (Board b : initial.neighbors())
            System.out.println(b.toString());
    }
    
}
