package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    /*blocked : 0; open(not full) : 4; blocked but connected up : 2;
      blocked but connected down : 1; open and connected up(full) : 6;
      open and connected down : 5; percolates : 7 */
    private int[][] grid;
    private WeightedQuickUnionUF uf;
    private int numberOfOpen;
    private int up;
    private int down;

    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("Invalid length for grid");
        }
        grid = new int[N][N];
        uf = new WeightedQuickUnionUF(N * N + 2);
        numberOfOpen = 0;
        for (int i = 0; i < N; i++) {
            uf.union(N * N, i);
            uf.union(N * N + 1, N * (N - 1) + i);
            grid[0][i] += 2;
            grid[N - 1][i] += 1;
        }
        up = grid[0][0];
        down = grid[N - 1][0];
    }                // create N-by-N grid, with all sites initially blocked
    private void updateRoot(int row, int col) {
        int l = grid.length;
        int root = uf.find(row * l + col);
        if (root == l * l) {
            up = up | grid[row][col];
        } else if (root == l * l + 1) {
            down = down | grid[row][col];
        } else {
            int rootRow = root / l;
            int rootCol = root - rootRow * l;
            grid[rootRow][rootCol] = grid[row][col] | grid[rootRow][rootCol];
        }
    }
    public void open(int row, int col) {
        if (row < 0 || col < 0 || row >= grid.length || col >= grid.length) {
            throw new IndexOutOfBoundsException();
        }
        if (grid[row][col] >= 4) {
            return;
        }
        numberOfOpen++;
        grid[row][col] += 4;
        int l = grid.length;
        if (row > 0 && grid[row - 1][col] >= 4) {
            if (uf.find(row * l + col) != uf.find((row - 1) * l + col)) {
                uf.union(row * l + col, (row - 1) * l + col);
            }
            updateRoot(row, col);
        }
        if (row < l - 1 && grid[row + 1][col] >= 4) {
            if (uf.find(row * l + col) != uf.find((row + 1) * l + col)) {
                uf.union(row * l + col, (row + 1) * l + col);
            }
            updateRoot(row, col);
        }
        if (col > 0 && grid[row][col - 1] >= 4) {
            if (uf.find(row * l + col) != uf.find(row * l + col - 1)) {
                uf.union(row * l + col, row * l + col - 1);
            }
            updateRoot(row, col);
        }
        if (col < l - 1 && grid[row][col + 1] >= 4) {
            if (uf.find(row * l + col) != uf.find(row * l + col + 1)) {
                uf.union(row * l + col, row * l + col + 1);
            }
            updateRoot(row, col);
        }
    }      // open the site (row, col) if it is not open already
    public boolean isOpen(int row, int col) {
        if (row < 0 || col < 0 || row >= grid.length || col >= grid.length) {
            throw new IndexOutOfBoundsException();
        }
        return grid[row][col] >= 4;
    } // is the site (row, col) open?
    public boolean isFull(int row, int col) {
        if (row < 0 || col < 0 || row >= grid.length || col >= grid.length) {
            throw new IndexOutOfBoundsException();
        }
        int N = grid.length;
        return grid[row][col] >= 4 && uf.connected(row * N + col, N * N);
    } // is the site (row, col) full?
    public int numberOfOpenSites() {
        return numberOfOpen;
    }          // number of open sites
    public boolean percolates() {
        int N = grid.length;
        return uf.connected(N * N, N * N + 1);
    }             // does the system percolate?
    public static void main(String[] args) {
    }  // use for unit testing (not required, but keep this here for the autograder)
}
