/**
 * Name: Youssef Ahmed Bahlol
 * ID: 20216120
 * */

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        ArrayList<String> courseGrades = new ArrayList<>();

        courseGrades.add("A+");
        courseGrades.add("C+");
        courseGrades.add("F");
        courseGrades.add("D");
        courseGrades.add("A");
        courseGrades.add("A");

        Student student = new Student("Youssef Ahmed Bahlol", 20216120, courseGrades);
        student.printTranscript();

    }
}
