/**
 *
 * Mahmoud Mohamed Said         20180261
 * Mahmoud Ramadan Sayed        20180252
 * Sama Sameh hassan            20190246
 *
 * **/

/* import lib/pkg */
import java.util.Arrays;
import java.util.Comparator;

/* Short job first */

public class SJF {

    static int[][] matrix = new int[4][6];
    static float average_waiting_time=0, average_turn_around_time=0;
    static void sortMatrix (int[][] matrix){
        Arrays.sort(matrix, new Comparator<int[]>() {
            @Override
            public int compare(int[] first, int[] second) {
                if(first[1] > second[1]) return 1;
                else return -1;
            }
        });
    }

    static void calculationTime(int number_of_process, int[][] matrix)
    {
        int temp, value = 0;
        matrix[0][3] = matrix[0][1] + matrix[0][2];/* calculation completion Time */
        matrix[0][5] = matrix[0][3] -  matrix[0][1];/* calculation Turnaround Time */
        matrix[0][4] = matrix[0][5] -  matrix[0][2];/* calculation waiting time */

        for (int i = 1; i < number_of_process; i++) {

            temp = matrix[i - 1][3];
            int low = matrix[i][2];

            for (int j = i; j < number_of_process; j++) {

                if (temp >= matrix[j][1] && low >= matrix[j][2]) {

                    low = matrix[j][2];
                    value = j;

                }

            }

            matrix[value][3] = temp + matrix[value][2];
            matrix[value][5] = matrix[value][3] - matrix[value][1];
            matrix[value][4] = matrix[value][5] - matrix[value][2];

            for (int k = 0; k < 6; k++) {

                int tem = matrix[value][k];
                matrix[value][k] = matrix[i][k];
                matrix[i][k] = tem;

            }

        }

        for(int i=0;i<number_of_process;i++) {

            average_waiting_time+= matrix[i][4];
            average_turn_around_time+= matrix[0][5];

        }

    }

}
