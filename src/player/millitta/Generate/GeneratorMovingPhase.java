package player.millitta.Generate;


import player.millitta.LookupTable;

public class GeneratorMovingPhase extends AbstractGenerator {

    public GeneratorMovingPhase(long board) {
        super(board);
    }


    public long[] getNextBoards() {
        boardPointer = 0;

        for (int i = 0; i < 24; i++) {
            // Fuer jeden meiner Spielsteine
            if (isMyMen(i)) {
                // Teste jede Nachbarposition ...
                for (int neighbor : LookupTable.neighbors[i]) {
                    // ... ob sie noch frei ist.
                    if (!isMen(neighbor)) {
                        long nextBoard = moveMyMen(i, neighbor);

                        // Wenn das jetzt eine geschlossene Muehle ist,
                        // dann bin ich immer noch an der Reihe,
                        // ansonsten muessen die Spieler gewechselt werden
                        if( !isMyMenInOpenMill(i) ) {
                            nextBoard = switchPlayer(nextBoard);
                        }
                        nextBoards[boardPointer++] = nextBoard;
                    }
                }
            }
        }

        nextBoards[boardPointer] = MAGIC_NO_BOARD;
        return nextBoards;
    }
}
