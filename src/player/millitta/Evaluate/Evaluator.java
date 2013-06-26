package player.millitta.Evaluate;


import player.millitta.Constants;
import player.millitta.Search.AlphaBetaPruning;

public class Evaluator implements Constants {
    private long board = -1L;

    public Evaluator(long board) {
        this.board = board;
    }

    public long getNextBoard() {

        AlphaBetaPruning ab = new AlphaBetaPruning(board, 8, 5000);
        long bestBoard = ab.getBestBoard();

        //System.out.println("Best board: " + bestBoard);


        return bestBoard;
    }
}
