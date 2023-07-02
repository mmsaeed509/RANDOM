/**
 * Name:-                         ID:-
 *        Mahmoud Mohamed Said         20180261
 *        Sama Sameh hassan            20190246
 *        Mahmoud Ramadan Sayed        20180252
 **/


import java.util.Scanner;
import java.util.Vector;

public class Main {

    public static void main(String[] args) {

        Vector<Partition> pr  = new Vector<Partition>();
        Vector<Process> proc  = new Vector<Process>();
        MemoryManagement best = new MemoryManagement();
        Scanner input = new Scanner(System.in);

        System.out.println(COLORS.GREEN_BOLD + "[1] firstFit\n" +
                           "[2] bestFit\n" +
                           "[3] worstFit\n" +
                           "");
        System.out.print("choice: " + COLORS.RESET_COLOR);
        int x = input.nextInt();


        Partition  partition[] = {

                new Partition(0,90),
                new Partition(1,20),
                new Partition(2,5),
                new Partition(3,30),
                new Partition(4,120),
                new Partition(5,80)

        };

        Process process[] = {

                new Process(1,15),
                new Process(2,90),
                new Process(3,30),
                new Process(4,100)
        };

        for(int i=0;i<6;i++) {

            pr.add(partition[i]);

            if(i<4) {

                proc.add(process[i]);

            }

        }

        int m = partition.length;
        int n = process.length;

        best.bestFit(pr, m, proc, n,x);

    }

}