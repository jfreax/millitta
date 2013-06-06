package player.millitta.Generate;


public class GeneratorPlacingPhase extends AbstractGenerator {

    public GeneratorPlacingPhase(long board) {
        super(board);
    }

    public long[] getNextBoards() {
        int rest = 9 - getMyMenOnBoard();

        System.out.println("Generate: Setzphase");

        if( action == SET_MAN ) { // Figur setzen
            if ( rest <= 0 ) {
                System.out.println("Something went wrong!\nGamephase says I have to set a man, but no men left to set :/");
                return nextBoards;
            }

            // Spielsteine beider Spieler zusammen.
            // Sagt aus welche Position frei und welche besetzt ist.
            long mergedBoardPoints = (board & BITS_MENS1) | ((board & BITS_MENS2) >> 24);
            for( int i = 0; i < 24; i++ ) {
                if ((mergedBoardPoints & (1L << i)) == 0) {
                    nextBoards[boardPointer++] = setMan(i);
                }
            }
        } else { // Figure rauskicken
            boolean removed = false;
            for(int i = 0; i < 24; i++) {
                if( isRemoveOppManPossible(i) ) {
                    removed = true;
                    nextBoards[boardPointer++] = removeOppMan(i);
                }
            }
            // Kein Stein konnte entfernt werden.
            // Das heißt alle Steine des Gegners sind in einer Muehle.
            // Also kann ich jeden beliebigen Stein rausschmeissen.
            if( !removed ) {
                for(int i = 0; i < 24; i++) {
                    if( isOppMen(i) ) {
                        nextBoards[boardPointer++] = removeOppMan(i);
                    }
                }
            }

        }

        // Mark the end
        nextBoards[boardPointer] = MAGIC_NO_BOARD;
        return nextBoards;
    }
}
