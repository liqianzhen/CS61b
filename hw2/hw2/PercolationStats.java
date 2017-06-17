package hw2;

import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {
    private Percolation Monte;
    private int [] times;
    private int size;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T) {
        times = new int[T];
        size = N;

        for (int j= 0; j < T; j++) {
            Monte = new Percolation(N);

            while (!Monte.percolates()) {
                times[j] += 1;
                int row = StdRandom.uniform(0, N);
                int col = StdRandom.uniform(0, N);
                Monte.open(row, col);
            }
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        int total = 0;
        for (int i: times) {
            total += i;
        }
        return total/times.length;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        double std = 0;
        for (int i : times) {
            std +=(i - mean())*(i - mean());
        }
        return std/(times.length - 1);
    }

    // low  endpoint of 95% confidence interval
    //public double confidenceLow()

    // high endpoint of 95% confidence interval
   //public double confidenceHigh()


}                       
