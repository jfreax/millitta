package player.millitta;

import algds.Player;
import player.millitta.Generate.AbstractGenerator;
import player.millitta.Generate.Generator;

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

        AbstractGenerator nextBoards = Generator.get(board);
        long nextBoard = nextBoards.getNextBoards()[0];

        setMessage("Next board: " + nextBoard);

        long BITS_PLAYER_POINT = BITS_MENS1;
        long bitDiff = board ^ nextBoard;

        if ((board & ((1L << BIT_ACTION))) != 0L) {
            int pos = (int)(Math.log(bitDiff) / LOG2);
            pos = pos >= 24 ? pos-24 : pos;

            if ( (board & (1L << (BIT_ACTION + 1))) != 0L ) { // Remove man
                this.removeMan(pos);
            } else { // Set man
                this.setMan(pos);
            }
        } else { // move man
            int pos1 = (int)(Math.log(bitDiff) / LOG2); // highest set bit
            int pos2 = (int)(Math.log(bitDiff & ~(1L << pos1)) / LOG2); // second set bit

            System.out.println("Move man: " + pos1 + " <-> " + pos2 + " von " + bitDiff);

            // TODO lesbar machen
            if( (board & (1L << pos1)) == 0L ) {
                moveMan(pos2 >= 24 ? pos2-24 : pos2, pos1 >= 24 ? pos1-24 : pos1);
            } else {
                moveMan(pos1 >= 24 ? pos1-24 : pos1, pos2 >= 24 ? pos2-24 : pos2);
            }

        }
    }

    public String getAuthor() {
        return "Jens Dieskau";
    }

    private long getBoardLong() {
        System.out.println("getBoardLong");
        long board = 0L;
        int[] boardArray = getBoard();

        if (getMyColor() == BLACK) {
            board = 1L << BIT_PLAYER;
        }

        for (int i = 0; i < 24; ++i) {
            switch (boardArray[i]) {
                case BLACK:
                    board |= 1L << (24 + i);
                    break;
                case WHITE:
                    board |= 1L << i;
                    break;
                default:
                    break;
            }
        }

        if ((board & ((1L << BIT_ACTION))) != 0L && ((board & (1L << (BIT_ACTION + 1))) != 0L) ) {
            System.out.println("Neeeeeein00 " + board);
        }

        switch (getAction()) {
            case SET_MAN:
                System.out.println("Action -> Set_Man");
                board |= 1L << BIT_PHASE; // Setzphase
                board |= 1L << BIT_ACTION;
                break;
            case MOVE_MAN:
                System.out.println("Action -> Move_Man");
                board |= 1L << BIT_ACTION + 1;
                break;
            case REMOVE_MAN:
                System.out.println("Action -> Remove_Man");
                board |= (1L << BIT_ACTION) | (1L << (BIT_ACTION + 1));
                break;
            default:
                System.out.println("Whatt??");
                break;
        }

        if ((board & ((1L << BIT_ACTION))) != 0L && ((board & (1L << (BIT_ACTION + 1))) != 0L) ) {
            System.out.println("Neeeeeein0");
        }

        if ( getAction() != SET_MAN ) {
            if( Board.getMyMenOnBoard(board) <= 3 && countMyRest() <= 0 ) { // Flugphase
                System.out.println("Flugphase");
                board |= (1L << BIT_PHASE) | (1L << (BIT_PHASE+1));
            } else if ( countMyRest() > 0 ) { // Setzphase
                System.out.println("countMyRest() -> " + countMyRest());
                board |= 1L << BIT_PHASE;
            } else { // Zugphase
                System.out.println("Zugphase");
                // BIT_PHASE => 0
            }
        }

        if ((board & ((1L << BIT_ACTION))) != 0L && ((board & (1L << (BIT_ACTION + 1))) != 0L) ) {
            System.out.println("Neeeeeein");
        } else {
            System.out.println("Mhh");
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
