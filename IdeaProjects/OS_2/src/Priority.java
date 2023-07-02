/**
 *
 * Mahmoud Mohamed Said         20180261
 * Mahmoud Ramadan Sayed        20180252
 * Sama Sameh hassan            20190246
 *
 * **/

/* import lib/pkg */

public class Priority {

    /* Sorting process and burst time by priority */
    static void SORT( int NUMBER_OF_PROCESS , int PRIORITY[] , int BURST_TIME[] , String PROCESS[] ) {

        int temp;
        String temp2;

        for (int i = 0; i < NUMBER_OF_PROCESS - 1; i++) {

            for (int j = 0; j < NUMBER_OF_PROCESS - 1; j++) {

                if (PRIORITY[j] > PRIORITY[j + 1]) {

                    temp = PRIORITY[j];
                    PRIORITY[j] = PRIORITY[j + 1];
                    PRIORITY[j + 1] = temp;

                    temp = BURST_TIME[j];
                    BURST_TIME[j] = BURST_TIME[j + 1];
                    BURST_TIME[j + 1] = temp;

                    temp2 = PROCESS[j];
                    PROCESS[j] = PROCESS[j + 1];
                    PROCESS[j + 1] = temp2;

                }

            }

        }

    }

    static void CALCULATIONS(int NUMBER_OF_PROCESS,int TAT[],int BURST_TIME[],int WAITING_TIME[]) {

        /* Calculate Waiting Time and Turn Around Time */
        for (int i = 0; i < NUMBER_OF_PROCESS; i++) {

            TAT[i] = (BURST_TIME[i] + 1 ) + WAITING_TIME[i];
            WAITING_TIME[i + 1] = TAT[i];

        }

    }

    static void STARVATION(int NUMBER_OF_PROCESS,int PRIORITY[]) {

        /* solve the starvation problem by aging */
        for (int i = 0; i < NUMBER_OF_PROCESS; i++) {

            if (PRIORITY[i] >= 30) {

                PRIORITY[i] -= 1;
                i++;

            }

        }

    }



}
