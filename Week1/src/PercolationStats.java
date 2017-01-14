import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import java.lang.Math;

/**
 * Created by anshi on 1/11/2017.
 */
public class PercolationStats {
    private int trials;
    private double[] results;


    public PercolationStats(int n, int trials){
        if (n <= 0 || trials <=0) throw new java.lang.IllegalArgumentException();
        this.trials = trials;
        this.results = new double[trials];

        for(int i =0;i<trials;i++) {
            int row, col;
            Percolation sample = new Percolation(n);

            while (!sample.percolates()) {
                row = StdRandom.uniform(1,n+1);
                col = StdRandom.uniform(1,n+1);
                sample.open(row, col);
            }

            results[i] = sample.numberOfOpenSites() * 1.0/(n*n);
        }
    }

    public double mean(){
        return StdStats.mean(results);
    }

    public double stddev(){
        return StdStats.stddev(results);
    }

    public double confidenceLo(){
        return mean() - 1.96 * stddev() / Math.sqrt(trials);
    }

    public double confidenceHi(){
        return mean() - 1.96 * stddev() / Math.sqrt(trials);
    }

    public static void main(String[] args){
        int n,trials;
        if (args.length ==2) {
            n = Integer.parseInt(args[0]);
            trials = Integer.parseInt(args[1]);
        }else {
            n = 200;
            trials = 100;
        }
        PercolationStats ps=new PercolationStats(n,trials);
        System.out.format("%-24s = %f\n","mean", ps.mean());
        System.out.format("%-24s = %f\n","stddev", ps.stddev());
        System.out.format("%-24s = %f, %f\n","95% confidence interval",ps.confidenceLo(),ps.confidenceHi());
    }

}
