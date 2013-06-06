package player.millitta.Generate;



public class GeneratorMovingPhase extends AbstractGenerator {

    public GeneratorMovingPhase(long board) {
        super(board);
    }


    public long[] getNextBoards() {
        int rest = 9 - getMyMenOnBoard();

        System.out.println("Generate: Zugphase");

        // TODO Zugphase

        nextBoards[boardPointer] = MAGIC_NO_BOARD;
        return nextBoards;
    }
}
