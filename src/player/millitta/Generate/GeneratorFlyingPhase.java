package player.millitta.Generate;


public class GeneratorFlyingPhase extends AbstractGenerator {

    public GeneratorFlyingPhase(long board) {
        super(board);
    }


    public long[] getNextBoards() {
        boardPointer = 0;

        for (int i = 0; i < 24; i++) {
            // Fuer jeden meiner Spielsteine
            if (isMyMen(i)) {
                // Teste jede andere position ...
                for (int j = 0; j < 24; j++) {
                    // ... ob sie noch frei ist.
                    if (!isMen(j)) {
                        long nextBoard = moveMyMen(i, j);

                        nextBoard = changePlayerIfNecessary(nextBoard, j);
                        nextBoard = getBoardWithNewPhase(nextBoard);

                        nextBoards[boardPointer++] = nextBoard;
                    }
                }
            }
        }

        nextBoards[boardPointer] = MAGIC_NO_BOARD;
        return nextBoards;
    }
}
