package player.millitta;


public interface Constants {

    int TIME_PER_MOVE = 50000; // in ms

    /* Bit position */
    long BIT_PLAYER = 48;
    long BIT_PHASE = 49;
    long BIT_ACTION = 52;

    long BIT_REST1 = 54;
    long BIT_REST2 = 58;

    long BITS_MENS1 = 16777215L;
    long BITS_MENS2 = 281474959933440L;
    long BITS_MENS  = 16777215L | 281474959933440L;

    long GAMEPHASE_SP = 0;
    long GAMEPHASE_ZP = 1;
    long GAMEPHASE_FP = 2;

    int WEIGHT_OPEN_MILL = 0;
    int WEIGHT_CLOSED_MILL = 1;
    int WEIGHT_DOUBLE_MILL = 2;
    int WEIGHT_MEN = 4;
    int WEIGHT_MOVABLE = 5;

    static double[][] Weighting = {
            {
                    2.8805077475449576, // open mills
                    2.7690945955253317, // closed mills
                    18.19591255975574, // Zwickmuehle
                    0.5192642916523981, // Gabeln
                    14.558305542722866, // men
                    0.6619799491171481, // movable
                    6.242419112367337, // Kreuzungen
            },
            {
                    2.8805077475449576, // open mills
                    2.7690945955253317, // closed mills
                    18.19591255975574, // Zwickmuehle
                    0.5192642916523981, // Gabeln
                    14.558305542722866, // men
                    0.6619799491171481, // movable
                    6.242419112367337, // Kreuzungen
            }
    };

    /* Magics */
    long MAGIC_NO_BOARD = -1L; // kennzeichnet ein invaliden oder nicht existenten board status

    /* Math constants */
    double LOG2 = Math.log(2);
}
