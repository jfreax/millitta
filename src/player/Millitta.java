package player;

import player.millitta.Helper;
import player.millitta.Generate.AbstractGenerator;
import player.millitta.Generate.Generator;
import player.millitta.Search.AlphaBetaPruning;

public class Millitta extends player.millitta.Millitta {

    public static void main(String[] args) {
        long board = 5110530058485761L;
        //board |= 1L << BIT_PHASE;

        //System.out.println("ok");
        //Evaluate eval = new Evaluate(board);
        //System.out.println("Bla " + eval.getClosedMills());

        Helper.printBoard(board);

        //AbstractGenerator gen = Generator.get(board);
        //long[] next = gen.getNextBoards();

        AlphaBetaPruning ab =  new AlphaBetaPruning(board, 20, 100);
        long bestBoard = ab.getBestBoard();


        System.out.println("Now: " + board + " | Next: " + bestBoard);

        System.out.println("Nexts:");
        Helper.printBoard(bestBoard);
    }
}
