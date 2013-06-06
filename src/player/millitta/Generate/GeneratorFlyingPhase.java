package player.millitta.Generate;


public class GeneratorFlyingPhase extends AbstractGenerator {

    public GeneratorFlyingPhase(long board) {
        super(board);
    }

    @Override
    public long[] getNextBoards() {
        System.out.println("Generate: Flugphase");
        boardPointer = 0;

        nextBoards[boardPointer] = MAGIC_NO_BOARD;
        return nextBoards;
    }
}
