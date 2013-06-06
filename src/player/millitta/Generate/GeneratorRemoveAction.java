package player.millitta.Generate;


public class GeneratorRemoveAction extends AbstractGenerator {

    public GeneratorRemoveAction(long board) {
        super(board);
    }


    public long[] getNextBoards() {
        System.out.println("Generate: Remove");
        boardPointer = 0;

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

        nextBoards[boardPointer] = MAGIC_NO_BOARD;
        return nextBoards;
    }
}
