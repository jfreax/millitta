package player.millitta;


abstract public class Board implements Constants, algds.Constants {
    protected long board;

    protected Board(long board) {
        this.board = board;
    }

    public int getFreePoss() {
        return 24 - Long.bitCount((board & BITS_MENS1) | ((board & BITS_MENS2) >> 24));
    }


    public int getMyMenOnBoard() {
        return Helper.getMyMenOnBoard(board);
    }


    public long setMyMan(int at) {
        if ((board & (1L << BIT_PLAYER)) != 0L) {
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
        if ((board & (1L << BIT_PLAYER)) != 0L) {
            from += 24;
            to += 24;
        }
        return (board | (1L << to)) & ~(1L << from);
    }


    public int getOppMenOnBoard() {
        return Long.bitCount(board & BITS_MENS2);
    }


    /*
        Entfernen einer Spielfigur des Gegners.
        Testet nicht ob das ein valider Zug ist!
    */
    public long removeOppMan(int pos) {
        if ((board & (1L << BIT_PLAYER)) == 0L) {
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
        if (getOppMenOnBoard() >= 3) {
            if (isOppMenInMill(pos)) {
                return false;
            }
        }

        return true;
    }

    public boolean isOppMen(int at) {
        if ((board & (1L << BIT_PLAYER)) == 0L) {
            return (board & (1L << (24 + at))) != 0L;
        } else {
            return (board & (1L << at)) != 0L;
        }
    }

    public boolean isOppMenInMill(int pos) {
        long tmpBoard = board;
        if ((board & (1L << BIT_PLAYER)) == 0L) {
            tmpBoard >>= 24;
        }

        for (int mill : mills) {
            if (Long.bitCount(tmpBoard & mill) == 3) {
                if ((mill & (1L << pos)) != 0L) {
                    return true;
                }
            }
        }
        return false;
    }
}
