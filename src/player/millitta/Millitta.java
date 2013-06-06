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


        setMessage("Board: " + String.valueOf(board));

        AbstractGenerator nextBoards = Generator.get(board);
        long nextBoard = nextBoards.getNextBoards()[0];

        setMessage("Next board: " + nextBoard);

        // Make the move!
        long bitDiff = board ^ nextBoard;
        if ((board & ((1L << BIT_ACTION))) != 0L) {
            int pos = (int) (Math.log(bitDiff) / LOG2);
            pos = pos >= 24 ? pos - 24 : pos;

            if ((board & (1L << (BIT_ACTION + 1))) != 0L) { // Remove man
                this.removeMan(pos);

            } else { // Set man
                this.setMan(pos);
            }
        } else { // move man
            int pos1 = (int) (Math.log(bitDiff) / LOG2); // highest set bit
            int pos2 = (int) (Math.log(bitDiff & ~(1L << pos1)) / LOG2); // second set bit

            // TODO lesbar machen
            if ((board & (1L << pos1)) == 0L) {
                moveMan(pos2 >= 24 ? pos2 - 24 : pos2, pos1 >= 24 ? pos1 - 24 : pos1);
            } else {
                moveMan(pos1 >= 24 ? pos1 - 24 : pos1, pos2 >= 24 ? pos2 - 24 : pos2);
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

        if (getAction() != SET_MAN) {
            if (Board.getMyMenOnBoard(board) <= 3 && countMyRest() <= 0) { // Flugphase
                board |= (1L << BIT_PHASE) | (1L << (BIT_PHASE + 1));
            } else if (countMyRest() > 0) { // Setzphase
                board |= 1L << BIT_PHASE;
            } else { // Zugphase
                // BIT_PHASE => 0
            }
        }

        return board;
    }
}
