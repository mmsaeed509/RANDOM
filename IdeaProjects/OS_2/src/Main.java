/**
 *
 * Mahmoud Mohamed Said         20180261
 * Mahmoud Ramadan Sayed        20180252
 * Sama Sameh hassan            20190246
 *
 * **/

/* import lib/pkg */
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Priority priorityObj = new Priority();
        SJF sjfObj = new SJF();
        RoundRobin round = new RoundRobin();
        SRTF srtf_2 = new SRTF();
        int number_of_process;
        int[][] matrix = new int[4][6];
        Scanner scanner = new Scanner(System.in);
        int number_of_technique;
        boolean test = true;
        while (test) {

            System.out.println( COLORS.PURPLE_FOREGROUND + "                                                             " + COLORS.RESET_COLOR );
            System.out.println( COLORS.PURPLE_FOREGROUND + " [*] Enter the number of Technique                           " + COLORS.RESET_COLOR );
            System.out.println( COLORS.PURPLE_FOREGROUND + " [+] 1 - Priority Scheduling Technique                       " + COLORS.RESET_COLOR );
            System.out.println( COLORS.PURPLE_FOREGROUND + " [+] 2 - Shortest- Job First Scheduling Technique            " + COLORS.RESET_COLOR );
            System.out.println( COLORS.PURPLE_FOREGROUND + " [+] 3 - Shortest-Remaining Time First Scheduling Technique  " + COLORS.RESET_COLOR );
            System.out.println( COLORS.PURPLE_FOREGROUND + " [+] 4 - The Round Robin scheduling Technique                " + COLORS.RESET_COLOR );
            System.out.println( COLORS.PURPLE_FOREGROUND + " [+] 5 - The Preemptive Round Robin scheduling Technique     " + COLORS.RESET_COLOR );
            System.out.println( COLORS.PURPLE_FOREGROUND + " [✘] 6 - Exit                                                " + COLORS.RESET_COLOR );
            System.out.println( COLORS.PURPLE_FOREGROUND + "                                                             " + COLORS.RESET_COLOR );

            number_of_technique = scanner.nextInt();

            switch (number_of_technique) {

                case 1:

                    System.out.println( COLORS.CYAN_FOREGROUND + " [+] Enter number of Process: " + COLORS.RESET_COLOR );
                    number_of_process = scanner.nextInt();
                    String process[] = new String[number_of_process];

                    for (int i = 0; i < number_of_process; i++) {

                        process[i] = "PROCESS" + (i + 1);

                    }

                    System.out.println(Arrays.toString(process));

                    System.out.print( COLORS.CYAN_FOREGROUND + " [+] Enter Burst Time for " + number_of_process + " process: " + COLORS.RESET_COLOR);

                    int burstTime[] = new int[number_of_process];
                    for (int i = 0; i < number_of_process; i++) {
                        burstTime[i] = scanner.nextInt();
                    }

                    System.out.println(Arrays.toString(burstTime));

                    System.out.print("Enter Priority for " + number_of_process + " process: ");

                    int priority[] = new int[number_of_process];
                    for (int i = 0; i < number_of_process; i++) {
                        priority[i] = scanner.nextInt();
                    }

                    System.out.println(Arrays.toString(priority));

                    priorityObj.SORT(number_of_process, priority, burstTime, process);

                    int TAT[] = new int[number_of_process + 1];
                    int waitingTime[] = new int[number_of_process + 1];

                    priorityObj.CALCULATIONS(number_of_process, TAT, burstTime, waitingTime);

                    priorityObj.STARVATION(number_of_process, priority);

                    int total_WT = 0;
                    int total_TAT = 0;
                    double avg_WT;
                    double avg_TAT;

                    System.out.println("Process     BT      WT        TAT");
                    for (int i = 0; i < number_of_process; i++) {

                        System.out.println(process[i] + "          " + burstTime[i] + "       " + waitingTime[i] + "         " + (TAT[i]));
                        total_TAT += (waitingTime[i] + burstTime[i]);
                        total_WT += waitingTime[i];

                    }

                    avg_WT = total_WT / (double) number_of_process;
                    avg_TAT = total_TAT / (double) number_of_process;
                    System.out.println("\n Average Wating Time: " + avg_WT);
                    System.out.println(" Average Turn Around Time: " + avg_TAT);
                    break;
                /**
                 * ********************************************************************************************************
                 */
                case 2:
                    System.out.println("Enter number of Process: ");
                    number_of_process = scanner.nextInt();
                    for (int i = 0; i < number_of_process; i++) {
                        matrix[i][0] = i + 1;
                        System.out.println("Enter process " + (i + 1) + " Arrival Time: ");
                        matrix[i][1] = scanner.nextInt();
                        System.out.println("Enter process " + (i + 1) + " Burst Time: ");
                        matrix[i][2] = scanner.nextInt();
                    }
                    sjfObj.sortMatrix(matrix);
                    sjfObj.calculationTime(number_of_process, matrix);
                    System.out.println("Process ID\tArrival Time\tBurst" + " Time\tWaiting Time\tTurnaround Time");
                    for (int i = 0; i < number_of_process; i++) {
                        System.out.printf("P%d\t\t%d\t\t%d\t\t%d\t\t%d " + "\n", matrix[i][0], matrix[i][1], matrix[i][2], matrix[i][4], matrix[i][5]);
                    }
                    System.out.println("Average Turnaround Time is : " + (float) (sjfObj.average_turn_around_time / number_of_process));
                    System.out.println("Average Waiting Time is : " + (float) (sjfObj.average_waiting_time / number_of_process));
                    break;
                /**
                 * ********************************************************************************************************
                 */
                case 3:
                    SRTF proc[] = {new SRTF(1, 6, 1),
                            new SRTF(2, 8, 1),
                            new SRTF(3, 7, 2),
                            new SRTF(4, 3, 3)};

                    int n = 4;
                    int srtf[] = new int[n];

                    for (int i = 0; i < n; i++) {
                        srtf[i] = i + 1;
                    }

                    int burst_time[] = new int[n];
                    System.out.println("enter burst times");
                    for (int i = 0; i < n; i++) {
                        burst_time[i] = scanner.nextInt();
                    }
                    int arrival_time[] = new int[n];
                    System.out.println("enter arrival_times ");
                    for (int i = 0; i < n; i++) {
                        arrival_time[i] = scanner.nextInt();
                    }
                    SRTF sr = new SRTF();
                    srtf_2.calculationTime(proc, proc.length);
                    break;
                /**
                 * ********************************************************************************************************
                 */
                case 4:
                    System.out.println("Enter number of Process: ");
                    number_of_process = scanner.nextInt();
                    int processes[] = new int[number_of_process];

                    for (int i = 0; i < number_of_process; i++) {
                        processes[i] = i + 1;
                    }
                    int quantum[] = {4, 3, 5, 2};

                    int burst_time_2[] = new int[number_of_process];
                    System.out.println("enter burst times");
                    for (int i = 0; i < number_of_process; i++) {
                        burst_time_2[i] = scanner.nextInt();
                    }
                    int arrival_time_2[] = new int[number_of_process];
                    System.out.println("enter arrival_times ");

                    for (int i = 0; i < number_of_process; i++) {
                        arrival_time_2[i] = scanner.nextInt();
                    }
                    round.print(processes, number_of_process, burst_time_2, quantum, arrival_time_2);
                    break;
                /**
                 * ********************************************************************************************************
                 */
                case 5:
                    Process p1 = new Process("P1", 0, 17, 4, 4);
                    Process p2 = new Process("P2", 3, 6, 3, 9);
                    Process p3 = new Process("P3", 4, 10, 5, 3);
                    Process p4 = new Process("P4", 29, 4, 2, 8);
                    Process[] arr = {p1, p2, p3, p4};
                    PreemptiveRR RR = new PreemptiveRR(arr);
                    RR.Main();
                    break;

                case 6:
                    test = false;
                    break;
            }

        }


    }

}
