package player;

import player.millitta.Board;
import player.millitta.Evaluate;
import player.millitta.Generator;

public class Millitta extends player.millitta.Millitta {

    public static void main(String [ ] args)
    {
        long board = 5348025077646592L;
        board |= 1L << BIT_PHASE;

        System.out.println("ok");
        Evaluate eval = new Evaluate(board);
        System.out.println("Bla " + eval.getClosedMills());

        Board.printBoard(board);

        Generator gen = new Generator(board);
        long[] next = gen.getNextBoards();

        System.out.println("Now: " + board + " | Next: " + next[0]);

        System.out.println("Nexts:");
        Board.printBoard(next[0]);

        for( int i = 0; true; i++ ) {
            if ( next[i] == -1L ) {
                break;
            }
            //Board.printBoard(next[i]);
        }
    }
}
