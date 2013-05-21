package player.millitta;

import algds.Player;

public class Millitta extends Player {
    public int color;
    public int rest;

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
    public Millitta() {
        super();
    }

    public void play() {
        long board = getBoardLong();

        Evaluate eval = new Evaluate(board);

        setMessage(String.valueOf(board));

        setMessage("Fitness: "+ eval.getFitness());

        for( int i = 0; i < 24; i++) {
            //setMessage("& "+ (board & (1<<i)));
            if( ((board & (1<<i)) + (board & (1<<i+24))) == 0 ) {
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

    private long getBoardLong()
    {
        long board = 0L;
        int[] boardArray = getBoard();

        if( getMyColor() == BLACK ) {
            board = 1L << Constants.BIT_PLAYER;
        }

        for( int i = 0; i < 24; ++i ) {
            //setMessage("Board " + i + " = " + boardArray[i] );
            switch (boardArray[i]) {
                case BLACK:
                    board |= 1 << 24+i;
                    break;
                case WHITE:
                    board |= 1 << i;
                    break;
                default:
                    break;
            }
        }

        return board;
    }

}
