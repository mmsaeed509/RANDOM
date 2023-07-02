/**
 *
 * Mahmoud Mohamed Said         20180261
 * Mahmoud Ramadan Sayed        20180252
 * Sama Sameh hassan            20190246
 *
 * **/

/* import lib/pkg */

public class SRTF {

    public int Id;
    public int arrival_time;
    public int burst_time;
    private int length;

    public SRTF() {

        arrival_time = 0;
        burst_time = 0;
    }

    public SRTF(int pid, int bt, int art) {
        this.Id = pid;
        burst_time = bt;
        arrival_time = art;
    }

    static void find_Waiting_Time(SRTF srtf[], int n,int wt[]) {
        int complete = 0;
        int t = 0;
        int minmum = Integer.MAX_VALUE;
        int shortest = 0, finish_time;
        boolean check = false;

        int rt[] = new int[n];
        for (int i = 0; i < n; i++) {
            rt[i] = srtf[i].burst_time;
        }

        while (complete != n) {
            for (int j = 0; j < n; j++) {
                if ((srtf[j].arrival_time <= t)
                        && (rt[j] < minmum) && rt[j] > 0) {
                    minmum = rt[j];
                    shortest = j;
                    check = true;
                }
            }

            if (check == false) {
                t++;
                continue;
            }
            rt[shortest]--;
            minmum = rt[shortest];
            if (minmum == 0) {
                minmum = Integer.MAX_VALUE;
            }
            if (rt[shortest] == 0) {
                complete++;
                check = false;
                finish_time = t + 1;
                wt[shortest] = finish_time
                        - srtf[shortest].burst_time
                        - srtf[shortest].arrival_time;

                if (wt[shortest] < 0) {
                    wt[shortest] = 0;
                }
            }
            t++;
        }
    }

    static void find_Turn_AroundTime(SRTF srtf[], int n,int wt[], int tat[]) {
        for (int i = 0; i < n; i++) {
            tat[i] = srtf[i].burst_time + wt[i];
        }
    }

    static void calculationTime(SRTF srtf[], int n) {
        int wt[] = new int[n], tat[] = new int[n];
        int total_wt = 0, total_tat = 0;
        find_Waiting_Time(srtf, n, wt);
        find_Turn_AroundTime(srtf, n, wt, tat);
        System.out.println("Processes "
                + " Burst time "
                + " Waiting time "
                + " Turn around time");
        for (int i = 0; i < n; i++) {
            total_wt = total_wt + wt[i];
            total_tat = total_tat + tat[i];
            System.out.println(" " + srtf[i].Id + "\t\t"
                    + srtf[i].burst_time + "\t\t " + wt[i]
                    + "\t\t" + tat[i]);
        }

        System.out.println("Average waiting time = "
                + (float) total_wt / (float) n);
        System.out.println("Average turn around time = "
                + (float) total_tat / (float) n);
    }

}
