package player.millitta.Generate;


public class GeneratorMovingPhase extends AbstractGenerator {

    public GeneratorMovingPhase(long board) {
        super(board);
    }


    public long[] getNextBoards() {
        System.out.println("Generate: Zugphase");
        boardPointer = 0;

        System.out.println("Move man");
        for( int i = 0; i < 24; i++ ) {
            // Fuer jeden meiner Spielsteine
            System.out.println("isMyMen(i) -> " + i + ": " + isMyMen(i));
            if( isMyMen(i) ) {
                // Teste jede Nachbarposition ...
                System.out.println("  neighbors[i] -> " + neighbors[i]);
                for(int neighbor : neighbors[i]) {
                    // ... ob sie noch frei ist.
                    System.out.println("    !isMen(neighbor) -> " + neighbor + ": " + !isMen(neighbor));
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
