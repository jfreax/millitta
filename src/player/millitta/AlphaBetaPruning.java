package player.millitta;

import java.util.HashMap;
import java.util.Map;


public class AlphaBetaPruning implements AlphaBetaPruningConstants {
    private Map<Long, Long> transpositionTable; // BoardState, ComputedEvaluation
    private int maxDepth;
    private long endTime;


    public AlphaBetaPruning(long boardState, int maxDepth, int maxTime) {
        this.transpositionTable = new HashMap<Long, Long>();

        this.maxDepth = maxDepth;
        this.endTime = maxTime + System.currentTimeMillis();
    }

    private int alphaBetaPruningSearch(long board, int alpha, int beta, int currentDepth, int remainingDepth) {
        if( System.currentTimeMillis() > endTime ) {
            // TODO proper ending, set variable or something
            // TODO return something meaningful
            return 0;
        }

        Long boardValue = transpositionTable.get(board);
        if( boardValue != null ) {

        }

        return 0;
    }
}
