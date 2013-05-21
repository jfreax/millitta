package player;


import player.millitta.Constants;
import player.millitta.Evaluate;

public class Millitta extends player.millitta.Millitta {

    public static void main(String [ ] args)
    {
        System.out.println("ok");
        Evaluate eval = new Evaluate(193);
        System.out.println("Bla " + eval.getClosedMills());
    }
}
