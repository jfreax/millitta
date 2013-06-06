package player.millitta.Generate;



public class GeneratorMovingPhase extends AbstractGenerator {

    public GeneratorMovingPhase(long board) {
        super(board);
    }


    public long[] getNextBoards() {
        int rest = 9 - getMyMenOnBoard(board_);

        System.out.println("Generate: Setzphase");

        if( action == SET_MAN ) { // Setzphase + Figur setzen
            if ( rest <= 0 ) {
                System.out.println("Something went wrong!\nGamephase says I have to set a man, but no men left to set :/");
                return nextBoards;
            }

            long mergedBoardPoints = (board_ & BITS_MENS1) | ((board_ & BITS_MENS2) >> 24);
            for( int i = 0; i < 24; i++ ) {
                if ((mergedBoardPoints & (1L << i)) == 0) {
                    nextBoards[boardPointer++] = setMan(board_, i);
                }
            }
        } else { // Setzphase + Figure rauskicken
            System.out.print("Kick it! ");
            for(int i = 0; i <= 24; i++) {
                System.out.println(i + " -> " + isRemoveOppManPossible(board_, i));
                if( isRemoveOppManPossible(board_, i) ) {
                    nextBoards[boardPointer++] = removeOppMan(board_, i);
                }
                // TODO was ist wenn alle Steine des Gegners in einer Mühle sind? (hint: dann kann jeder gekickt werden!)
            }

        }

        nextBoards[boardPointer] = MAGIC_NO_BOARD;
        return nextBoards;
    }
}
