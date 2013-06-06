package player.millitta.Search;


public interface AlphaBetaPruningConstants {
    int BIT_VALUE = 0; // -> 30
    int BIG_REMAIN_DEPTH = 31;
    int BIT_HAS_CUT = 32;
    int BIT_CAN_CUT_DEEPER = 33;

    int END_VALUE = 1000;

    int INFINITY = Integer.MAX_VALUE;
    int NEG_INFINITY = Integer.MIN_VALUE;
}
