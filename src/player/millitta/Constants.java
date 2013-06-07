package player.millitta;


public interface Constants {
    /* Bit position */
    long BIT_PLAYER = 48;
    long BIT_PHASE = 49;
    long BIT_ACTION = 52;

    long BITS_MENS1 = 16777215L;
    long BITS_MENS2 = 281474959933440L;
    long BITS_MENS  = 16777215L | 281474959933440L;

    long GAMEPHASE_SP = 0;
    long GAMEPHASE_ZP = 1;
    long GAMEPHASE_FP = 2;

    int WEIGHT_OPEN_MILL = 0;
    int WEIGHT_CLOSED_MILL = 1;
    int WEIGHT_MEN = 4;

    static final double[] Weighting = {
            1.0, // open mills
            3.0, // closed mills
            5.0, // Zwickmuehle
            1.5, // Gabeln
            1.2, // men
            1.2, // rest
            1.5, // moveable
            1.0  // Kreuzungen
    };

    /* Magics */
    long MAGIC_NO_BOARD = -1L; // kennzeichnet ein invaliden oder nicht existenten board status

    /* Math constants */
    double LOG2 = Math.log(2);
}
