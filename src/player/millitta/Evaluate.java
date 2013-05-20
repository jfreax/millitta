package player.millitta;

public class Evaluate {
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
        Bits 0 and 25-31 are unused
     */
    private final static int mills[] = {
        14, // 0, 1, 2
        56, // 2, 3, 4
        224, // 4, 5, 6
        386, // 6, 7, 0

        3584, // 8, 9, 10
        14336, // 10, 11, 12
        57344, // 12, 13, 14
        98816, // 14, 15, 8

        917504, // 16, 17, 18
        3670016, // 18, 19, 20
        14680064, // 20, 21, 22
        25296896, // 22, 23, 16

        263172, // 1, 9, 17
        1052688, // 3, 11, 19
        4210752, // 5, 13, 21
        16843008, // 7, 15, 23
    };

    /*
        0 = Player ID to evaluate
         1-24 = Mens of White Player (0)
        25-48 = Mens of Black Player (1)
        49-51 = Game phase
    */
    public Evaluate(long boardState)
    {


    }

    public double getFitness()
    {

        return 0.0f;
    }

    private int getClosedMills()
    {
        int mills = 0;


        return mills;
    }

    private int getOpenMills()
    {
        int mills = 0;

        return mills;
    }
}
