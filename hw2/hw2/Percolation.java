package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    /*blocked : 0; open(not full) : 4; blocked but connected up : 2;
      blocked but connected down : 1; open and connected up(full) : 6;
      open and connected down : 5; percolates : 7 */
    private byte[] state;
    private WeightedQuickUnionUF uf;
    private int numberOfOpen;
    private int n; //n * n grid
    private boolean percolate = false;

    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("Invalid length for grid");
        }
        n = N;
        state = new byte[n * n];
        uf = new WeightedQuickUnionUF(n * n);
        numberOfOpen = 0;
    }                // create N-by-N grid, with all sites initially blocked

    public void open(int row, int col) {
        if (row < 0 || col < 0 || row >= n || col >= n) {
            throw new IndexOutOfBoundsException();
        }
        if (state[row * n + col] >= 4) {
            return;
        }
        numberOfOpen++;
        if (row == 0) {
            state[row * n + col] = (byte) (state[row * n + col] | 2);
        }
        if (row == n - 1) {
            state[row * n + col] = (byte) (state[row * n + col] | 1);
        }
        state[row * n + col] = (byte) (4 | state[row * n + col]);
        if (row > 0 && state[(row - 1) * n + col] >= 4) {
            int adjacentRootIdx = uf.find((row - 1) * n + col);
            state[row * n + col] = (byte) (state[row * n + col] | state[adjacentRootIdx]);
            uf.union(row * n + col, (row - 1) * n + col);
        }
        if (row < n - 1 && state[(row + 1) * n + col] >= 4) {
            int adjacentRootIdx = uf.find((row + 1) * n + col);
            state[row * n + col] = (byte) (state[row * n + col] | state[adjacentRootIdx]);
            uf.union(row * n + col, (row + 1) * n + col);
        }
        if (col > 0 && state[row * n + col - 1] >= 4) {
            int adjacentRootIdx = uf.find(row * n + col - 1);
            state[row * n + col] = (byte) (state[row * n + col] | state[adjacentRootIdx]);
            uf.union(row * n + col, row * n + col - 1);
        }
        if (col < n - 1 && state[row * n + col + 1] >= 4) {
            int adjacentRootIdx = uf.find(row * n + col + 1);
            state[row * n + col] = (byte) (state[row * n + col] | state[adjacentRootIdx]);
            uf.union(row * n + col, row * n + col + 1);
        }
        int rootIdx = uf.find(row * n + col);
        state[rootIdx] = (byte) (state[rootIdx] | state[row * n + col]);
        if (7 == (byte) (state[rootIdx] & 7)) {
            percolate = true;
        }
    }      // open the site (row, col) if it is not open already
    public boolean isOpen(int row, int col) {
        if (row < 0 || col < 0 || row >= n || col >= n) {
            throw new IndexOutOfBoundsException();
        }
        return state[row * n + col] >= 4;
    } // is the site (row, col) open?
    public boolean isFull(int row, int col) {
        if (row < 0 || col < 0 || row >= n || col >= n) {
            throw new IndexOutOfBoundsException();
        }
        int rootIdx = uf.find(row * n + col); // root index of given site (row, col)
        return isOpen(row, col) && 2 == (state[rootIdx] & 2);
    } // is the site (row, col) full?
    public int numberOfOpenSites() {
        return numberOfOpen;
    }          // number of open sites
    public boolean percolates() {
        return percolate;
    }             // does the system percolate?
    public static void main(String[] args) {
    }  // use for unit testing (not required, but keep this here for the autograder)
}
