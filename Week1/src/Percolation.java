import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int bigN;
    private int n;
    private WeightedQuickUnionUF uf;
    private int[] trackOpen;
    private int openSitesNumber;
    private int top;
    private int bottom;

    public Percolation(int n)  // create n-by-n grid, with all sites blocked
    {
        this.n = n;
        this.bigN = n * n;
        this.uf = new WeightedQuickUnionUF(bigN +2);
        this.top = bigN;
        this.bottom = bigN + 1;
        // initialize trackOpen;
        trackOpen = new int[bigN];
        for (int i = 0; i < bigN; i++) {
            trackOpen[i] = 0;
        }
        openSitesNumber=0;
    }

    public static void main(String[] args)   // test client (optional)
    {

        for (int i = 0; i < 1000; i++) {

            int row, col;
            Percolation sample = new Percolation(20);

            while (!sample.percolates()) {
                row = StdRandom.uniform(1,21);
                col = StdRandom.uniform(1,21);
                sample.open(row, col);
            }
            System.out.println("System percolates after " + sample.numberOfOpenSites() + " sites are opened;");
        }
    }

    public void open(int row, int col)    // open site (row, col) if it is not open already
    {
        int surIndex;
        int index = getIndex(row,col);
        if (trackOpen[index] == 1) return;
        trackOpen[index]=1;
        openSitesNumber++;
        int[] surroundSites = {getTopSiteIndex(row, col),getRightSiteIndex(row, col),getBottomSiteIndex(row, col),getLeftSiteIndex(row, col)};
        for(int i=0;i<4;i++){
            if(surroundSites[i] >=0){
                surIndex = surroundSites[i];
                if(trackOpen[surIndex] == 1){
                    uf.union(index,surIndex);
                }
            }
        }
        if (row == 1) uf.union(index,top);
        if (row == n) uf.union(index,bottom);
    }

    public boolean isOpen(int row, int col)  // is site (row, col) open?
    {
        int index = getIndex(row,col);
        return trackOpen[index] == 1;
    }

    public boolean isFull(int row, int col)  // is site (row, col) full?
    {
        int index = getIndex(row,col);
        return uf.connected(index, top);
    }

    public int numberOfOpenSites()  // number of open sites
    {
        return openSitesNumber;
    }

    public boolean percolates()  // does the system percolate?
    {
        return uf.connected(top,bottom);
    }

    private int getTopSiteIndex(int row, int col){
        row--;
        if (row>=1)
            return getIndex(row,col);
        else
            return -1;
    }

    private int getBottomSiteIndex(int row, int col){
        row++;
        if (row<=this.n)
            return getIndex(row,col);
        else
            return -1;
    }

    private int getLeftSiteIndex(int row, int col){
        col--;
        if (col>=1)
            return getIndex(row,col);
        else
            return -1;
    }

    private int getRightSiteIndex(int row, int col){
        col++;
        if (col<=this.n)
            return getIndex(row,col);
        else
            return -1;
    }


    private int getIndex(int row,int col){
        int index;
        if (row == 1)
            index = col-1;
        else
            index = (row-1) * this.n + col - 1;

        if ((index >= bigN) || (row > this.n || row < 1) || (col > this.n || col <1))
            throw new java.lang.IndexOutOfBoundsException();

        return index;
    }

}