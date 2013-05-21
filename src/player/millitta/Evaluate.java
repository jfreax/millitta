package player.millitta;


public class Evaluate implements Constants {
    public static double[] Weighting = {
        1.0, // open mills
        3.0, // closed mills
        5.0, // Zwickmuehle
        1.5, // Gabeln
        1.2, // men
        1.2, // rest
        1.5, // moveable
        1.0  // Kreuzungen
    };

    /*
        Masks for all 16 mills.
        Bits 1-24 are board position.
        Bits 24-31 are unused
     */
    private final static int mills[] = {
        7, // 0, 1, 2
        28, // 2, 3, 4
        112, // 4, 5, 6
        193, // 6, 7, 0

        1792, // 8, 9, 10
        7168, // 10, 11, 12
        28672, // 12, 13, 14
        49408, // 14, 15, 8

        459752, // 16, 17, 18
        1835008, // 18, 19, 20
        7340032, // 20, 21, 22
        12648448, // 22, 23, 16

        131586, // 1, 9, 17
        526344, // 3, 11, 19
        2105376, // 5, 13, 21
        8421504 // 7, 15, 23
    };

    long boardState_ = 0L;
    /* board state for only the player to evaluate */
    long playersBoard_ = 0L;

    /*
         0-22 = Mens of White Player (0)
        24-47 = Mens of Black Player (1)
           48 = Player ID to evaluate
        49-51 = Game phase
    */
    public Evaluate(long boardState)
    {
        this.boardState_ = playersBoard_ = boardState;

        if ( (boardState_ & (1L<<48)) == 1 ) {
            playersBoard_ = boardState_ >> 24;
        }
    }

    public double getFitness()
    {
        double fitness = 0.f;

        fitness += Weighting[WEIGHT_OPEN_MILL] * getOpenMills();
        fitness += Weighting[WEIGHT_CLOSED_MILL] * getClosedMills();

        return fitness;
    }

    /*
        Es existieren insgesamt 16 mögliche Mühlen.
        Für jede Mühle wird eine 24 Bit-Maske angelegt und die entsprechenden Bits gemäß des Spielfeldes gesetzt.
        Auf diese Masken kann dann eine logische UND-Verknüpfung mit den Bits der gesetzten Steine
        des entsprechenden Spielers angewandt werden.

        Sind bei einer Maske noch 3 Bits gesetzt, so hat der Spieler dort eine geschlossene Mühle.
        Durch Abzählen, bei wievielen der 16 Masken dies vorkommt,
        weiß man wieviele Mühlen der Spieler insgesamt hat.
     */
    public int getClosedMills()
    {
        int closedMills = 0;

        for( int mill : mills ) {
            if( Long.bitCount( playersBoard_ & mill ) == 3 ) {
                closedMills++;
            }
        }

        return closedMills;
    }

    /*
        Genau wie beim Erkennen der geschlossenen Mühlen werden hier die Steine des Spielers mit allen 16 Masken
        logisch mit UND-Verknüpft. Eine offene Mühle liegt genau dann vor,
        wenn 2 Bits in einer verknüpften Maske noch gesetzt sind.

        Solange der auszuwertende Spieler noch Steine zum Setzen hat oder springen kann,
        reicht diese Bedingung schon aus. Ansonsten muss geprüft werden, ob ein angrenzedes Feld zu dem Feld,
        in dem der Stein zur fertigen Mühle fehlt, auch ein Stein des selben Spielers liegt.
        Dieser Stein darf dann natürlich nicht selber schon Teil der gerade betrachteten offenen Mühle sein.
     */
    private int getOpenMills()
    {
        int openMills = 0;

        // TODO !
        for( int mill : mills ) {
            if( Long.bitCount( playersBoard_ & mill ) == 2 ) {
                openMills++;
            }
        }

        return openMills;
    }
}
