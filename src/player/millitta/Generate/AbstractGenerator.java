package player.millitta.Generate;


import player.millitta.Board;
import player.millitta.Constants;

abstract public class AbstractGenerator implements Constants, algds.Constants {
    protected long board  = 0L;
    protected long action = -1L;

    protected static long[] nextBoards = new long[100]; // TODO find max. size
    protected int boardPointer = 0;


    public AbstractGenerator(long board) {
        this.board = board;
        calcAction();
    }

    abstract public long[] getNextBoards();


    private void calcAction() {
        if ((board & ((1L << BIT_ACTION))) != 0L && ((board & (1L << (BIT_ACTION + 1))) != 0L) ) {
            System.out.println("Remove man");
            action = REMOVE_MAN;
        } else if ((board & (1L << BIT_ACTION)) != 0L ) {
            action = SET_MAN;
        } else {
            action = MOVE_MAN;
        }
    }


    public int getMyMenOnBoard(long board) {
        return Board.getMyMenOnBoard(board);
    }


    public int getFreePoss(long board) {
        return 24 - Long.bitCount((board & BITS_MENS1) | ((board & BITS_MENS2) >> 24));
    }


    public long setMan(long board, int pos) {
        if ((board & (1L << BIT_PLAYER)) != 0) {
            return board | (1L << (pos+24));
        } else {
            return board | (1L << pos);
        }
    }


    public int numberOfOppManOnBoard(long board, int pos) {
        return Long.bitCount( board & BITS_MENS2 );
    }


    /*
        Entfernen einer Spielfigur des Gegners.
        Testet nicht ob das ein valider Zug ist!
    */
    public long removeOppMan(long board, int pos) {
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
    public boolean isRemoveOppManPossible(long board, int pos) {
        // Wenn dort keine Figur steht, kann man sie auch nicht entfernen.
        if ( !isOppMen(board,pos)) {
            System.out.println("Not a men here: " + pos);
            return false;
        }

        // Wenn mehr als drei gegnerische Figuren auf dem Spielbrett sind,
        // dann darf die zu entfernende Figur nicht in einer Muehle sein.
        if( numberOfOppManOnBoard(board, pos) > 3 ) {
            System.out.println("Is mill? " + pos + " -> " + isOppMenInMill(board, pos));
            if( isOppMenInMill(board, pos) ) {
                return false;
            }
        }

        return true;
    }

    public boolean isOppMen(long board, int at) {
        if ((board & (1L << BIT_PLAYER)) == 0) {
            return (board & (1L << (24+at))) != 0;
        } else {
            return (board & (1L << at)) != 0;
        }
    }

    public boolean isOppMenInMill(long board, int pos) {
        for( int mill : mills ) {
            if (Long.bitCount(board & mill) == 3) {
                if ((mill & (1L << pos)) != 0 ) {
                    return true;
                }
            }
        }
        return false;
    }
}
