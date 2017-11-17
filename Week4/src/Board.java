/**
 * Created by anshi on 3/11/2017.
 */

import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.ArrayList;

public final class Board {
    private final int[][] blocks;
    private final int ndim;

    public Board(int[][] blocks){
        if (blocks == null) {
            throw new java.lang.NullPointerException();
        }
        this.ndim = blocks.length;
        this.blocks = new int[ndim][ndim];
        for(int i = 0; i < ndim; i++){
            for(int j = 0; j < ndim; j++){
                this.blocks[i][j] = blocks[i][j];
            }
        }
    }           // construct a board from an n-by-n array of blocks
    // (where blocks[i][j] = block in row i, column j)

    public int dimension(){
        return this.ndim;
    }                 // board dimension n

    public int hamming(){
        int curValue;
        int hammingPrior = 0;
        for(int i = 0; i < ndim; i++){
            for(int j = 0; j < ndim-1; j++){
                curValue = i * ndim + j + 1;
                if (blocks[i][j] != curValue && blocks[i][j] != 0) hammingPrior++;
            }
        }
        if(blocks[ndim-1][ndim-1] != 0) hammingPrior++;
        return hammingPrior;
    }                   // number of blocks out of place

    public int manhattan(){
        int curNum, row, col;
        int manhattanPrior = 0;
        for(int i = 0; i < ndim; i++){
            for(int j = 0; j < ndim; j++){
                curNum = this.blocks[i][j];
                if(curNum != 0) {
                    row = (curNum-1) / ndim;
                    col = (curNum-1) % ndim;
                    manhattanPrior = manhattanPrior + Math.abs(row - i) + Math.abs(col - j);
                }
            }
        }
        return manhattanPrior;
    }                 // sum of Manhattan distances between blocks and goal

    public boolean isGoal(){
        int curValue;
        for(int i = 0; i < ndim; i++){
            for(int j = 0; j < ndim-1; j++){
                curValue = i * ndim + j + 1;
                if (blocks[i][j] != curValue) return false;
            }
        }
        if (blocks[ndim-1][ndim-1]!=0)
            return false;
        return true;
    }                // is this board the goal board?

    public Board twin(){
        int sourceRow,sourceCol,targetRow,targetCol;
        do {
            sourceRow = StdRandom.uniform(ndim);
            sourceCol = StdRandom.uniform(ndim);
            targetRow = StdRandom.uniform(ndim);
            targetCol = StdRandom.uniform(ndim);
        } while(sourceRow == targetRow && sourceCol == targetCol || blocks[sourceRow][sourceCol]==0 || blocks[targetRow][targetCol]==0);
        int[][] clonedBlocks = new int[ndim][ndim];
        for(int i = 0; i < ndim; i++)
            for(int j = 0; j < ndim; j++)
                clonedBlocks[i][j] = blocks[i][j];
        int temp = clonedBlocks[targetRow][targetCol];
        clonedBlocks[targetRow][targetCol] = clonedBlocks[sourceRow][sourceCol];
        clonedBlocks[sourceRow][sourceCol] = temp;
        return new Board(clonedBlocks);
    }                    // a board that is obtained by exchanging any pair of blocks

    public boolean equals(Object y){
        if(y == this) return true;
        if(y == null) return false;
        if(y.getClass() != this.getClass()) return false;
        Board that = (Board) y;
        if(this.ndim != that.ndim)
            return false;
        else{
            for(int i = 0; i < ndim; i++)
                for(int j = 0; j < ndim; j++){
                    if(this.blocks[i][j] != that.blocks[i][j])
                        return false;
                }
        }
        return true;
    }        // does this board equal y?

    public Iterable<Board> neighbors(){
        return new Iterable<Board>() {
            @Override
            public Iterator<Board> iterator() {
                return new boardIterator();
            }
        };
    }

    private class boardIterator implements Iterator<Board>{

        private ArrayList<Board> neighbourBoards;
        private int curIndex;

        public boardIterator(){
            int cur0Col = 0;
            int cur0Row = 0;
            curIndex = 0;
            neighbourBoards = new ArrayList<>();
            for(int i = 0; i < ndim; i++)
                for(int j = 0; j < ndim; j++){
                    if (blocks[i][j] == 0) {
                        cur0Row = i;
                        cur0Col = j;
                        break;
                    }
            }
            if(cur0Row > 0) neighbourBoards.add(exchBlock(cur0Row,cur0Col, cur0Row-1, cur0Col));
            if(cur0Row < ndim-1) neighbourBoards.add(exchBlock(cur0Row,cur0Col, cur0Row+1, cur0Col));
            if(cur0Col > 0) neighbourBoards.add(exchBlock(cur0Row,cur0Col, cur0Row, cur0Col - 1));
            if(cur0Col < ndim-1) neighbourBoards.add(exchBlock(cur0Row,cur0Col, cur0Row, cur0Col+1));
        }

        private Board exchBlock(int si, int sj, int ti, int tj){
            int[][] curBlocks = new int[ndim][ndim];
            for(int i = 0; i < ndim; i++)
                for(int j = 0; j < ndim; j++)
                    curBlocks[i][j] = blocks[i][j];
            int tempBlock = curBlocks[ti][tj];
            curBlocks[ti][tj] = curBlocks[si][sj];
            curBlocks[si][sj] = tempBlock;
            return new Board(curBlocks);
        }

        public boolean hasNext(){
            if(curIndex < neighbourBoards.size()) return true;
            return false;
        }
        public Board next(){
            return neighbourBoards.get(curIndex++);
        }
    }

    public String toString(){
        String printStr = "" + ndim + "\n";
        for(int i = 0; i < ndim; i++) {
            for (int j = 0; j < ndim; j++) {
                printStr = printStr + blocks[i][j] + " ";
            }
            printStr = printStr + "\n";
        }
        return printStr;
    }               // string representation of this board (in the output format specified below)

    public static void main(String[] args){
        int[][] testBlocks = {{1,2,3},{4,0,6},{8,5,7}};
        Board testBoard = new Board(testBlocks);
        System.out.println(testBoard);
//        System.out.println(testBoard.isGoal());
//        System.out.println(testBoard.hamming());
//        System.out.println(testBoard.manhattan());
//        for(Board i : testBoard.neighbors())
//            System.out.println(i);
        for(int i = 0 ; i < 10; i++)
            System.out.println(testBoard.twin());
    } // unit tests (not graded)
}