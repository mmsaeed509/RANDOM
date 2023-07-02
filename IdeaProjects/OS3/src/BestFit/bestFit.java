/**
 * Name:-                         ID:-
 *        Mahmoud Mohamed Said         20180261
 *        Sama Sameh hassan            20190246
 *        Mahmoud Ramadan Sayed        20180252
 **/

package BestFit;
public class bestFit {

    public void bestFit(int partition[], int m, int process[], int n)
    {
        int allocation[] = new int[m];
        for (int i = 0; i < allocation.length; i++)
            allocation[i] = -1;

        for (int i=0; i<n; i++)
        {
            int  best= -1;
            for (int j=0; j<m; j++)
            {
                if (partition[j] >= process[i])
                {
                    if (best == -1)
                        best = j;
                    else if (partition[best] > partition[j])
                        best = j;
                }
            }

            if (best != -1)
            {

                allocation[i] = best;
                partition[best]=process[i];

            }
        }


        for (int i = 0; i <m ; i++)
        {
            System.out.print("partition" + (i) +'('+partition[i]+"KB)"+ "=>");
            if (allocation[i] != -1)
                System.out.print("process"+(allocation[i]+1));
            else
                System.out.print("external fragment");
            System.out.println();
        }
    }

}
