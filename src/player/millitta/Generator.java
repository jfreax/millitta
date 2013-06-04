package player.millitta;

public class Generator implements Constants {

    private long board_ = 0L;
    private int gamePhase = -1;

    Generator(long board) {
        board_ = board;

        calcGamePhase();
    }

    private void calcGamePhase() {
        if (
                (board_ & (1L <<  BIT_GAMEPHASE))    != 0 &&
                (board_ & (1L << (BIT_GAMEPHASE+1))) == 0 &&
                (board_ & (1L << (BIT_GAMEPHASE+2))) == 0
                ) {
            gamePhase = GAMEPHASE_SETMAN;
        } else if (
                (board_ & (1L <<  BIT_GAMEPHASE))    == 0
                ) {
            gamePhase = GAMEPHASE_MOVEMAN;
        } else if (
                (board_ & (1L <<  BIT_GAMEPHASE))    != 0 &&
                (board_ & (1L << (BIT_GAMEPHASE+1))) != 0 &&
                (board_ & (1L << (BIT_GAMEPHASE+1))) != 0
                ) {
            gamePhase = GAMEPHASE_REMOVEMAN;
        }
    }


    public long getNextBoard() {

        int playerID = (board_ & (1L << BIT_PLAYER)) == 0 ? 0 : 1;



        return 0L;
    }

}
