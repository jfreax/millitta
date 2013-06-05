package player.millitta;


public class Helper implements Constants {
    static public int getMyMen(long board) {
        if ((board & (1L << BIT_PLAYER)) != 0) {
            return Long.bitCount(board & 281474959933440L); // TODO is this correct?
        } else {
            return Long.bitCount(board & 16777215L);
        }
    }
}
