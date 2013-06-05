package player.millitta;


public class Board implements Constants {
    static public int getMyMenOnBoard(long board) {
        if ((board & (1L << BIT_PLAYER)) != 0) {
            return Long.bitCount(board & BITS_MENS2);
        } else {
            return Long.bitCount(board & BITS_MENS2);
        }
    }

    static public int getFreePoss(long board) {
        return 24 - Long.bitCount((board & BITS_MENS1) | ((board & BITS_MENS2) >> 24));
    }

    static public long setMan(long board, int pos) {
        if ((board & (1L << BIT_PLAYER)) != 0) {
            return board | (1L << (pos+24));
        } else {
            return board | (1L << pos);
        }
    }


    /*
        Entfernen einer Spielfigur des Gegners.
        Testet nicht ob das ein valider Zug ist!
    */
    static public long removeOppMan(long board, int pos) {
        if ((board & (1L << BIT_PLAYER)) == 0) {
            return board & ~(1L << (pos+24));
        } else {
            return board & ~(1L << pos);
        }
    }

    /*
        Testet ob das entfernen einer gegnerisches Figur an Position pos
        vom Spielbrett moeglich (erlaubt) ist.
     */
    static public boolean isRemoveOppManPossible(long board, int pos) {
        //
        if ( !isOppMen(board,pos)) {
            return false;
        }

        // TODO

        return true;
    }

    static public boolean isOppMen(long board, int at) {
        if ((board & (1L << BIT_PLAYER)) == 0) {
            return (board & (1L << (24+at))) != 0;
        } else {
            return (board & (1L << at)) != 0;
        }
    }

    static public boolean isOppMenInMill(long board, int pos) {
        return false; // TODO
    }

    static public int playerOnPos( long board, int pos ) {
        if( (board & (1L << pos)) != 0 ) {
            return 1;
        } else if ( (board & (1L << (pos+24))) != 0 ) {
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
        System.out.print( playerOnPos(board, 0) );
        System.out.print(" --------- ");
        System.out.print( playerOnPos(board, 1) );
        System.out.print(" --------- ");
        System.out.print( playerOnPos(board, 2) );

        System.out.print("\n|           |           |\n");

        System.out.print("|   " + playerOnPos(board, 8) );
        System.out.print(" ----- ");
        System.out.print( playerOnPos(board, 9) );
        System.out.print(" ----- ");
        System.out.print( playerOnPos(board, 10) );

        System.out.print("   |\n|   |       |       |   |\n");

        System.out.print("|   |   " + playerOnPos(board, 16) );
        System.out.print(" - ");
        System.out.print( playerOnPos(board, 17) );
        System.out.print(" - ");
        System.out.print( playerOnPos(board, 18) );

        System.out.print("   |   |\n|   |   |       |   |   |\n");

        System.out.print(playerOnPos(board, 7) + " - " );
        System.out.print(playerOnPos(board, 15) + " - " );
        System.out.print(playerOnPos(board, 23) + "       ");

        System.out.print(playerOnPos(board, 19) + " - " );
        System.out.print(playerOnPos(board, 11) + " - " );
        System.out.print(playerOnPos(board, 3));

        System.out.print("\n|   |   |       |   |   |\n");
        System.out.print("|   |   " + playerOnPos(board, 22) );
        System.out.print(" - ");
        System.out.print( playerOnPos(board, 21) );
        System.out.print(" - ");
        System.out.print( playerOnPos(board, 20) );

        System.out.print("   |   |\n|   |               |   |\n");


        System.out.print("|   " + playerOnPos(board, 14) );
        System.out.print(" ----- ");
        System.out.print( playerOnPos(board, 13) );
        System.out.print(" ----- ");
        System.out.print( playerOnPos(board, 12) );

        System.out.print("   |\n|           |           |\n");

        System.out.print( playerOnPos(board, 6) );
        System.out.print(" --------- ");
        System.out.print( playerOnPos(board, 5) );
        System.out.print(" --------- ");
        System.out.println( playerOnPos(board, 4) );

    }
}
