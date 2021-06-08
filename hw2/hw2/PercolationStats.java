package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private double[] samples;
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        pf = new PercolationFactory();
        samples = new double[T];
        Percolation p = pf.make(N);
        for (int i = 0; i < T; i++) {
            while (!p.percolates()) {
                int random = StdRandom.uniform(N * N);
                int row = random / N;
                int col = random - N * row;
                p.open(row, col);
            }
            samples[i] = p.numberOfOpenSites() / (N * N);
        }
    }  // perform T independent experiments on an N-by-N grid
    public double mean() {
        return StdStats.mean(samples);
    }                                          // sample mean of percolation threshold
    public double stddev() {
        return StdStats.stddev(samples);
    }                                        // sample standard deviation of percolation threshold
    public double confidenceLow() {
        double res = StdStats.mean(samples) - 1.96 * StdStats.stddev(samples) / Math.sqrt(samples.length);
        return res;
    }                                 // low endpoint of 95% confidence interval
    public double confidenceHigh() {
        double res = StdStats.mean(samples) + 1.96 * StdStats.stddev(samples) / Math.sqrt(samples.length);
        return res;
    }                                // high endpoint of 95% confidence interval
}
