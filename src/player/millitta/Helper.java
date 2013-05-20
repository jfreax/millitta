package player.millitta;


public class Helper {

    static int BitCount( long n )
    {
        int count = 0;
        while (n != 0) {
            count++;
            n &= (n - 1);
        }
        return count;
    }
}
