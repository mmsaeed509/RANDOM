/**
 * Name:-                         ID:-
 *        Mahmoud Mohamed Said         20180261
 *        Sama Sameh hassan            20190246
 *        Mahmoud Ramadan Sayed        20180252
 **/

package BestFit;

public class Main {

    public static void main(String[] args) {

        bestFit best = new bestFit();

        int partition[] = {90, 20, 5, 30, 120,80};
        int process[] = {15, 90, 30, 100};
        int m = partition.length;
        int n = process.length;

        best.bestFit(partition, m, process, n);

    }

}
