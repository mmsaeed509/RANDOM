import java.util.ArrayList;
import java.util.List;

public class ProgramRecommendation {

    private final List<DegreeProgram> programs;

    public ProgramRecommendation() {

        programs = new ArrayList<>();

        /* Add degree programs criteria to the list */
        programs.add(new DegreeProgram("Software Engineering", "SE", 5000, 3.0, "Low", "Software Engineer", 3.5));
        programs.add(new DegreeProgram("CyberSecurity", "CY", 7000, 3.5, "Very High", "CyberSecurity Engineer", 4.0));
        programs.add(new DegreeProgram("Computer Science and AI", "CSAI", 5000, 3.0, "High", "Software Developer", 3.5));
        programs.add(new DegreeProgram("Computer and Network Engineering", "CNE", 5000, 3.0, "High", "Network Engineer", 3.5));
        programs.add(new DegreeProgram("Data Science", "DS", 6000, 3.5, "Medium", "Data Analyst/Data Scientist", 3.5));

    }

    /**
     * Recommends a list of degree programs based on the given student's criteria.
     *
     * @param student The student for whom degree programs are recommended.
     * @return A list of recommended degree programs.
     */
    public List<DegreeProgram> recommendPrograms(Student student) {

        List<DegreeProgram> recommendedPrograms = new ArrayList<>();

        /*
        * Iterate through all available degree programs
        * to check if matches the criteria */
        for (DegreeProgram program : programs) {

            /*
            * Check if the program matches the criteria for recommendation
            * At first, check out the minimum salary
            * */
            if (program.getMinSalary() >= student.getStudentMinSalary()) {

                /* Then, check out the minimum required GPA */
                if (program.getMinGPA() <= student.getPreviousGPA()) {

                    /* Then, check out student's Programming interest */
                    if (program.getProgrammingInterest().equalsIgnoreCase(student.getProgrammingInterest())) {

                        /*
                        * add the program to the recommended programs list
                        * that match the criteria
                        * */
                        recommendedPrograms.add(program);

                    }

                }

            }

        }

        /* Return the list of recommended programs */
        return recommendedPrograms;
    }


}
