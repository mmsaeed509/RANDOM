
public class Partition{
    int Partition_Num; //partition name
    int Part_Size; //partition size
    Process currentP=null;
    public Partition(int partName, int partSize){
        this.Partition_Num = partName;
        this.Part_Size = partSize;
    }


    public int getPartName(){
        return this.Partition_Num;
    }

    public int getPartSize(){
        return this.Part_Size;
    }
}