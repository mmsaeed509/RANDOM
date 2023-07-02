
import java.util.Scanner;
import java.util.Vector;
public class MemoryManagement {
    //
    void bestFit(Vector<Partition> partition, int m, Vector<Process> process, int n,int type)
    {
        //m # of partition , k 0-->5
        int k=m-1;
        //loop on # of the process n
        for (int i=0; i<n; i++)
        {// if partation empty
            int  best= -1;
            //loop on # of the partition n*k  *process
            for (int j=0; j<partition.size(); j++)
            {//partition can fit process or no && if it has a process
                ///
                if (partition.get(j).getPartSize() >= process.get(i).getProcSize() && partition.get(j).currentP==null)
                {
                    // if it is the first partition I found

                    //remove the if  to be first
                    if (best == -1)
                        best = j;
                    // if founded part, check  the smallest  to take
                    // if we wer worst  <//
                    //if we wer best  >//

                    // comment else if  to be first Fit, the best
//    else if (partition.get(best).getPartSize() > partition.get(j).getPartSize())
//           best = j;
                    switch(type)
                    {
                        case 2:
                            if (partition.get(best).getPartSize() > partition.get(j).getPartSize())
                                best = j;

                            break;
                        case 3:
                            if (partition.get(best).getPartSize() < partition.get(j).getPartSize())
                                best = j;

                            break;
                        default:
                            break;
                    }
                }
            }
            //  best = j;

// it founded partition
            if (best != -1)
            {

                // // it founded partition , flag false
                process.get(i).flag=false;
                //allocat process
                partition.get(best).currentP=process.get(i);
                //partition[m+1] = partition[best]-process[i];
                //cheak if take the wholl of partition or not
                if(process.get(i).getProcSize() < partition.get(best).getPartSize())
                {//last element name
                    // k=5 ,  need the new partition to be (K)5+1
                    //Part size partSize - ProcSize
                    Partition p = new Partition(k+1,(partition.get(best).getPartSize()- process.get(i).getProcSize()));
                    //size old part= process size
                    partition.get(best).Part_Size=process.get(i).getProcSize();
                    //new part after the best part selected
                    partition.insertElementAt(p,best+1);
                    // update the last partition
                    k+=1;
                }

            }
        }

//System.out.println("total partition"+(k+1));
//loop on part if it wer external fragment(empty) or not
        for (int i = 0; i <partition.size(); i++)
        {
            if(partition.get(i).currentP!=null)
            {
                System.out.println( COLORS.GREEN_BOLD + " Partition " + partition.get(i).getPartName()+" -- "+partition.get(i).getPartSize()+" (KB) "+" Process "+ partition.get(i).currentP.getProcName() + COLORS.RESET_COLOR);


            }
            else
            {
                System.out.println( COLORS.GREEN_BOLD + " Partition " + partition.get(i).getPartName()+" -- "+partition.get(i).getPartSize()+" (KB) "+" External Fragment founded" + COLORS.RESET_COLOR);

            }

        }
//------------Compaction----------------
// if need compaction or not , if true ,need to be  compacted

        boolean comp=false;
// if process did not allocated
        int sum=0;
        Vector<Integer> arr =new Vector<Integer>();
        Vector<Process> nextproc =new Vector<Process>();
        for(int i=0;i<n;i++)
        {
            if(process.get(i).flag)
            {
                System.out.println( COLORS.RED_BOLD + "Process "+process.get(i).getProcName()+" can not be allocated" + COLORS.RESET_COLOR );
                comp=true;
                // tak all process not allocated
                nextproc.add(process.get(i));
            }

        }
        for(int i=0;i<partition.size();i++)
        {
            if(partition.get(i).currentP==null)
            {
                //total size of new part
                sum+=partition.get(i).getPartSize();
                arr.add(i);// indexes of the free .. externel frag

            }
        }
        if(arr.size()!=1)
        {
            System.out.println( COLORS.PURPLE_BOLD + "[+] Do you want to compact? 1 for yes 2 for no" + COLORS.RESET_COLOR);
            Scanner input = new Scanner(System.in);
            int x = input.nextInt();
            if(x==1)
            {

                int r=0;// which will subtract of it
                for(int i=0;i<arr.size();i++)
                {
                    //remove all embety parti
                    partition.remove(arr.get(i)-r);
                    r+=1;

                }
                Partition p =new Partition(k+1,sum);
                k+=2;
                //adding new part
                partition.add(p);
                //System.out.println(partition.size());
                bestFit(partition,k,nextproc,nextproc.size(),type);
                //

            }

        }
    }
}
