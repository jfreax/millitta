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

                        // Wenn das jetzt eine geschlossene Muehle ist,
                        // dann bin ich immer noch an der Reihe,
                        // ansonsten muessen die Spieler gewechselt werden
                        if( !isMyMenInOpenMill(i) ) {
                            nextBoard = switchPlayer(nextBoard);
                        }
                        nextBoards[boardPointer++] = moveMyMen(i, j);
                    }
                }
            }
        }

        nextBoards[boardPointer] = MAGIC_NO_BOARD;
        return nextBoards;
    }
}
