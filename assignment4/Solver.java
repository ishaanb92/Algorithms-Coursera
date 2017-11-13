import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;
import java.util.ArrayList;

public class Solver {
    
    private boolean isSolvable;
    private ArrayList<Board> solution;
    private int moves;
    
    private class Node implements Comparable<Node> {
        private Board board;
        private int moves;
        private Node pred;
        private int manDist;

        
        public Node(Board b,int moves,Node pred) {
            this.board = b;
            this.moves = moves;
            this.pred = pred;
            this.manDist = board.manhattan();
        }
        
        public int compareTo(Node that) {
            return ((this.manDist + this.moves) - (that.manDist + that.moves));
        }     
    }
    public Solver(Board initial) {
        MinPQ<Node> pq = new MinPQ<Node>();
        MinPQ<Node> pq2 = new MinPQ<Node>();
        pq.insert(new Node(initial,0,null));
        pq2.insert(new Node(initial.twin(),0,null));
        this.solution = new ArrayList<Board>();
        while(true) {
            
            Node temp = pq.delMin();
            // Add this to the solution path
            solution.add(temp.board);
            if (temp.board.isGoal()) {
                this.isSolvable = true;
                this.moves = temp.moves;
                break;
            }
            
            // If its not the solution, we continue looking by generating neighbors
            for (Board b : temp.board.neighbors()) {
                if (b != null || !b.equals(temp.pred)) {
                    pq.insert(new Node(b,temp.moves+1,temp));
                }
            }
            
            // Check solution for twin
            Node temp2 = pq2.delMin();
            if (temp2.board.isGoal()) {
                this.isSolvable = false;
                this.moves = -1;
                break;
            }
            
            for (Board b : temp2.board.neighbors()) {
                if (b != null || !b.equals(temp2.pred)) {
                    pq2.insert(new Node(b,temp2.moves+1,temp2));
                }
            }
        }
       
        
    } // find a solution to the initial board (using the A* algorithm)
    public boolean isSolvable() {
        return isSolvable;
    } // is the initial board solvable?
    public int moves() {
        return this.moves;
    } // min number of moves to solve initial board; -1 if unsolvable
    public Iterable<Board> solution() {
        return this.solution;
    } // sequence of boards in a shortest solution; null if unsolvable
    public static void main(String[] args) {
        
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
            blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);
        
        // solve the puzzle
        Solver solver = new Solver(initial);
        
        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    } // solve a slider puzzle (given below)
    
}
