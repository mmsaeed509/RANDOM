#include <bits/stdc++.h>

using namespace std;

int main() {

    int num1, reverseNum=0, rem, aliceResult, bobResult;

    ostringstream newInt;
    string temp;
    cin>>num1;

    if (to_string(num1).length() == 1){

        newInt << std::setfill('0') << std::setw(3) << num1 << std::endl;
        temp = move(newInt).str();
        reverse(temp.begin(), temp.end());
        num1 = stoi(temp);

    }

    else if (to_string(num1).length() == 2){

        newInt << std::setfill('0') << std::setw(3) << num1 << std::endl;
        temp = move(newInt).str();
        reverse(temp.begin(), temp.end());
        num1 = stoi(temp);

    }
    else if (to_string(num1).length() == 3) {

        while(num1!=0)
        {
            rem=num1%10;
            reverseNum=reverseNum*10+rem;
            num1/=10;
        }

    }


    aliceResult = pow(num1,reverseNum);
    bobResult = pow(reverseNum,num1);

    if (aliceResult > bobResult)
        cout<<"Alice";
    else if (bobResult > aliceResult)
        cout<<"Bob";
    else if (bobResult == aliceResult)
        cout<<"Draw";

    return 0;
}
