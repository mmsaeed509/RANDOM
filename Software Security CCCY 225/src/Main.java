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


import java.util.Scanner;
import java.util.List;

public class Main {

    /* Validate minSalary input */
    public static double validateMinSalary(double minSalary){

        Scanner scanner = new Scanner(System.in);
        boolean validMinSalary = false;

        while (!validMinSalary) {

            System.out.print(COLORS.PURPLE_BOLD + "\nEnter minimum acceptable industry salary (SAR): " + COLORS.RESET_COLOR);

            if (scanner.hasNextDouble()) {

                minSalary = scanner.nextDouble();
                validMinSalary = true;

            } else {

                System.out.println(COLORS.RED_BOLD + "    Invalid input. Please enter a valid double for salary." + COLORS.RESET_COLOR);
                scanner.nextLine(); /* Consume the invalid input */

            }

        }

        return minSalary;

    }

    /* Validate previousGPA input */
    public static double validatePreviousGPA(double previousGPA){

        Scanner scanner = new Scanner(System.in);
        boolean validPreviousGPA = false;

        while (!validPreviousGPA) {

            System.out.print(COLORS.PURPLE_BOLD + "Enter previous GPA: " + COLORS.RESET_COLOR);

            if (scanner.hasNextDouble()) {

                previousGPA = scanner.nextDouble();
                validPreviousGPA = true;

            } else {

                System.out.println(COLORS.RED_BOLD + "    Invalid input. Please enter a valid double for GPA." + COLORS.RESET_COLOR);
                scanner.nextLine(); /* Consume the invalid input */

            }
        }

        scanner.nextLine(); /* Consume the newline character */

        return previousGPA;

    }

    /* Validate programmingInterest input */
    public static String validateProgrammingInterest(String programmingInterest) {

        Scanner scanner = new Scanner(System.in);
        boolean validProgrammingInterest = false;

        while (!validProgrammingInterest) {

            System.out.print(COLORS.PURPLE_BOLD + "Enter computer programming interest (Low, Medium, High, Very High): " + COLORS.RESET_COLOR);
            programmingInterest = scanner.nextLine().trim();

            if (!programmingInterest.isEmpty() && programmingInterest.matches("^(?i)(Low|Medium|High|Very High)$")) {

                validProgrammingInterest = true;

            } else {

                System.out.println(COLORS.RED_BOLD + "    Invalid input. Please enter a valid programming interest." + COLORS.RESET_COLOR);

            }

        }

        return programmingInterest;

    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        /*  --------------  Initialize Variables  --------------  */
        double minSalary = 0.0, previousGPA = 0.0;
        String programmingInterest = "";

        minSalary = validateMinSalary(minSalary); /* get minimum salary */

        previousGPA = validatePreviousGPA(previousGPA); /* get minimum Previous GPA */

        programmingInterest = validateProgrammingInterest(programmingInterest); /* get Programming Interest */

        /* Create a student object and Initialize with user input */
        Student student = new Student(minSalary, previousGPA, programmingInterest);

        /*
        * Calculate required study hours
        * assuming 1 point GPA = 1 hour of study
        * 1 point GPA = 60 minutes of study
        * */
        int studyHours = (int) (previousGPA * 60);

        /*
        * Create an instance of the ProgramRecommendation class
        * to Get recommended programs based on user input
        *  */
        ProgramRecommendation programRecommendation = new ProgramRecommendation();
        List<DegreeProgram> recommendedPrograms = programRecommendation.recommendPrograms(student);

        /* Display recommended programs */
        if (recommendedPrograms.isEmpty()) {

            System.out.println(COLORS.RED_BOLD + "\nNo programs match your criteria.\n" + COLORS.RESET_COLOR);

        } else {

            System.out.println(COLORS.GREEN_BOLD + "\nRecommended Program:-");

            for (DegreeProgram program : recommendedPrograms) {

                System.out.println(COLORS.PURPLE_BOLD + "    Program: " + COLORS.CYAN_BOLD + program.getName());
                System.out.println(COLORS.PURPLE_BOLD + "    Category: " + COLORS.CYAN_BOLD + program.getCategory());
                System.out.println(COLORS.PURPLE_BOLD + "    Minimum Salary: " + COLORS.CYAN_BOLD + program.getMinSalary() + " SAR");
                System.out.println(COLORS.PURPLE_BOLD + "    Minimum GPA: " + COLORS.CYAN_BOLD + program.getMinGPA());
                System.out.println(COLORS.PURPLE_BOLD + "    Programming Interest: " + COLORS.CYAN_BOLD + program.getProgrammingInterest());
                System.out.println(COLORS.PURPLE_BOLD + "    Job Category: " + COLORS.CYAN_BOLD + program.getJobCategory());
                System.out.println(COLORS.PURPLE_BOLD + "    Acceptable GPA after Degree: " + COLORS.CYAN_BOLD + program.getAcceptableGPA());
                System.out.println(COLORS.PURPLE_BOLD + "    Required Study Hours: " + COLORS.CYAN_BOLD + studyHours + COLORS.PURPLE_BOLD +
                                                            " Minutes per day OR " + COLORS.CYAN_BOLD + studyHours/60 + " Hours per day" );

                System.out.println();

            }

        }

        scanner.close();

    }

}
