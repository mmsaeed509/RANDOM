
public class Process {
    boolean flag=true;
    int process_num; //process name
    int Process_Size; //process size

    public Process(int procName, int procSize){
        this.process_num = procName;
        this.Process_Size = procSize;
    }

    public int getProcName(){
        return this.process_num;
    }

    public int getProcSize(){
        return this.Process_Size;
    }
}