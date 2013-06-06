package player.millitta.Generate;


import player.millitta.Board;
import player.millitta.Constants;

abstract public class AbstractGenerator implements Constants, algds.Constants {
    protected static long[] nextBoards = new long[100]; // TODO find max. size
    protected long board = 0L;
    protected int boardPointer = 0;


    public AbstractGenerator(long board) {
        this.board = board;
    }

    abstract public long[] getNextBoards();

    public int getMyMenOnBoard() {
        return Board.getMyMenOnBoard(board);
    }

    public int getFreePoss() {
        return 24 - Long.bitCount((board & BITS_MENS1) | ((board & BITS_MENS2) >> 24));
    }

    public long setMyMan(int at) {
        if ((board & (1L << BIT_PLAYER)) != 0) {
            return board | (1L << (at + 24));
        } else {
            return board | (1L << at);
        }
    }

    public boolean isMyMen(int at) {
        if ((board & (1L << BIT_PLAYER)) != 0L) {
            return (board & (1L << (24 + at))) != 0L;
        } else {
            return (board & (1L << at)) != 0L;
        }
    }

    public boolean isMen(int at) {
        return (board & ((1L << (24 + at)) | (1L << at))) != 0L;
    }

    public long moveMyMen(int from, int to) {
        if ((board & (1L << BIT_PLAYER)) != 0) {
            from += 24;
            to += 24;
        }
        return (board | (1L << to)) & ~(1L << from);
    }

    public int numberOfOppManOnBoard() {
        return Long.bitCount(board & BITS_MENS2);
    }

    /*
        Entfernen einer Spielfigur des Gegners.
        Testet nicht ob das ein valider Zug ist!
    */
    public long removeOppMan(int pos) {
        if ((board & (1L << BIT_PLAYER)) == 0) {
            return board & ~(1L << (pos + 24));
        } else {
            return board & ~(1L << pos);
        }
    }

    /*
        Testet ob das entfernen einer gegnerisches Figur an Position pos
        vom Spielbrett moeglich (erlaubt) ist.
     */
    public boolean isRemoveOppManPossible(int pos) {
        // Wenn dort keine Figur steht, kann man sie auch nicht entfernen.
        if (!isOppMen(pos)) {
            return false;
        }

        // Wenn mehr als drei gegnerische Figuren auf dem Spielbrett sind,
        // dann darf die zu entfernende Figur nicht in einer Muehle sein.
        if (numberOfOppManOnBoard() >= 3) {
            if (isOppMenInMill(pos)) {
                return false;
            }
        }

        return true;
    }

    public boolean isOppMen(int at) {
        if ((board & (1L << BIT_PLAYER)) == 0) {
            return (board & (1L << (24 + at))) != 0L;
        } else {
            return (board & (1L << at)) != 0L;
        }
    }

    public boolean isOppMenInMill(int pos) {
        if ((board & (1L << BIT_PLAYER)) == 0) {
            pos += 24;
        }

        for (int mill : mills) {
            if (Long.bitCount(board & mill) == 3) {
                if ((mill & (1L << pos)) != 0) {
                    return true;
                }
            }
        }
        return false;
    }
}
