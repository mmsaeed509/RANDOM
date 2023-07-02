package AG;
public class Main {
    public static void main(String[] args) {

        Scheduler scheduler = new Scheduler();
        scheduler.first.add(new process("p1", 17, 0,  4, 7));
        scheduler.first.add(new process("p2", 6,  2,  7, 9));
        scheduler.first.add(new process("p3", 11, 5,  3, 4));
        scheduler.first.add(new process("p4", 4,  15, 6, 6));
        scheduler.starting();

    }
}