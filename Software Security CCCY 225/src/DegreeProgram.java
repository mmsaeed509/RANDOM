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


public class DegreeProgram {

    /*  --------------  Variables  --------------  */
    private String name;
    private String category;
    private double minSalary;
    private double minGPA;
    private String programmingInterest;
    private String jobCategory;
    private double acceptableGPA;
    /*  --------------  Variables  --------------  */

    /*  --------------  Constructors  --------------  */
    public DegreeProgram() {}

    public DegreeProgram(String name, String category, double minSalary, double minGPA, String programmingInterest, String jobCategory, double acceptableGPA) {

        this.name = name;
        this.category = category;
        this.minSalary = minSalary;
        this.minGPA = minGPA;
        this.programmingInterest = programmingInterest;
        this.jobCategory = jobCategory;
        this.acceptableGPA = acceptableGPA;

    }
    /*  --------------  Constructors  --------------  */

    /*  --------------  Setters & Getters  --------------  */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(double minSalary) {
        this.minSalary = minSalary;
    }

    public double getMinGPA() {
        return minGPA;
    }

    public void setMinGPA(double minGPA) {
        this.minGPA = minGPA;
    }

    public String getProgrammingInterest() {
        return programmingInterest;
    }

    public void setProgrammingInterest(String programmingInterest) {
        this.programmingInterest = programmingInterest;
    }

    public String getJobCategory() {
        return jobCategory;
    }

    public void setJobCategory(String jobCategory) {
        this.jobCategory = jobCategory;
    }

    public double getAcceptableGPA() {
        return acceptableGPA;
    }

    public void setAcceptableGPA(double acceptableGPA) {
        this.acceptableGPA = acceptableGPA;
    }
    /*  --------------  Setters & Getters  --------------  */

}
