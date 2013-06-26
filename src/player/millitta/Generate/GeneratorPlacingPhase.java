package player.millitta.Generate;


public class GeneratorPlacingPhase extends AbstractGenerator {


    public GeneratorPlacingPhase(long board) {
        super(board);
    }

    public long[] getNextBoards() {
        boardPointer = 0;

        int myPlayerOnBoard = getMyMenOnBoard();
        int maxRest = 9 - myPlayerOnBoard;
        if (maxRest <= 0) {
            System.out.println("Something went wrong!\nGamephase says I have to set a man, but no men left to set :/");
            return nextBoards;
        }

        // Spielsteine beider Spieler zusammen.
        // Sagt aus welche Position frei und welche besetzt ist.
        long mergedBoardPoints = (board & BITS_MENS1) | ((board & BITS_MENS2) >> 24);
        for (int i : idOrdner) {
            if ((mergedBoardPoints & (1L << i)) == 0) {
                // Setze Figur auf Spielbrett
                long nextBoard = setMyMan(i);

                nextBoard = changePlayerIfNecessary(nextBoard, i);
                nextBoard = getBoardWithNewPhase(nextBoard);

                if( myPlayerOnBoard + 1 >= 9 ) {
                    setMoveAction(nextBoard);
                }

                nextBoards[boardPointer++] = nextBoard;
            }
        }

        // Mark the end
        nextBoards[boardPointer] = MAGIC_NO_BOARD;
        return nextBoards;
    }
}
