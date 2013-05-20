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
        int[] board = getBoard();

        setMessage(String.valueOf(color));

    }

    public String getAuthor() {
        /* Initialize here,  because, yeah, fuck me */
        color = getMyColor();
        rest = countMyRest();

        return "Jens Dieskau";
    }

}
