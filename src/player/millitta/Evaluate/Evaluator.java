package player.millitta.Evaluate;


import player.millitta.Constants;
import player.millitta.Generate.AbstractGenerator;
import player.millitta.Generate.Generator;

public class Evaluator implements Constants {
    private long board = -1L;

    public Evaluator(long board) {
        this.board = board;
    }

    public long getNextBoard() {

        AbstractGenerator nextBoards = Generator.get(board);

        double best = -1.f;
        long bestBoard = 0L;

        for( long nextBoard : nextBoards.getNextBoards() ) {
            if( nextBoard == MAGIC_NO_BOARD )
                break;

            Evaluate eval = new Evaluate(nextBoard);
            double score = eval.getFitness();

            if( score > best ) {
                best = score;
                bestBoard = nextBoard;
            }
        }

        System.out.println("Best board: " + bestBoard + " | Score: " + best);


        return bestBoard;
    }
}
