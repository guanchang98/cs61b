package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private double[] samples;
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        samples = new double[T];
        for (int i = 0; i < T; i++) {
            Percolation p = pf.make(N);
            while (!p.percolates()) {
                int random = StdRandom.uniform(N * N);
                int row = random / N;
                int col = random - N * row;
                p.open(row, col);
            }
            double sample = p.numberOfOpenSites() / (double) (N * N);
            //System.out.println(sample);
            samples[i] = sample;
        }
    }  // perform T independent experiments on an N-by-N grid
    public double mean() {
        return StdStats.mean(samples);
    }                                          // sample mean of percolation threshold
    public double stddev() {
        return StdStats.stddev(samples);
    }                                        // sample standard deviation of percolation threshold
    public double confidenceLow() {
        double res = StdStats.mean(samples) - 1.96 * StdStats.stddev(samples)
                / Math.sqrt(samples.length);
        return res;
    }                                 // low endpoint of 95% confidence interval
    public double confidenceHigh() {
        double res = StdStats.mean(samples) + 1.96 * StdStats.stddev(samples)
                / Math.sqrt(samples.length);
        return res;
    }                                // high endpoint of 95% confidence interval
    public static void main(String[] args) {
        PercolationFactory pf = new PercolationFactory();
        PercolationStats ps = new PercolationStats(4, 50, pf);
        System.out.println(ps.mean());
        System.out.println(ps.stddev());
        System.out.println(ps.confidenceHigh());
        System.out.println(ps.confidenceLow());
    }
}
