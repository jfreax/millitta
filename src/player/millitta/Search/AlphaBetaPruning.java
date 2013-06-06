package player.millitta.Search;

import java.util.HashMap;
import java.util.Map;


public class AlphaBetaPruning implements AlphaBetaPruningConstants {
    private Map<Long, Long> transpositionTable; // BoardState, ComputedEvaluation
    private int maxDepth;
    private long endTime;

    private int currentBestMoveValue = Integer.MIN_VALUE; // -Infinity
    private long currentBestMove = -1; // TODO


    public AlphaBetaPruning(long boardState, int maxDepth, int maxTime) {
        this.transpositionTable = new HashMap<Long, Long>();

        this.maxDepth = maxDepth;
        this.endTime = maxTime + System.currentTimeMillis();
    }

    private int alphaBetaPruningSearch(long board, int alpha, int beta, int currentDepth, int remainingDepth) {
        if (System.currentTimeMillis() > endTime) {
            // TODO proper ending, set variable or something
            // TODO return something meaningful
            return 0;
        }

        Long boardComputedL = transpositionTable.get(board);
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
                    currentBestMoveValue = boardValue;
                }

                return boardValue;
            }

        }

        return 0;
    }
}
