package player.millitta;


public class Helper implements Constants {

    static public int getMyMenOnBoard(long board) {
        if ((board & (1L << BIT_PLAYER)) == 0L) {
            return Long.bitCount(board & BITS_MENS1);
        } else {
            return Long.bitCount(board & BITS_MENS2);
        }
    }

    static public int playerOnPos(long board, int pos) {
        if ((board & (1L << pos)) != 0) {
            return 1;
        } else if ((board & (1L << (pos + 24))) != 0) {
            return 2;
        }

        return 0;
    }

    /*
    0 ----------- 1 ----------- 2
    |             |             |
    |   8 ------- 9 ------ 10   |
    |   |         |         |   |
    |   |    16 - 17 - 18   |   |
    |   |    |         |    |   |
    7 - 15 - 23        19- 11 - 3
    |   |    |         |    |   |
    |   |    22 - 21 - 20   |   |
    |   |         |         |   |
    |   14 ------ 13 ----- 12   |
    |             |             |
    6 ----------- 5 ----------- 4
 */
    // FIXME copy&paste was faster than thinking
    static public void printBoard(long board) {
        System.out.print(playerOnPos(board, 0));
        System.out.print(" --------- ");
        System.out.print(playerOnPos(board, 1));
        System.out.print(" --------- ");
        System.out.print(playerOnPos(board, 2));

        System.out.print("\n|           |           |\n");

        System.out.print("|   " + playerOnPos(board, 8));
        System.out.print(" ----- ");
        System.out.print(playerOnPos(board, 9));
        System.out.print(" ----- ");
        System.out.print(playerOnPos(board, 10));

        System.out.print("   |\n|   |       |       |   |\n");

        System.out.print("|   |   " + playerOnPos(board, 16));
        System.out.print(" - ");
        System.out.print(playerOnPos(board, 17));
        System.out.print(" - ");
        System.out.print(playerOnPos(board, 18));

        System.out.print("   |   |\n|   |   |       |   |   |\n");

        System.out.print(playerOnPos(board, 7) + " - ");
        System.out.print(playerOnPos(board, 15) + " - ");
        System.out.print(playerOnPos(board, 23) + "       ");

        System.out.print(playerOnPos(board, 19) + " - ");
        System.out.print(playerOnPos(board, 11) + " - ");
        System.out.print(playerOnPos(board, 3));

        System.out.print("\n|   |   |       |   |   |\n");
        System.out.print("|   |   " + playerOnPos(board, 22));
        System.out.print(" - ");
        System.out.print(playerOnPos(board, 21));
        System.out.print(" - ");
        System.out.print(playerOnPos(board, 20));

        System.out.print("   |   |\n|   |       |       |   |\n");


        System.out.print("|   " + playerOnPos(board, 14));
        System.out.print(" ----- ");
        System.out.print(playerOnPos(board, 13));
        System.out.print(" ----- ");
        System.out.print(playerOnPos(board, 12));

        System.out.print("   |\n|           |           |\n");

        System.out.print(playerOnPos(board, 6));
        System.out.print(" --------- ");
        System.out.print(playerOnPos(board, 5));
        System.out.print(" --------- ");
        System.out.println(playerOnPos(board, 4));
    }

    static public void printBoardState(long board) {
        System.out.print("Player: " + ((board & (1L << BIT_PLAYER)) != 0 ? 2 : 1) + " | ");
        System.out.print("Phase: ");

        if ((board & ((1L << BIT_ACTION))) != 0L && ((board & (1L << (BIT_ACTION + 1))) != 0L)) { // Remove
            System.out.println("Remove ");
        } else if ((board & (1L << BIT_PHASE)) != 0 && (board & (1L << (BIT_PHASE + 1))) != 0) { // Flugphase
            System.out.println("Flying phase");
        } else if ((board & (1L << BIT_PHASE)) != 0) { // Setzphase
            System.out.println("Placing");
        } else { // Zugphase
            System.out.println("Moving");
        }
    }

    static public String[] dnaName = {
            "open mills",
            "closed mills",
            "Zwickmuehle",
            "Gabeln",
            "men",
            "movable",
            "Kreuzungen"
    };

    static public void printDNA(double[] dna) {
        System.out.println("{");
        for( int i = 0; i < 7; i++ ) {
            System.out.println("\t" + dna[i] + ", // " + dnaName[i]);
        }

        System.out.println("}");

    }

}
