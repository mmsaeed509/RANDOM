//#include <iostream>
#include <bits/stdc++.h>
using namespace std;

int main() {

    long long n,x;
    cin>>n>>x;
    int counter = 0;


    long long array [n];

    for (long long i = 0; i < n ; ++i) {

        cin>>array[i];

    }

    for (int i = 0; i < n ; ++i) {

        int I = i+1;
        for (int j = 0; j < n ; ++j) {

            int J = j+1;

            if (I<J){

                if (array[i] >= I){

                    if (array[j] >= J){

                        if (array[i] + array[j] <= x)
                            counter++;

                    }

                }

            }

        }

    }

    cout<<counter;

    return 0;
}
