/**
 *
 * Mahmoud Mohamed Said         20180261
 * Mahmoud Ramadan Sayed        20180252
 * Sama Sameh hassan            20190246
 *
 * **/

/* import lib/pkg */
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class PreemptiveRR {

    Process[] Processes_arr;
    int total_burst_time;
    ArrayList<Process> finalArray = new <Process>ArrayList();
    int numberOfProcess;
    double V1;
    double V2;

    public PreemptiveRR(Process[] arr_of_Processes) {
        this.Processes_arr = arr_of_Processes;
        for (int i = 0; i < arr_of_Processes.length; i++) {
            total_burst_time += arr_of_Processes[i].getBurst_time();
        }
        numberOfProcess = arr_of_Processes.length;
        for (int i = 0; i < arr_of_Processes.length; i++) {
            Process key = new Process();
            key = arr_of_Processes[i];
            int j = i - 1;
            while (j >= 0 && arr_of_Processes[j].getArrival_time() > key.getArrival_time()) {
                arr_of_Processes[j + 1] = arr_of_Processes[j];
                j = j - 1;
            }
            arr_of_Processes[j + 1] = key;
        }
        setV1();
    }

    public void setV1() {
        if (Processes_arr[Processes_arr.length - 1].getArrival_time() > 10) {
            V1 = (Processes_arr[Processes_arr.length - 1].getArrival_time() / 10);
        } else {
            V1 = 1;
        }
    }
    public double getV1() {
        return V1;
    }


    public void SetV2(double burstTime) {
        if (burstTime > 10) {
            V2 = (burstTime / 10);

        } else {
            V2 = 1;
        }
    }

    public double getV2() {
        return V2;
    }

    public ArrayList<Process> ArrayList_with_arrival_smallerThan_CurrentTime(int time) {
        ArrayList<Process> temp = new <Process>ArrayList();
        for (int i = 0; i < Processes_arr.length; i++) {
            if (Processes_arr[i].getArrival_time() <= time) {
                temp.add(Processes_arr[i]);
            }
        }
        return temp;
    }



    public void SetAGAT_Factor(Process p1) {
        double f = (10 - p1.getPriority()) + Math.ceil(p1.tempV1) + Math.ceil(p1.getBurst_time() / V2);
        p1.set_Factor(f);
    }

    public ArrayList<Process> setAGATForReadyProcess(ArrayList<Process> arraylist) {
        for (int j = 0; j < arraylist.size(); j++) {
            SetAGAT_Factor(arraylist.get(j));
        }
        return arraylist;
    }
    public int getMaxBurst(ArrayList<Process> arraylist) {
        int index = 0;
        double max = arraylist.get(0).getBurst_time();
        for (int i = 0; i < arraylist.size(); i++) {
            if (arraylist.get(i).getBurst_time() > max) {
                max = arraylist.get(i).getBurst_time();
                index = i;
            } else {
                continue;
            }
        }
        return index;
    }

    public Process Min_Factor(ArrayList<Process> arraylist) {
        Process p = new Process();
        int index = 0;
        double min = arraylist.get(0).get_Factor();
        for (int i = 0; i < arraylist.size(); i++) {
            if ((arraylist.get(i).get_Factor()< min) && (arraylist.get(i).get_Factor() != -1)) {
                min = arraylist.get(i).get_Factor();
                index = i;
            } else {
                continue;
            }
        }

        p.setArrival_time( arraylist.get(index).getArrival_time());
        p.setBurst_time( arraylist.get(index).getBurst_time());
        p.setName( arraylist.get(index).getName());
        p.setPriority( arraylist.get(index).getPriority());
        p.setQuantum_time( arraylist.get(index).getQuantum_time());
        p.Quantum2 =  arraylist.get(index).Quantum2;
        p.set_Factor( arraylist.get(index).get_Factor());
        return p;
    }

    public int indexReturn(Process p) {
        int indx2 = 0;
        for (int i = 0; i < Processes_arr.length; i++) {
            if (p.getName() == Processes_arr[i].getName()) {
                indx2 = i;
            }
        }
        return indx2;
    }
    public Queue<Process> Queue_with_arrival_smallerThan_CurrentTime(int time) {
        Queue<Process> readyQueue = new LinkedList<Process>();
        for (int i = 0; i < Processes_arr.length; i++) {
            if (Processes_arr[i].getArrival_time() <= time) {
                readyQueue.add(Processes_arr[i]);
            }
        }
        return readyQueue;
    }

    public ArrayList<Process> setV2_AGAT(ArrayList<Process> arrayList, int indx) {
        for (int j = 0; j < Processes_arr.length; j++) {
            if (j == indx) {
                SetV2(Processes_arr[j].getBurst_time());
            } else {
                continue;
            }
            if (arrayList.contains(Processes_arr[j])) {
                for (int k = 0; k < arrayList.size(); k++) {
                    if (arrayList.get(k).getName() == Processes_arr[j].getName()) {
                        arrayList.get(k).setArrival_time(Processes_arr[j].getArrival_time());
                        arrayList.get(k).setBurst_time(Processes_arr[j].getBurst_time());
                        arrayList.get(k).setName(Processes_arr[j].getName());
                        arrayList.get(k).setPriority(Processes_arr[j].getPriority());
                        arrayList.get(k).setQuantum_time(Processes_arr[j].getQuantum_time());
                        arrayList.get(k).Quantum2 = Processes_arr[j].Quantum2;
                        arrayList.get(k).set_Factor(Processes_arr[j].get_Factor());
                    }
                }
            }
        }
        arrayList = setAGATForReadyProcess(arrayList);
        return arrayList;
    }

    public Queue<Process> HandlingQueue(Queue<Process> queue, Process p) {
        if (queue.contains(p)) {
            queue.remove(p);
            Process[] arr = new Process[queue.size()];
            int i = 0;
            while (!queue.isEmpty()) {
                arr[i] = queue.poll();
                i++;
            }
            queue.add(p);
            for (int j = 0; j < queue.size(); j++) {
                queue.add(arr[j]);
            }
        }
        return queue;
    }

    public static Process[] deleteArrElement(Process[] arr, int index) {

        if (arr == null || index < 0 || index >= arr.length) {
            return arr;
        }

        Process[] Array2 = new Process[arr.length - 1];
        for (int i = 0; i < Array2.length; i++) {
            Array2[i] = new Process();
        }

        for (int i = 0, j = 0; i < arr.length; i++) {
            if (i == index) {
                continue;
            }
            Array2[j++] = arr[i];
        }

        return Array2;
    }

    public void removeElement(String name) {
        for (int i = 0; i < Processes_arr.length; i++) {
            if (Processes_arr[i].getName() == name) {
                Processes_arr = deleteArrElement(Processes_arr, i);
            } else {
                continue;
            }
        }
    }


    public void Main() {
        Queue<Process> readyQueue = new LinkedList<Process>();
        ArrayList<Process> availableProcesses = new ArrayList<Process>();
        ArrayList<Process> finalSequence = new ArrayList<Process>();
        for (int i = 0; i < Processes_arr.length; i++) {
            Processes_arr[i].tempV1 = Processes_arr[i].getArrival_time() / V1;
        }
        int completed = 0;
        int current_time = (int) Processes_arr[0].getArrival_time();
        while (completed != numberOfProcess) {
            for (int i = 0; i < Processes_arr.length; i++) {
                if (Processes_arr[i].getQuantum_time() == 0) {
                    Processes_arr[i].setQuantum_time(Processes_arr[i].getQuantum_time() + Processes_arr[i].Quantum2);
                    readyQueue.poll();
                    readyQueue.add(Processes_arr[i]);
                }
            }


            readyQueue = Queue_with_arrival_smallerThan_CurrentTime(current_time);
            availableProcesses = ArrayList_with_arrival_smallerThan_CurrentTime(current_time);
            int indx = getMaxBurst(availableProcesses);
            availableProcesses = setV2_AGAT(availableProcesses, indx);
            Process p = new Process();
            p = Min_Factor(availableProcesses);


            int period = (int) Math.round(0.4 * p.getQuantum_time());
            readyQueue = HandlingQueue(readyQueue, p);




            if (p.getBurst_time() == 0) {
                p.setQuantum_time(0);
                p.set_Factor(-1);

                int n = indexReturn(p);

                Processes_arr[n].setArrival_time(p.getArrival_time());
                Processes_arr[n].setBurst_time(p.getBurst_time());
                Processes_arr[n].setName(p.getName());
                Processes_arr[n].setPriority(p.getPriority());
                Processes_arr[n].setQuantum_time(p.getQuantum_time());
                Processes_arr[n].Quantum2 = p.Quantum2;
                Processes_arr[n].set_Factor(p.get_Factor());
                finalArray.add(p);
                removeElement(p.getName());
                completed++;

            } else if ((finalSequence.isEmpty()) || finalSequence.get(finalSequence.size() - 1).getName() != p.getName()) {//Different process will run

                p.setQuantum_time(p.getQuantum_time() - period);

                p.setBurst_time(p.getBurst_time() - period);
                p.Quantum2 -= period;
                int n2 = indexReturn(p);

                Processes_arr[n2].setArrival_time(p.getArrival_time());
                Processes_arr[n2].setBurst_time(p.getBurst_time());
                Processes_arr[n2].setName(p.getName());
                Processes_arr[n2].setPriority(p.getPriority());
                Processes_arr[n2].setQuantum_time(p.getQuantum_time());
                Processes_arr[n2].Quantum2 = p.Quantum2;
                Processes_arr[n2].set_Factor(p.get_Factor());
                current_time += period;

                finalSequence.add(p);



            } else if (finalSequence.get(finalSequence.size() - 1).getName() == p.getName()) {

                p.setBurst_time(p.getBurst_time() - 1);
                p.setQuantum_time(p.getQuantum_time() - 1);
                current_time++;
                int n3 = indexReturn(p);

                Processes_arr[n3].setArrival_time(p.getArrival_time());
                Processes_arr[n3].setBurst_time(p.getBurst_time());
                Processes_arr[n3].setName(p.getName());
                Processes_arr[n3].setPriority(p.getPriority());
                Processes_arr[n3].setQuantum_time(p.getQuantum_time());
                Processes_arr[n3].Quantum2 = p.Quantum2;
                Processes_arr[n3].set_Factor(p.get_Factor());
                finalSequence.add(p);

            }


        }
        for (int i = 0; i < finalSequence.size(); i++) {
            System.out.print(finalSequence.get(i).getName() + " ");
        }


    }

}
