package player.millitta.Search;

import player.millitta.Constants;
import player.millitta.Evaluate.Evaluate;
import player.millitta.Evaluate.Evaluator;
import player.millitta.Generate.AbstractGenerator;
import player.millitta.Generate.Generator;
import player.millitta.Helper;

import java.util.HashMap;
import java.util.Map;


public class AlphaBetaPruning implements AlphaBetaPruningConstants, Constants {
    private Map<Long, Long> transpositionTable; // BoardState, ComputedEvaluation
    private int maxDepth;
    private int maxTime;
    private long endTime;

    private long board;

    private long currentBestBoard = MAGIC_NO_BOARD;
    private double currentBestBoardValue = NEG_INFINITY;


    public AlphaBetaPruning(long board, int maxDepth, int maxTime) {
        this.transpositionTable = new HashMap<Long, Long>();

        this.board = board;

        this.maxDepth = maxDepth;
        this.maxTime = maxTime;
    }


    public long getBestBoard() {
        this.endTime = maxTime + System.currentTimeMillis();

        long prevBestBoard = currentBestBoard;
        double prevBestBoardValue = currentBestBoardValue;

        for (int depth = Math.min(2, maxDepth); depth <= maxDepth; depth += 2) {
            System.out.println("------- Start with depth: " + depth);
            double value = alphaBetaPruningSearch(board, NEG_INFINITY, INFINITY, 0, depth);

            //if (Math.abs(value) == END_VALUE) {
                if (currentBestBoardValue <= prevBestBoardValue) {
                    currentBestBoard = prevBestBoard;
                    currentBestBoardValue = prevBestBoardValue;
                }

            //    break;
            //}
            //System.out.println("Current best: " + currentBestBoardValue);


            prevBestBoard = currentBestBoard;
            prevBestBoardValue = value;
        }

        System.out.println("Abs best: " + currentBestBoardValue);

        return currentBestBoard;

    }


    private double alphaBetaPruningSearch(long currentBoard, double alpha, double beta, int currentDepth, int remainingDepth) {
        if (System.currentTimeMillis() > endTime) {
            return END_VALUE;
        }

        // TODO!
        Long boardComputedL = transpositionTable.get(currentBoard);
        if (boardComputedL != null) {
            long boardComputed = boardComputedL;
            int boardValue = (int) (boardComputed & 2147483647); // (2^31)-1 aka first 31 bit

            if ((boardComputed & (1L << BIT_HAS_CUT)) != 0) {
                alpha = Math.max(alpha, boardValue);
            }

            if ((boardComputed & (1L << BIT_CAN_CUT_DEEPER)) != 0) {
                beta = Math.min(beta, boardValue);
            }

            if (((boardComputed & (1L << BIT_HAS_CUT)) == 0 && (boardComputed & (1L << BIT_CAN_CUT_DEEPER)) == 0)
                    || alpha >= beta) {
                if (currentDepth == 0) {
                    // TODO currentBestMove = get best move from boardComputed;
                    //currentBestBoardValue = boardValue;
                }

                return boardValue;
            }
        }

        // Maximale Tiefe erreicht
        // Rueckgabe der Fitness dieses Knotens
        if (remainingDepth == 0) {
            //System.out.println("Depth0: " + (currentBoard));
            //System.out.println("Depth0 fitness: " + (new Evaluate(currentBoard)).getFitness());
            //Helper.printBoard(currentBoard);

            return (new Evaluate(currentBoard)).getFitness();
        } else {
            long nodeBestBoard = MAGIC_NO_BOARD;
            double nodeBestValue = NEG_INFINITY;

            // Hole alle moeglichen naechsten Spielzuege
            AbstractGenerator nextBoardsGenerator = Generator.get(currentBoard);
            long[] nextBoards = nextBoardsGenerator.getNextBoards();

            // Keine weiteren Spielzuege mehr vorhanden
            if( nextBoards[0] == MAGIC_NO_BOARD) {
                return (new Evaluate(currentBoard)).getFitness();
            }

            // Ergebnis vom rekursiven Aufruf
            double value;

            // Für jedes moegliche Nachfolgespielbrett...
            for (long nextBoard : nextBoards) {
                if( nextBoard == MAGIC_NO_BOARD ) {
                    break;
                }

                // Wenn das Spielbrett fuer den zu maximierenden Spieler ist
                if( isMaxPlayer(currentBoard)) {
                    value = alphaBetaPruningSearch(nextBoard, alpha, beta, currentDepth+1, remainingDepth-1);
                    if( value > alpha) {
                        alpha = value;

                        // Obersten Knoten als den momentan besten merken
                        if ( currentDepth == 0 ) {
                            currentBestBoard = nextBoard;
                            currentBestBoardValue = value;
                        }
                    }

                    if (value > nodeBestValue) {
                        nodeBestValue = value;
                        nodeBestBoard = nextBoard;
                    }

                } else {
                    beta = Math.min(beta, alphaBetaPruningSearch(nextBoard, alpha, beta, currentDepth+1, remainingDepth-1));
                }

                // Cut-off
                if ( beta <= alpha ) {
                    break;
                }
            }

            return nodeBestValue;

//            if( isMaxPlayer(currentBoard)) {
//                System.out.println("Alpha: " + alpha);
//                return alpha; // Alpha Cut-off
//            } else {
//                System.out.println("Beta: " + beta);
//                return  beta; // Beta Cut-off
            //}

            //transpositionTable.put(currentBoard.getBoardID(), new BoardStateValue(nodeBestValue, remainingDepth, nodeBestMove, alpha >= beta, nodeBestValue < alpha));

            //return nodeBestValue;
        }
    }

    private boolean isMaxPlayer(long nextBoard) {
        return (board & (1L << BIT_PLAYER)) == (nextBoard & (1L << BIT_PLAYER));
    }
}
