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
        color = getMyColor();
        rest = countMyRest();
    }

    public void play() {
        int[] board = getBoard();

    }

    public String getAuthor() {
        return "Jens Dieskau";
    }

}
