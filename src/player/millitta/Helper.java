package player.millitta;


public class Helper implements Constants {
    static public int getMyMen(long board) {
        if ((board & (1L << BIT_PLAYER)) != 0) {
            return Long.bitCount(board & BITS_MENS2);
        } else {
            return Long.bitCount(board & BITS_MENS2);
        }
    }

    static public int getFreePoss(long board) {
        return 24 - Long.bitCount((board & BITS_MENS1) | ((board & BITS_MENS2) << 24));
    }

    static public long setMan(long board, int pos) {
        if ((board & (1L << BIT_PLAYER)) != 0) {
            return board | (pos << 24);
        } else {
            return board | pos;
        }
    }

    static public void printBoard(long board) {

    }
}
