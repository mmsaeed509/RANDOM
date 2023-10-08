/**
 * ----------------------------------
 *
 * @author      : 00xWolf
 *   GitHub    : @mmsaeed509
 *   Developer : Mahmoud Mohamed
 * 﫥  Copyright : Mahmoud Mohamed
 *
 * ---------------------------------
 *
**/


public class Student {

    /*  --------------  Variables  --------------  */
    private double studentMinSalary;
    private double previousGPA;
    private String programmingInterest;
    /*  --------------  Variables  --------------  */

    /*  --------------  Constructors  --------------  */
    public Student() {}

    public Student(double minSalary, double previousGPA, String programmingInterest) {

        this.studentMinSalary = minSalary;
        this.previousGPA = previousGPA;
        this.programmingInterest = programmingInterest;

    }
    /*  --------------  Constructors  --------------  */

    /*  --------------  Setters & Getters  --------------  */
    public double getStudentMinSalary() {
        return studentMinSalary;
    }

    public void setStudentMinSalary(double minSalary) {
        this.studentMinSalary = minSalary;
    }

    public double getPreviousGPA() {
        return previousGPA;
    }

    public void setPreviousGPA(double previousGPA) {
        this.previousGPA = previousGPA;
    }

    public String getProgrammingInterest() {
        return programmingInterest;
    }

    public void setProgrammingInterest(String programmingInterest) {
        this.programmingInterest = programmingInterest;
    }
    /*  --------------  Setters & Getters  --------------  */

}
