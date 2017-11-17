import edu.princeton.cs.algs4.MinPQ;
import java.util.ArrayList;

import edu.princeton.cs.algs4.*;

public final class Solver {

    private final ArrayList<Board> solution;
    private final boolean canSolve;
    private int totalMoves;

    private class SolverBoard implements Comparable<SolverBoard> {
        public SolverBoard parentBoard;
        public int pior;
        public Board curBoard;
        public int curMove;

        public SolverBoard(Board curBoard, int curMove){
            this.parentBoard = null;
            this.curBoard = curBoard;
            this.curMove = curMove;
            this.pior = curBoard.manhattan() + curMove;
        }

        public int compareTo(SolverBoard another){
            return this.pior - another.pior;
        }

    }

    public Solver(Board initial){
        if (initial == null) {
            throw new java.lang.NullPointerException();
        }
        MinPQ<SolverBoard> pq = new MinPQ<>();
        MinPQ<SolverBoard> pqTwin = new MinPQ<>();
        pqTwin.insert(new SolverBoard(initial.twin(),0));
        pq.insert(new SolverBoard(initial,0));
        this.totalMoves = 0;
        this.solution = new ArrayList<>();
        SolverBoard curBoard,lastBoard,twinCurBoard,twinLastBoard;
        curBoard = pq.delMin();
        twinCurBoard = pqTwin.delMin();
        lastBoard = null;
        twinLastBoard = null;
        while(!curBoard.curBoard.isGoal() || !twinCurBoard.curBoard.isGoal()){
            if(lastBoard != null){
                for(Board neighbour:curBoard.curBoard.neighbors()){
                    if(!neighbour.equals(lastBoard)) {
                        SolverBoard curSB = new SolverBoard(neighbour,totalMoves);
                        curSB.parentBoard = lastBoard;
                        pq.insert(curSB);
                    }
                }
                for(Board neighbour: twinCurBoard.curBoard.neighbors()){
                    if(!neighbour.equals(twinLastBoard)) {
                        SolverBoard curSB = new SolverBoard(neighbour,totalMoves);
                        curSB.parentBoard = twinLastBoard;
                        pqTwin.insert(curSB);
                    }
                }
            }else{
                for(Board neighbour:curBoard.curBoard.neighbors()) {
                    SolverBoard curSB = new SolverBoard(neighbour,totalMoves);
                    curSB.parentBoard = lastBoard;
                    pq.insert(curSB);
                }
                for(Board neighbour: twinCurBoard.curBoard.neighbors()) {
                    SolverBoard curSB = new SolverBoard(neighbour,totalMoves);
                    curSB.parentBoard = twinLastBoard;
                    pqTwin.insert(curSB);
                }
            }
            lastBoard = curBoard;
            twinLastBoard = twinCurBoard;
            curBoard = pq.delMin();
            twinCurBoard = pqTwin.delMin();
            this.totalMoves++;
        }
        if (curBoard.curBoard.isGoal()){
            this.canSolve = true;
        }else{
            this.canSolve = false;
            this.totalMoves = -1;
        }

    }           // find a solution to the initial board (using the A* algorithm)
    public boolean isSolvable(){
        return this.canSolve;
    }           // is the initial board solvable?
    public int moves(){
        return this.totalMoves;
    }                     // min number of moves to solve initial board; -1 if unsolvable
    public Iterable<Board> solution(){
        if (this.canSolve)
            return this.solution;
        else
            return null;
    }      // sequence of boards in a shortest solution; null if unsolvable
    public static void main(String[] args){

        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    } // solve a slider puzzle (given below)
}