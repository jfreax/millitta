package player.millitta;

public class Generator implements Constants {


    public static long getNextBoard(long board) {

        int playerID = (board & (1L << BIT_PLAYER)) == 0 ? 0 : 1;

        if ((board & (1L <<  BIT_GAMEPHASE))    != 0 &&
            (board & (1L << (BIT_GAMEPHASE+1))) == 0) { // SET MAN

            //int freeMens =
        }

        return 0L;
    }

}
