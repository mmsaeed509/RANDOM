/**
 * Name:-                         ID:-
 *        Mahmoud Mohamed Said         20180261
 *        Sama Sameh hassan            20190246
 *        Mahmoud Ramadan Sayed        20180252
 **/

package WorstFit;

public class Main {

    public static void main(String[] args) {

        worstFit worst_fit= new worstFit();

        int blockSize[] = {100, 500, 200, 300, 600};
        int processSize[] = {212, 417, 112, 426};
        int m = blockSize.length;
        int n = processSize.length;

        worst_fit.worstFit(blockSize, m, processSize, n);

    }

}
