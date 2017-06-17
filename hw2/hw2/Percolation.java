package hw2;                       

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean [] [] sites;
    private WeightedQuickUnionUF connection;
    private int openSize;

    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        sites = new boolean[N][N];
        connection = new WeightedQuickUnionUF(N * N + 2);
    }

    public void open(int row, int col) {
        if (row > sites.length-1 || col > sites.length-1 ) {
            throw new IndexOutOfBoundsException();
        } else {
            sites[row][col] = true;
            openSize += 1;
            connectOpenSites(row, col);
        }
        if (row == 0) {
            connection.union(sites.length * sites.length +1, xyTo1D(row, col));
        } else if (row == sites.length -1 ) {
            connection.union(sites.length * sites.length, xyTo1D(row, col));
        }
    }

    private void connectOpenSites(int row, int col) {
        if (row > 0 && isOpen(row - 1, col)) {
            connection.union(xyTo1D(row, col), xyTo1D(row - 1, col));
        }
        if (row < sites.length -1 && isOpen(row + 1, col)) {
            connection.union(xyTo1D(row, col), xyTo1D(row + 1, col));
        }
        if (col > 0 && isOpen(row, col - 1)) {
            connection.union(xyTo1D(row, col), xyTo1D(row, col - 1));
        }
        if (col < sites.length-1 && isOpen(row, col + 1)) {
            connection.union(xyTo1D(row, col), xyTo1D(row, col + 1));
        }
    }


    public boolean isOpen(int row, int col) {
        if (row > sites.length-1 || col > sites.length-1 ) {
            throw new IndexOutOfBoundsException();
        }
        return sites[row][col];
    }

    public boolean isFull(int row, int col) {
        return connection.connected(sites.length * sites.length +1, xyTo1D(row, col));
    }

    public int numberOfOpenSites() {
        return openSize;
    }

    public boolean percolates() {
        return connection.connected(sites.length * sites.length +1, sites.length * sites.length);
    }

    private int xyTo1D(int r, int c) {
        return r * sites.length + c;
    }

    public static void main(String[] args) {}

}                       
