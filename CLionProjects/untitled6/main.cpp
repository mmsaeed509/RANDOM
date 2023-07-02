#include <iostream>
#include <string>

using namespace std;

int main() {

    int numberOfStudents{0}, numberOfSQuizzes {0};
    cout<<"No. Students : ";
    cin>>numberOfStudents;
    cout<<"No. Quizzes : ";
    cin>>numberOfSQuizzes;

    string studentsNames[numberOfStudents];/* collect students names */
    string quizzesNames[numberOfSQuizzes];/* collect quizzes names */
    int studentMarks[numberOfSQuizzes];/* collect students marks */
    int quizMarks[numberOfSQuizzes];/* collect marks per quiz */

    int studentMarksAverage[numberOfSQuizzes];
    int quizMarksAverage[numberOfSQuizzes];
    int sumAverageStudent{0};
    int sumAverageQuiz{0};

    /* take students names */
    for (int i = 0; i < numberOfStudents; ++i) {

        cout<<"Student's Name No. "<<i+1<<" : ";
        cin>>studentsNames[i];

    }

    /* take Quizzes marks */
    for (int i = 0; i <numberOfSQuizzes ; ++i) {

        cout<<"Quiz Name No. "<<i+1<<" : ";
        cin>>quizzesNames[i];

    }

    /* take students marks */
    for (int i = 0; i < numberOfStudents; ++i) {

        for (int j = 0; j < numberOfSQuizzes; ++j) {

            cout<<"student's Name : "<<studentsNames[i]<<" || Quiz Name : "<<quizzesNames[j]<<" || Mark = ";
            cin>>studentMarks[j];
            sumAverageStudent+=studentMarks[j];
            quizMarks[j]=j;
            sumAverageQuiz+=quizMarks[j];

        }

        studentMarksAverage[i] = sumAverageStudent;
        sumAverageStudent = 0;

        quizMarksAverage[i]=;



    }


    for (int i = 0; i <numberOfStudents ; ++i) {

        cout<<"\n________________________________________\n";
        cout<<"Student Name : "<<studentsNames[i]<<" || Average Name = "<<studentMarksAverage[i]/numberOfSQuizzes<<"\n";
        cout<<"________________________________________";


    }

    return 0;
}
