package player;

import player.millitta.Evaluate.Evaluate;
import player.millitta.Helper;
import player.millitta.Generate.AbstractGenerator;
import player.millitta.Generate.Generator;
import player.millitta.Search.AlphaBetaPruning;

public class Millitta extends player.millitta.Millitta {

    public static void main(String[] args) {
        //long board = 5110530058485761L;
        //long board = 2148921259453317200L; // ^ (1L << BIT_PLAYER);
        long board = 1536422364282103296L;
        board = 1514185841121961473L; // bloed
        board = 2148885525325414403L;
        //Helper.printBoard(board);
        //board = 1909851697447105L;
        //long board = 1820355849000880256L; // gut
        //board |= 1L << BIT_PHASE;

        //System.out.println("ok");
        //Evaluate eval = new Evaluate(board);
        //System.out.println("Bla " + eval.getClosedMills());
        Helper.printBoard(board);
        System.out.println((new Evaluate(board)).getFitness());
        System.out.println("#########################\n");

        //Helper.printBoard(1820355957494120448L);


        //AbstractGenerator gen = Generator.get(board);
        //long[] next = gen.getNextBoards();
        //long bestBoard = next[0];
/*
        long bestBoard = 2126684736293175408L; // 1 Mill
        //long bestBoard = 2126684736293175376L; // 1 offene
        System.out.println(bestBoard);
        System.out.println( "Player1.1: " + ((bestBoard & (1L << BIT_PLAYER )) != 0L));

        Helper.printBoard(bestBoard);
        System.out.println("#########################\n");
        System.out.println((new Evaluate(bestBoard)).getFitness());

        //AlphaBetaPruning ab =  new AlphaBetaPruning(board, 1, 1000);
        //bestBoard = ab.getBestBoard();

        AbstractGenerator gen = Generator.get(bestBoard);
        long[] next = gen.getNextBoards();
        bestBoard = next[0];

        System.out.println(bestBoard);
        System.out.println( "Player2: " + ((bestBoard & (1L << BIT_PLAYER )) != 0L));

        Helper.printBoard(bestBoard);*/

//        AbstractGenerator gen = Generator.get(board);
//        long[] next = gen.getNextBoards();
//        for( long b : next) {
//            if( b == MAGIC_NO_BOARD) break;
//            System.out.println("\n"+b+"\n"+(new Evaluate(b)).getFitness());
//
//            Helper.printBoard(b);
//        }
//
        AlphaBetaPruning ab =  new AlphaBetaPruning(board, 8, 5000);
        long bestBoard = ab.getBestBoard();

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
