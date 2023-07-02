/**
 *
 * Mahmoud Mohamed Said         20180261
 * Mahmoud Ramadan Sayed        20180252
 * Sama Sameh hassan            20190246
 *
 * **/

/* import lib/pkg */

public class RoundRobin {

    static void time( int PROCESS[] , int n , int BURST_TIME[] , int WAITING_TIME[] , int QUANTUM[] , int ARRIVAL_TIME[] , int TURN_AROUND_TIME[] ) {

        int NEW_BURST_TIME[];
        NEW_BURST_TIME = new int[n];

        for (int i = 0; i < n; i++) {

            NEW_BURST_TIME[i] = BURST_TIME[i];

        }

        int TIME = 0;

        while (true) {

            boolean finished = true;

            for (int i = 0; i < n; i++) {

                if (NEW_BURST_TIME[i] > 0) {

                    finished = false;

                    if (NEW_BURST_TIME[i] > QUANTUM[i]) {

                        TIME += QUANTUM[i];
                        NEW_BURST_TIME[i] -= QUANTUM[i];

                    } else {

                        TIME = TIME + NEW_BURST_TIME[i];
                        WAITING_TIME[i] = TIME - BURST_TIME[i] - ARRIVAL_TIME[i];

                        NEW_BURST_TIME[i] = 0;

                    }

                    TURN_AROUND_TIME[i] = BURST_TIME[i] + WAITING_TIME[i];

                }

            }

            if (finished == true) {

                break;

            }

        }

    }

    static void print(int PROCESS[], int n, int BURST_TIME[], int QUANTUM[], int ARRIVAL_TIME[]) {

        int WAITING_TIME[] = new int[n], TURN_AROUND_TIME[] = new int[n];
        int total_waiting_time = 0, total_turnaround_time = 0;
        time(PROCESS, n, BURST_TIME, WAITING_TIME, QUANTUM, ARRIVAL_TIME, TURN_AROUND_TIME);
        System.out.println("processes " + "burst time " + "arrival_time " + "Quantum  ");

        for (int i = 0; i < n; i++) {

            total_waiting_time = total_waiting_time + WAITING_TIME[i];
            total_turnaround_time = total_turnaround_time + TURN_AROUND_TIME[i];
            System.out.println("" + (i + 1) + "\t\t" + BURST_TIME[i] + "\t\t" + ARRIVAL_TIME[i] + "\t" + "\t" + QUANTUM[i] + "\t");

        }

        for (int i = 0; i < n; i++) {

            System.out.println("waiting time for process " + (i + 1) + "= " + WAITING_TIME[i]);
            System.out.println("turn around time for process " + (i + 1) + "= " + TURN_AROUND_TIME[i]);

        }

        System.out.println("average waiting time=" + (float) total_waiting_time / (float) n);
        System.out.println("average turn around time=" + (float) total_turnaround_time / (float) n);

    }

}
