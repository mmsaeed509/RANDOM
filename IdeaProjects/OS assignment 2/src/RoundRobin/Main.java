package RoundRobin;

import java.util.LinkedList;
import java.util.Queue;
public class Main {

    public static void main(String[] args) {

        Queue<process> queue = new LinkedList<process>();
        process p1 = new process("P1",0,24,5);
        process p2 = new process("P2",0,3,5);
        process p3 = new process("P3",0,3,5);
        queue.add(p1);
        queue.add(p2);
        queue.add(p3);
        RoundRobin obj2 = new RoundRobin(queue,4,0,3);
        obj2.algorithm_rr();
        obj2.print();

    }

}
