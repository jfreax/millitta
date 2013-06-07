package player.millitta.Generate;


import player.millitta.Board;
import player.millitta.Helper;
import player.millitta.Constants;

abstract public class AbstractGenerator extends Board {
    protected long[] nextBoards = new long[100]; // TODO find max. size
    protected int boardPointer = 0;


    public AbstractGenerator(long board) {
        super(board);
    }

    abstract public long[] getNextBoards();
}
