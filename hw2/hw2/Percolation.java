package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int[][] grid;
    private WeightedQuickUnionUF uf;
    private int numberOfOpen;
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("Invalid length for grid");
        }
        grid = new int[N][N];
        uf = new WeightedQuickUnionUF(N * N);
        numberOfOpen = 0;
    }                // create N-by-N grid, with all sites initially blocked
    public void open(int row, int col) {
        if (row < 0 || col < 0 || row >= grid.length || col >= grid.length) {
            throw new IndexOutOfBoundsException();
        }
        if (grid[row][col] == 0) {
            numberOfOpen++;
        }
        grid[row][col] = 1;
        int l = grid.length;
        if (row > 0 && grid[row - 1][col] == 1) {
            uf.union(row * l + col, (row - 1) * l + col);
        }
        if (row < l - 1 && grid[row + 1][col] == 1) {
            uf.union(row * l + col, (row + 1) * l + col);
        }
        if (col > 0 && grid[row][col - 1] == 1) {
            uf.union(row * l + col, row * l + col - 1);
        }
        if (col < l - 1 && grid[row][col + 1] == 1) {
            uf.union(row * l + col, row * l + col + 1);
        }
    }      // open the site (row, col) if it is not open already
    public boolean isOpen(int row, int col) {
        if (row < 0 || col < 0 || row >= grid.length || col >= grid.length) {
            throw new IndexOutOfBoundsException();
        }
        return grid[row][col] == 1;
    } // is the site (row, col) open?
    public boolean isFull(int row, int col) {
        if (row < 0 || col < 0 || row >= grid.length || col >= grid.length) {
            throw new IndexOutOfBoundsException();
        }
        for (int i = 0; i < grid.length; i++) {
            if (grid[row][col] == 1 && uf.connected(row * grid.length + col, i)) {
                return true;
            }
        }
        return false;
    } // is the site (row, col) full?
    public int numberOfOpenSites() {
        return numberOfOpen;
    }          // number of open sites
    public boolean percolates() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (uf.connected(i, (grid.length - 1) * grid.length + j)) {
                    return true;
                }
            }
        }
        return false;
    }             // does the system percolate?
    public static void main(String[] args) {

    }  // use for unit testing (not required, but keep this here for the autograder)
}
