#include <iostream>

using namespace std;

int main() {

    int TEMP;
    int MAX_SUM = 0 , MIN_SUM = 0 ;
    int arr[] = {3,1,5,7, 9 };




//    for (int i = 0; i < 5 ; ++i) {
//
//        for (int j = i+1; j < 5 ; ++j) {
//
//            for (int k = j+1; k < 5 ; ++k) {
//
//                for (int l = k+1; l < 5 ; ++l) {
//
//                    SUM = arr[i] + arr[j] + arr[k] + arr[l];
//
//                    if (SUM < MAX_SUM){
//
//                        MAX_SUM = SUM;
//                        MIN = MAX_SUM;
//
//                    }
//
//                }
//
//            }
//
//        }
//
//    }

    for (int i = 0; i < 5; ++i) {

        for (int j = +i; j < 5; ++j) {

            if ( arr[i] > arr[j] ){

                TEMP = arr[j];
                arr[j] = arr[i];
                arr[i] = TEMP;


            }

        }

    }

//    for (int i = 0; i <5 ; ++i) {
//
//        cout<<arr[i]<<"    ";
//
//    }

    for (int i = 0; i <4 ; ++i) {
        MIN_SUM+=arr[i];
    }

    for (int i = 1; i <=4 ; ++i) {
        MAX_SUM+=arr[i];
    }


    cout<<"\nMAX SUM = "<<MAX_SUM<<"    MIN_SUM = "<<MIN_SUM<<endl;

    cout<<MIN_SUM<<MAX_SUM<<endl;

    return 0;
}
