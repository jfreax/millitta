package player.millitta.Evaluate;


import player.millitta.Generate.AbstractGenerator;
import player.millitta.Generate.Generator;

public class Evaluator {
    private long board = -1L;

    public Evaluator(long board) {
        this.board = board;
    }

    public long getNextBoard() {

        AbstractGenerator nextBoards = Generator.get(board);
        long nextBoard = nextBoards.getNextBoards()[0];

        return nextBoard;
    }
}
