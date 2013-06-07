package player;

import player.millitta.Helper;
import player.millitta.Generate.AbstractGenerator;
import player.millitta.Generate.Generator;
import player.millitta.Search.AlphaBetaPruning;

public class Millitta extends player.millitta.Millitta {

    public static void main(String[] args) {
        //long board = 5110530058485761L;
        long board = 2761269521531535360L; // ^ (1L << BIT_PLAYER);
        //board |= 1L << BIT_PHASE;

        //System.out.println("ok");
        //Evaluate eval = new Evaluate(board);
        //System.out.println("Bla " + eval.getClosedMills());

        Helper.printBoard(board);

        AbstractGenerator gen = Generator.get(board);
        long[] next = gen.getNextBoards();
        long bestBoard = next[0];

//        for( long b : next) {
//            if( b == MAGIC_NO_BOARD) break;
//            System.out.println(b);
//            Helper.printBoard(b);
//        }

        AlphaBetaPruning ab =  new AlphaBetaPruning(board, 20, 100);
        bestBoard = ab.getBestBoard();


        System.out.println("Now: " + board + " | Next: " + bestBoard);

        System.out.println("Next:");
        Helper.printBoard(bestBoard);
//
//        long bitDiff = (board ^ bestBoard) & BITS_MENS;
//        int pos = (int) (Math.log(bitDiff) / LOG2);
//
//        System.out.println("Pos: " + pos);
    }
}
