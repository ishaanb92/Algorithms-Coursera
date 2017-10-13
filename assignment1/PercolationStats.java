import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;



public class PercolationStats {
    private int n;
    private int trials;
    private double [] thresholds;
    private double mean;
    private double stddev;
    
    public PercolationStats(int n, int trials) {
        if (n < 0)
            throw new IllegalArgumentException("Grid size is negative");
        if (trials <= 0)
            throw new IllegalArgumentException("Trials is 0 or negative");
        this.n = n;
        this.trials = trials;
        this.thresholds = new double[this.trials];
        
        // Perform the trial
        for(int t = 0; t < this.trials; t++) {
            Percolation perc = new Percolation(this.n);
            while(!perc.percolates()) {
                // Choose a random row,col
                int i = StdRandom.uniform(this.n) + 1;
                int j = StdRandom.uniform(this.n) + 1;
                if (!perc.isOpen(i,j)) {
                    perc.open(i,j);
                }
            }
            int openSites = perc.numberOfOpenSites();
            double frac = (double)openSites/(double)(this.n*this.n);
            this.thresholds[t] = frac;
        }
        this.mean = StdStats.mean(this.thresholds);
        this.stddev = StdStats.stddev(this.thresholds);
    } // perform trials independent experiments on an n-by-n grid
    
    public double mean() {

        return this.mean;
    } // sample mean of percolation threshold
    
    public double stddev() {
        return this.stddev;
    } // sample standard deviation of percolation threshold
    
    public double confidenceLo() {
        return (this.mean - (1.96*this.stddev/Math.sqrt(this.trials)));
    } // low  endpoint of 95% confidence interval
    
    public double confidenceHi() {
        return (this.mean + (1.96*this.stddev/Math.sqrt(this.trials)));
    } // high endpoint of 95% confidence interval

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(n,trials);
        System.out.println("Mean = " + stats.mean());
        System.out.println("Std dev = " + stats.stddev());
        System.out.println("95% confidence interval = [" + stats.confidenceLo() + " , "+ stats.confidenceHi() + " ]");
    } // test client (described below)

}