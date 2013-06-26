package player.millitta.EA;


import player.millitta.Constants;

import java.util.*;

public class Individual {
    Random random;
    private static final double max = 16.f;

    private List<Integer> crossOverIds = new LinkedList<Integer>(Arrays.asList(0, 1, 2, 3, 4, 5));

    public double[] dna = Constants.Weighting[0];


    public Individual() {
        this(false);
    }


    public Individual(double dna[]) {
        random = new Random(System.nanoTime());
        this.dna = dna.clone();
    }


    public Individual(boolean uninitialized) {
        random = new Random(System.nanoTime());

        if( !uninitialized ) {
            for( int i = 0; i < dna.length; i++ ) {
                dna[i] = random.nextDouble() * this.max;
            }
        }
    }


    public void mutate( int rate ) {
        for( int i = random.nextInt(rate); i >= 0; i-- ) {
            dna[i] *= random.nextDouble() + 0.5f;
        }
    }

    public Individual crossover( Individual partner ) {
        Individual child = new Individual(true);

        Collections.shuffle(crossOverIds);
        int pivot = random.nextInt(6);

        for( int i = 0; i < pivot; i++ ) {
            child.dna[i] = this.dna[i];
        }
        for( int i = pivot; i < dna.length; i++ ) {
            child.dna[i] = partner.dna[i];
        }

        return child;
    }
}


