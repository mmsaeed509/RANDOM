#include <bits/stdc++.h>
using namespace std;
// Driver code
int main()
{
    int n, x;
    cin>>n>>x;
    vector<int>v;
    for(int i=0;i<n;i++){
        int temp;
        cin>>temp;
        if(temp>= i+1){
            v.push_back(temp);
        }
    }
    sort(v.begin(),v.end());
    long long c=0;
    for(int i=0;i<v.size();i++){
        int start=0,end=i-1;
//        int mid=(start+end)/2;
        int mx=0;
        while(start<=end){
            int  mid=(start+end)/2;
            if(v[mid]+v[i]<=x){
                mx=max(mx,mid+1);
                start=mid+1;
            }else{
                end=mid-1;
            }

        }
        c+=mx;

    }
    cout<<c;
}