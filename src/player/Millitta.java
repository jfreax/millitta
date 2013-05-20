package player;


import player.millitta.Evaluate;

public class Millitta extends player.millitta.Millitta {

    public static void main(String [ ] args)
    {
        System.out.println("ok");
        Evaluate eval = new Evaluate(62);
        System.out.println(String.valueOf(eval.getClosedMills()));
    }
}
