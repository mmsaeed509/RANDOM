/**
 *
 * Mahmoud Mohamed Said         20180261
 * Mahmoud Ramadan Sayed        20180252
 * Sama Sameh hassan            20190246
 *
 * **/

/* import lib/pkg */
public class Process {

    double tempV1;
    private String name;
    private double burst_time;
    private double arrival_time;
    private int priority;
    private double quantum_time;
    double Quantum2;
    private double Factor;

    public Process() {
        this.name = "";

        this.burst_time = 0;
        this.arrival_time = 0;
        this.priority = 0;
        this.quantum_time = 0;
        this.Quantum2 = 0;
    }

    public Process(String name, int arrival_time, int burst_time, int quantum_time, int priority) {
        this.name = name;
        this.burst_time = burst_time;
        this.arrival_time = arrival_time;
        this.quantum_time = quantum_time;
        this.Quantum2 = quantum_time;
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBurst_time(double burst_time) {
        this.burst_time = burst_time;
    }

    public double getBurst_time() {
        return burst_time;
    }

    public void setArrival_time(double arrival) {
        this.arrival_time = arrival;
    }

    public double getArrival_time() {
        return arrival_time;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    public void setQuantum_time(double quantum_time) {
        this.quantum_time = quantum_time;
    }

    public double getQuantum_time() {
        return quantum_time;
    }

    public void set_Factor(double AGAT_Factor) {
        this.Factor = Factor;
    }

    public double get_Factor() {
        return Factor;
    }


}
