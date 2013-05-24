package player.millitta;

import algds.Player;

public class Millitta extends Player implements Constants {
    private int color;
    private int rest;

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
        long board = getBoardLong();

        Evaluate eval = new Evaluate(board);


        setMessage(String.valueOf(board));

        setMessage("Fitness: " + eval.getFitness());

        for (int i = 0; i < 24; i++) {
            //setMessage("& "+ (board & (1<<i)));
            if (((board & (1 << i)) + (board & (1 << i + 24))) == 0) {
                setMan(i);
                return;
            }
        }
    }

    public String getAuthor() {
        /* Initialize here,  because, yeah, fuck me */
        color = getMyColor();
        rest = countMyRest();

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
                    board |= 1 << 24 + i;
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
                board |= 1L << BIT_GAMEPHASE;
                break;
            case MOVE_MAN:
                if (getMyColor() == WHITE) {
                    board |= 1L << BIT_GAMEPHASE + 1;
                }
                break;
            case REMOVE_MAN:
                board |= (1L << BIT_GAMEPHASE) | (1L << (BIT_GAMEPHASE + 1));
                // TODO ziehen (0) oder nehmen(1)? BIT_(GAMEPHASE+2)
                break;
            default:
        }

        return board;
    }

}
