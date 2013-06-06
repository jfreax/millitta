package player.millitta.Generate;


public class GeneratorMovingPhase extends AbstractGenerator {

    public GeneratorMovingPhase(long board) {
        super(board);
    }


    public long[] getNextBoards() {
        System.out.println("Generate: Zugphase");
        boardPointer = 0;

        for( int i = 0; i < 24; i++ ) {
            // Fuer jeden meiner Spielsteine
            if( isMyMen(i) ) {
                // Teste jede Nachbarposition ...
                for(int neighbor : neighbors[i]) {
                    // ... ob sie noch frei ist.
                    if( !isMen(neighbor) ) {
                        System.out.println(boardPointer);
                        nextBoards[boardPointer++] = moveMyMen(i, neighbor);
                    }
                }
            }
        }


        nextBoards[boardPointer] = MAGIC_NO_BOARD;
        return nextBoards;
    }
}
