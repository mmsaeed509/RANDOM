#include <iostream>
#include <vector>
#include <string>

using namespace std;

void Sort(vector<int>*vec){

    int temp;
    for (int i = 0; i < vec->size(); ++i) {
        for (int j = i+1; j < vec->size(); ++j) {

            if (vec->at(i)>vec->at(j)){

                temp=vec->at(i);
                vec->at(i)=vec->at(j);
                vec->at(j)=temp;

            }

        }
    }

}

int removeDuplicated(vector<int>*vec){

    for (int i = 0; i < vec->size() ; ++i) {

        for (int j = i+1; j < vec->size(); ++j) {

            if ( vec->at(i) == vec->at(j) ){

                vec->erase(j);

            }

        }
    }

}


int main() {

            vector<int>vec;
            int size ,total, total_temp,input;
            cin>>size;
            cin>>total;
            for (int i = 0; i < size; ++i) {
                cin>>input;
                vec.push_back(input);
            }
            Sort(&vec);

            for (int i = 0; i <size ; ++i) {

                for (int j = 0; j < size; ++j) {

                    for (int k = 0; k < size ; ++k) {

                        if ( vec.at(i)*vec.at(i) == vec.at(j)*vec.at(j) + vec.at(k)*vec.at(k) ) {



                        }

                    }

                }

            }

            cout<<endl<<endl;



}
