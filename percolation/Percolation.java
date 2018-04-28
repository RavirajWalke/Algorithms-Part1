import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation
{
  private final int n;
  private final boolean [][]connection;
  private final WeightedQuickUnionUF grid;
  private int openSites;
  public Percolation(int n)                // create n-by-n grid, with all sites blocked
  {
    if (n <= 0)
    {
      throw new IllegalArgumentException();
    }
    connection = new boolean[n][n];
    openSites = 0;
    this.n = n;
    grid = new WeightedQuickUnionUF(n*n+2);
  }

   public boolean isOpen(int row, int col)            // is site (row, col) open?
   {
     if (row < 1 || row > n || col < 1 || col > n)
       throw new IllegalArgumentException();
     return connection[row-1][col-1];
   }

   private int map(int row, int col)                  // convert two dim array index to one dim arr index
   {
     return (row-1)*n+col;
   }

   public void open(int row, int col)    // open site (row, col) if it is not open already
   {
     if (row < 1 || row > n || col < 1 || col > n)
       throw new IllegalArgumentException();
     if (isOpen(row, col))
     {
       return;
     }
     connection[row-1][col-1] = true;
     if (col-1 > 0 && isOpen(row, col-1)) {
       grid.union(map(row, col-1), map(row, col));
     }
     if (col+1 <= n && isOpen(row, col+1)) {
       grid.union(map(row, col+1), map(row, col));
     }
     if (row-1 > 0 && isOpen(row-1, col)) {
       grid.union(map(row-1, col), map(row, col));
     }
     if (row+1 <= n && isOpen(row+1, col)) {
       grid.union(map(row+1, col), map(row, col));
     }
     if (row == 1) {
       grid.union(0, (row-1)*n+col);
     }
     if (row == n) {
       grid.union(n*n+1, (row-1)*n+col);
     }
     openSites++;
   }

   public boolean isFull(int row, int col)  // is site (row, col) full?
   {
     if (row < 1 || row > n || col < 1 || col > n)
       throw new IllegalArgumentException();
     return grid.connected(0, map(row, col));
   }

   public int numberOfOpenSites()       // number of open sites
   {
     return openSites;
   }

   public boolean percolates()              // does the system percolate?
   {
     return grid.connected(0, n * n + 1);
   }
}
