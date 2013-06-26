package player.millitta.Evaluate;


import player.millitta.Board;
import player.millitta.Constants;
import player.millitta.Generate.*;
import player.millitta.Search.AlphaBetaPruning;
import player.millitta.Search.YouLostException;

public class Evaluator implements Constants {
    private long board = -1L;

    public Evaluator(long board) {
        this.board = board;
    }

    public long getNextBoard() throws YouLostException {

        /*
            Ausnahme wenn Zwickmuehle vorhanden ist.
            Diese wird _immer_ ausgenutzt :)
         */
        Evaluate eval = new Evaluate(board);
        if( eval.getDoubleMills() > 0 ) {
            AbstractGenerator nextBoardsGenerator = Generator.get(board);
            long[] nextBoards = nextBoardsGenerator.getNextBoards();

            for (long nextBoard : nextBoards) {
                if( nextBoard == MAGIC_NO_BOARD ) {
                    break;
                }

                Evaluate evalNext = new Evaluate(nextBoard);

                if( evalNext.getDoubleMills() > 0 && Board.isMyMen(nextBoard, eval.oneDoubleMillHole) ) {
                    return nextBoard;
                }

            }
        }


        AlphaBetaPruning ab = new AlphaBetaPruning(board, 32, Constants.TIME_PER_MOVE);
        long bestBoard = ab.getBestBoard();

        return bestBoard;
    }
}
