/**
 * Name: Youssef Ahmed Bahlol
 * ID: 20216120
 * */

import java.text.DecimalFormat;
import java.util.ArrayList;


public class Student {

    /*
     * here we reformat double values
     * we set Precision to 2
     * it means that if the value = 2.5678
     * we set the value to be = 2.57
     * */
    Double valuePrecision (Double value){

        DecimalFormat setValuePrecision = new DecimalFormat("#.##");
        return Double.parseDouble(setValuePrecision.format(value));

    }

    private String studentName;
    private int studentID;
    private ArrayList<String> studentGrades;
    double totalGrade = 0.0;

    public Student(String studentName, int studentID, ArrayList<String> studentGrades) {
        this.studentName = studentName;
        this.studentID = studentID;
        this.studentGrades = studentGrades;
    }

    /*
     *
     * To calculate your GPA, divide the total number of points earned by the total number of letter grades
     *
     * GPA Grades:-
     *   A+ = 4, A = 4, A- = 3.7
     *   B+ = 3.3, B = 3, B- = 2.7
     *   C+ = 2.3, C = 2, C- = 1.7
     *   D+ = 1.3, D = 1, D- = 0.7
     *   F = 0
     *
     * */
    public double calculateGPA() {

        double courseGrade = 0.0;

        for (int i = 0; i < studentGrades.size(); i++) {

            if (studentGrades.get(i).equalsIgnoreCase("A+") || studentGrades.get(i).equalsIgnoreCase("A") ){

                courseGrade = 4;
                totalGrade += courseGrade;
            }

            if (studentGrades.get(i).equalsIgnoreCase("A-")){

                courseGrade = 3.7;
                totalGrade += courseGrade;
            }

            if (studentGrades.get(i).equalsIgnoreCase("B+")){

                courseGrade = 3.3;
                totalGrade += courseGrade;
            }

            if (studentGrades.get(i).equalsIgnoreCase("B")){

                courseGrade = 3;
                totalGrade += courseGrade;
            }

            if (studentGrades.get(i).equalsIgnoreCase("B-")){

                courseGrade = 2.7;
                totalGrade += courseGrade;
            }

            if (studentGrades.get(i).equalsIgnoreCase("C+")){

                courseGrade = 2.3;
                totalGrade += courseGrade;
            }

            if (studentGrades.get(i).equalsIgnoreCase("C")){

                courseGrade = 2;
                totalGrade += courseGrade;
            }

            if (studentGrades.get(i).equalsIgnoreCase("C-")){

                courseGrade = 1.7;
                totalGrade += courseGrade;
            }

            if (studentGrades.get(i).equalsIgnoreCase("D+")){

                courseGrade = 1.3;
                totalGrade += courseGrade;
            }

            if (studentGrades.get(i).equalsIgnoreCase("D")){

                courseGrade = 1;
                totalGrade += courseGrade;
            }

            if (studentGrades.get(i).equalsIgnoreCase("D-")){

                courseGrade = 0.7;
                totalGrade += courseGrade;
            }

            if (studentGrades.get(i).equalsIgnoreCase("F")){

                courseGrade = 0;
                totalGrade += courseGrade;
            }

        }

        Double GPA = valuePrecision(totalGrade / studentGrades.size());

        return GPA;
    }

    public void printTranscript() {

        double GPA = calculateGPA();
        System.out.println("Name: " + studentName + ", ID: " + studentID + ", GPA: " + GPA);

    }

}
