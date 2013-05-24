package player.millitta;

import java.util.HashMap;
import java.util.Map;


public class AlphaBetaPruning {
    private Map<Long, Long> transpositionTable;
    private int maxDepth;
    private int maxTime;
    private long startTime;


    public AlphaBetaPruning(long boardState, int maxDepth, int maxTime) {
        this.transpositionTable = new HashMap<Long, Long>();

        this.maxDepth = maxDepth;
        this.maxTime = maxTime;

        this.startTime = System.currentTimeMillis();
    }

    private int alphaBetaPruningSearch(int alpha, int beta, int currentDepth, int remainingDepth) {

        return 0;
    }
}
