package player.millitta;


public interface Constants {
    /* Bit position */
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

    /*
    Masks for all 16 mills.
    Bits 1-24 are board position.
    Bits 24-31 are unused
   */
    final static int mills[] = {
            7, // 0, 1, 2
            28, // 2, 3, 4
            112, // 4, 5, 6
            193, // 6, 7, 0

            1792, // 8, 9, 10
            7168, // 10, 11, 12
            28672, // 12, 13, 14
            49408, // 14, 15, 8

            459752, // 16, 17, 18
            1835008, // 18, 19, 20
            7340032, // 20, 21, 22
            12648448, // 22, 23, 16

            131586, // 1, 9, 17
            526344, // 3, 11, 19
            2105376, // 5, 13, 21
            8421504 // 7, 15, 23
    };

    /* Nachbarn */
    final static int[] neighbors[] = {
            {7, 1},
            {0, 2, 9},
            {1, 3},
            {2, 4},
            {3, 5},
            {4, 6, 13},
            {5, 7},
            {6, 0, 15}, // 7
            {15, 9},
            {8, 1, 10},
            {9, 11},
            {10, 3, 12, 19},
            {11, 13},
            {14, 21, 12, 5},
            {13, 15},
            {14, 8}, // 15
            {23, 17},
            {16, 9, 18},
            {17, 19},
            {18, 11, 20},
            {19, 21},
            {22, 20, 13},
            {23, 21},
            {15, 16, 22}
    };

    /* Magics */
    long MAGIC_NO_BOARD = -1L; // kennzeichnet ein invaliden oder nicht existenten board status

    /* Math constants */
    double LOG2 = Math.log(2);
}
