package player;


import player.millitta.Board;
import player.millitta.EA.Individual;
import player.millitta.EA.Population;
import player.millitta.Evaluate.Evaluate;
import player.millitta.Generate.AbstractGenerator;
import player.millitta.Generate.Generator;
import player.millitta.Helper;
import player.millitta.Search.YouLostException;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

public class Millitta extends player.millitta.Millitta {



    public static void main(String[] args) {
        Random random = new Random();

        // Populationsgroesse mind. 4
        int populationSize = 4;
        Population pop = new Population(populationSize);

        while (true) {
            // Turnier
            LinkedList<Individual> bestSoFar = pop.tournament();

            // Gib einen der besten Beispielhaft aus
            Individual best = bestSoFar.getFirst();

            Helper.printDNA(best.dna);
            System.out.flush();

            // Mutiere ein paar
            for( Individual ind : bestSoFar ) {
                ind.mutate(random.nextInt(4)+1);
            }

            // Crossover um neue zu erzeugen
            Collections.shuffle(bestSoFar);
            for( int i = 0; i < populationSize - bestSoFar.size(); i++ ) {
                Individual mom = bestSoFar.get( random.nextInt(bestSoFar.size()-1) );
                Individual dad = bestSoFar.get( random.nextInt(bestSoFar.size()-1) );

                Individual child = mom.crossover( dad );

                // Add second generation
                bestSoFar.add(child);
            }

            // Alte + Mutationen + Kinder = neue Population
            pop.setIndividuals(bestSoFar);
        }


//        Millitta game = new Millitta();
//
//        long board = 2761269521531535360L; // Start board player1
//        //long board = 1239335431263223808L;
//        //board = 9297206485978469L;
//        board = 9301224276890080L;
//
//        Helper.printBoardState(board);
//        Helper.printBoard(board);
//        //double fitness = (new Evaluate(board)).getFitness();
//
///*        long board = 1518557508648567125L;
//
//        Helper.printBoard(board);
//
//        long board2 = Board.changePlayerIfNecessary(board, 16);
//
//        Helper.printBoardState(board2);
//        Helper.printBoard(board2);*/
//
//
///*        board = 6342767437611008L;
//        Helper.printBoardState(board);
//        Helper.printBoard(board);
//        double fitness = (new Evaluate(board)).getFitness();
//
//
//        System.out.println(fitness);*/
//
//
//        for( int i = 0; i < 3; i++ ) {
//            try {
//                board = game.playWithoutEngine(board);
//            } catch (YouLostException e) {
//                System.out.println("I WILL LOSE! :(");
//            }
//
//            System.out.println(board);
//            Helper.printBoardState(board);
//            Helper.printBoard(board);
//            System.out.println();
//
//            double fitness = (new Evaluate(board)).getFitness();
//            if( fitness == -1L ) {
//                System.out.println("I LOST! :(");
//                break;
//            }
//
//            Helper.printBoardState(board);
//            System.out.println("BEST: " + fitness);
//
//            //board = Generator.get(board).getNextBoards()[0];
//        }

//        System.out.println("Player 1:");
//
//        long boardPlayer2 = game.playWithoutEngine(boardPlayer1);
//
//        System.out.println("Player 2:");
//        Helper.printBoard(boardPlayer2);

//        //long board = 5110530058485761L;
//        //long board = 2148921259453317200L; // ^ (1L << BIT_PLAYER);
//        long board = 1536422364282103296L;
//        board = 1514185841121961473L; // bloed
//        board = 2148885525325414403L;
//        //Helper.printBoard(board);
//        //board = 1909851697447105L;
//        //long board = 1820355849000880256L; // gut
//        //board |= 1L << BIT_PHASE;
//
//        //System.out.println("ok");
//        //Evaluate eval = new Evaluate(board);
//        //System.out.println("Bla " + eval.getClosedMills());
//        Helper.printBoard(board);
//        System.out.println((new Evaluate(board)).getFitness());
//        System.out.println("#########################\n");
//
//        //Helper.printBoard(1820355957494120448L);
//
//
//        //AbstractGenerator gen = Generator.get(board);
//        //long[] next = gen.getNextBoards();
//        //long bestBoard = next[0];
///*
//        long bestBoard = 2126684736293175408L; // 1 Mill
//        //long bestBoard = 2126684736293175376L; // 1 offene
//        System.out.println(bestBoard);
//        System.out.println( "Player1.1: " + ((bestBoard & (1L << BIT_PLAYER )) != 0L));
//
//        Helper.printBoard(bestBoard);
//        System.out.println("#########################\n");
//        System.out.println((new Evaluate(bestBoard)).getFitness());
//
//        //AlphaBetaPruning ab =  new AlphaBetaPruning(board, 1, 1000);
//        //bestBoard = ab.getBestBoard();
//
//        AbstractGenerator gen = Generator.get(bestBoard);
//        long[] next = gen.getNextBoards();
//        bestBoard = next[0];
//
//        System.out.println(bestBoard);
//        System.out.println( "Player2: " + ((bestBoard & (1L << BIT_PLAYER )) != 0L));
//
//        Helper.printBoard(bestBoard);*/
//
////        AbstractGenerator gen = Generator.get(board);
////        long[] next = gen.getNextBoards();
////        for( long b : next) {
////            if( b == MAGIC_NO_BOARD) break;
////            System.out.println("\n"+b+"\n"+(new Evaluate(b)).getFitness());
////
////            Helper.printBoard(b);
////        }
////
//        AlphaBetaPruning ab =  new AlphaBetaPruning(board, 8, 5000);
//        long bestBoard = ab.getBestBoard();
//
//        System.out.println("Now: " + board + " | Next: " + bestBoard);
//
//        System.out.println("Next:");
//        Helper.printBoard(bestBoard);
////
////        long bitDiff = (board ^ bestBoard) & BITS_MENS;
////        int pos = (int) (Math.log(bitDiff) / LOG2);
////
////        System.out.println("Pos: " + pos);
    }
}
