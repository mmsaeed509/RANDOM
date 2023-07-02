/**
 * Name:-                         ID:-
 *        Mahmoud Mohamed Said         20180261
 *        Sama Sameh hassan            20190246
 *        Mahmoud Ramadan Sayed        20180252
 **/

import BestFit.*;
import FirstFit.*;
import WorstFit.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String choice;
        Scanner scanner = new Scanner(System.in);

        System.out.println( COLORS.BLACK_BACKGROUND + COLORS.GREEN_FOREGROUND + " ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ " + COLORS.RESET_COLOR);
        System.out.println( COLORS.BLACK_BACKGROUND + COLORS.GREEN_FOREGROUND + " ┃  " + COLORS.RED_BOLD + "[*] Memory Management Algorithms " + COLORS.GREEN_FOREGROUND + " ┃ " + COLORS.RESET_COLOR);
        System.out.println( COLORS.BLACK_BACKGROUND + COLORS.GREEN_FOREGROUND + " ┃                                    ┃ " + COLORS.RESET_COLOR);
        System.out.println( COLORS.BLACK_BACKGROUND + COLORS.GREEN_FOREGROUND + " ┃ " + COLORS.PURPLE_BOLD + " [1] First Fit                    " + COLORS.GREEN_FOREGROUND + " ┃ " + COLORS.RESET_COLOR);
        System.out.println( COLORS.BLACK_BACKGROUND + COLORS.GREEN_FOREGROUND + " ┃ " + COLORS.PURPLE_BOLD + " [2] Best Fit                     " + COLORS.GREEN_FOREGROUND + " ┃ " + COLORS.RESET_COLOR);
        System.out.println( COLORS.BLACK_BACKGROUND + COLORS.GREEN_FOREGROUND + " ┃ " + COLORS.PURPLE_BOLD + " [3] Worst Fit                    " + COLORS.GREEN_FOREGROUND + " ┃ " + COLORS.RESET_COLOR);
        System.out.println( COLORS.BLACK_BACKGROUND + COLORS.GREEN_FOREGROUND + " ┃ " + COLORS.PURPLE_BOLD + " [4] Exit                         " + COLORS.GREEN_FOREGROUND + " ┃ " + COLORS.RESET_COLOR);
        System.out.println( COLORS.BLACK_BACKGROUND + COLORS.GREEN_FOREGROUND + " ┃                                    ┃ " + COLORS.RESET_COLOR);
        System.out.println( COLORS.BLACK_BACKGROUND + COLORS.GREEN_FOREGROUND + " ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛ " + COLORS.RESET_COLOR);
        System.out.println( COLORS.BLACK_BACKGROUND + COLORS.GREEN_FOREGROUND + "                                        " + COLORS.RESET_COLOR);


        while (true){

            System.out.print( COLORS.BLACK_BACKGROUND + COLORS.GREEN_FOREGROUND + "    [+] Your choice: " + "                  ");
            choice = scanner.nextLine();

            if ( choice.equals("1") ){

                firstFit first_fit = new firstFit();

                int blockSize[] = {100, 500, 200, 300, 600};
                int processSize[] = {212, 417, 112, 426};
                int m = blockSize.length;
                int n = processSize.length;

                first_fit.firstFit(blockSize, m, processSize, n);


            } else if ( choice.equals("2") ) {

                bestFit best_fit = new bestFit();

                int partition[] = {90, 20, 5, 30, 120,80};
                int process[] = {15, 90, 30, 100};
                int m = partition.length;
                int n = process.length;
                best_fit.bestFit(partition, m, process, n);

            } else if ( choice.equals("3") ) {

                worstFit worst_fit= new worstFit();

                int blockSize[] = {100, 500, 200, 300, 600};
                int processSize[] = {212, 417, 112, 426};
                int m = blockSize.length;
                int n = processSize.length;

                worst_fit.worstFit(blockSize, m, processSize, n);

            } else if ( choice.equals("4") ) {

                System.out.println( COLORS.BLACK_BACKGROUND + COLORS.RED_BOLD +  "                                        " + COLORS.RESET_COLOR);
                System.out.println( COLORS.BLACK_BACKGROUND + COLORS.RED_BOLD +  "    [*] Closing                         " + COLORS.RESET_COLOR);
                System.out.println( COLORS.BLACK_BACKGROUND + COLORS.RED_BOLD +  "                                        " + COLORS.RESET_COLOR);
                System.exit(0);

            }else {

                System.out.println( COLORS.BLACK_BACKGROUND + COLORS.RED_BOLD +  "                                        " + COLORS.RESET_COLOR);
                System.out.println( COLORS.BLACK_BACKGROUND + COLORS.RED_BOLD +  "    [*] Wrong Choice                    " + COLORS.RESET_COLOR);
                System.out.println( COLORS.BLACK_BACKGROUND + COLORS.RED_BOLD +  "                                        " + COLORS.RESET_COLOR);

            }

        }


    }

}