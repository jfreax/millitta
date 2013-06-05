package player.millitta;

import algds.Player;

public class Millitta extends Player implements Constants {
    private long board;

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
    protected Millitta() {
        super();
    }

    public void play() {
        board = getBoardLong();

        //Evaluate eval = new Evaluate(board);


        setMessage("Board: "+ String.valueOf(board));

        Generator nextBoards = new Generator(board);
        long nextBoard = nextBoards.getNextBoards()[0];

        setMessage("Next board: " + nextBoard);

        long BITS_PLAYER_POINT = BITS_MENS1;

        if ((board & ((1L << BIT_ACTION))) != 0L) {
            int pos = (int)(Math.log(board ^ nextBoard) / LOG2);
            pos = pos >= 24 ? pos-24 : pos;

            if ( (board & (1L << (BIT_ACTION + 1))) != 0L ) { // Remove man
                this.removeMan(pos);
            } else { // Set man
                setMessage("OK!" + pos);
                this.setMan(pos);
            }
        } else { // move man
            // TODO
        }

        //for (int i = 0; i < 24; i++) {
        //    //setMessage("& "+ (board & (1<<i)));
        //    if (((board & (1 << i)) + (board & (1 << i + 24))) == 0) {
        //        setMan(i);
        //        return;
        //    }
        //}
    }

    public String getAuthor() {
        return "Jens Dieskau";
    }

    private long getBoardLong() {
        long board = 0L;
        int[] boardArray = getBoard();

        if (getMyColor() == BLACK) {
            board = 1L << BIT_PLAYER;
        }

        for (int i = 0; i < 24; ++i) {
            switch (boardArray[i]) {
                case BLACK:
                    board |= 1 << (24 + i);
                    break;
                case WHITE:
                    board |= 1 << i;
                    break;
                default:
                    break;
            }
        }

        switch (getAction()) {
            case SET_MAN:
                board |= 1L << BIT_PHASE; // Setzphase
                board |= 1L << BIT_ACTION;
                break;
            case MOVE_MAN:
                board |= 1L << BIT_ACTION + 1;
                break;
            case REMOVE_MAN:
                board |= (1L << BIT_ACTION) | (1L << (BIT_ACTION + 1));
                break;
            default:
                break;
        }

        if ( getAction() != SET_MAN ) {
            if( Board.getMyMenOnBoard(board) <= 3 && countMyRest() <= 0 ) { // Flugphase
                System.out.println("Flugphase");
                board |= (1L << BIT_PHASE) | (1L << (BIT_PHASE+1));
            } else if ( countMyRest() > 0 ) { // Setzphase
                board |= 1L << BIT_PHASE;
            } else { // Zugphase
                System.out.println("Zugphase");
                // BIT_PHASE => 0
            }
        }

        return board;
    }

    private boolean AmIPlayZero() {
        return (board & (1L << BIT_PLAYER)) == 0;
    }

    /*
        Gibt zurueck welcher Stein bewegt, entfernt oder gesetzt wurde
     */
    private int getMoveFromBoard(long nextBoard) {

        long BITS_PLAYER_POINT = BITS_MENS1;
        if ((board & (1L << BIT_PLAYER)) != 0) {
            BITS_PLAYER_POINT = BITS_MENS2;
        }

        // Remove man, or set man
        if ((board & ((1L << BIT_ACTION))) != 0L && (board & (1L << (BIT_ACTION + 1))) != 0L) {
            return (int)(Math.log((board ^ BITS_MENS1) & BITS_PLAYER_POINT) / LOG2);
        } else { // Set man

        }

        return 0;
    }

}
