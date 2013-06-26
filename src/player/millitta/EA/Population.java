package player.millitta.EA;


import player.Millitta;
import player.millitta.Constants;
import player.millitta.Evaluate.Evaluate;
import player.millitta.Helper;
import player.millitta.Search.YouLostException;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Population {

    List<Individual> individuals;

    private Millitta game = new Millitta();
    private long startBoard = 2761269521531535360L;

    public Population(int size) {
        individuals = new LinkedList<Individual>();

        for( int i = 0; i < size; i++ ) {
            individuals.add(new Individual());
        }
    }

    /*
        Startet die Turnierauswahl.
        Gibt die Liste der Gewinner zurueck.
     */
    public LinkedList<Individual> tournament() {
        // Randomize
        Collections.shuffle(individuals);

        // Winner Liste fuer naechste Runde
        LinkedList<Individual> winner = new LinkedList<Individual>();

        // Immer zwei gegeneinander
        for( int i = 0; i < individuals.size()-1; i++) {
            winner.add(fight(individuals.get(i), individuals.get(i + 1)));
        }

        return winner;
    }

    /*
        Lässt zwei Individuuen gegeneinander antreten und gibt den Sieger zurueck
     */
    private Individual fight(Individual ind1, Individual ind2) {
        // Set dna
        Constants.Weighting[0] = ind1.dna;
        Constants.Weighting[1] = ind2.dna;

        long board = startBoard;

        // Maximal 200 Zuege
        for(int k = 0; k < 200; k++ ) {
            try {
                board = game.playWithoutEngine(board);
            } catch (YouLostException e) {
                System.out.println("I WILL LOSE! :(");
            }

            // Check if lost
            double fitness = (new Evaluate(board)).getFitness();
            if( fitness == -1L ) {
                if ((board & (1L << Constants.BIT_PLAYER)) != 0) {
                    return ind2;
                } else {
                    return ind1;
                }
            }
        }

        return ind1; // Unentschieden eigentlich
    }

    public void setIndividuals(List<Individual> individuals) {
        this.individuals = individuals;
    }
}
