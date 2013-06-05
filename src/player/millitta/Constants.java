package player.millitta;


public interface Constants {
    long BIT_PLAYER = 48;
    long BIT_PHASE = 49;
    long BIT_ACTION = 52;

    long BITS_MENS1 = 16777215L; // TODO is this correct?
    long BITS_MENS2 = 281474959933440L;

    long GAMEPHASE_SP = 0;
    long GAMEPHASE_ZP = 1;
    long GAMEPHASE_FP = 2;

    int WEIGHT_OPEN_MILL = 0;
    int WEIGHT_CLOSED_MILL = 1;

    long MAGIC_NO_BOARD = -1L; // kennzeichnet ein invaliden oder nicht existenten board status


    double LOG2 = Math.log(2);
}
