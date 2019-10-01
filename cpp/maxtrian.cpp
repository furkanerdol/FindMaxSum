#include <bits/stdc++.h>
    using namespace std;

    map<string,int> sets;

    int path(int a[][1000],int n,int i,int j){
        int tempo;
        if(i==n||j==n)
            return 0;

        string temp = to_string(i).append(to_string(j));
        bool isin = sets.find(temp)!=sets.end();


        if(isin){
            auto x = sets.find(temp);
            return x->second;
            }


        tempo = (temp,a[i][j]+max(path(a,n,i+1,j),path(a,n,i+1,j+1)));

        sets.insert(pair<string,int>(temp,tempo));
     


        return tempo;
        auto y = sets.find("00");
        return y->second;
    }

    int main() {
        int i,t,n,j,k;
        int a[1000][1000];
        cin>>t;
        for(i=0;i<t;i++)
        {
            sets.clear();
            cin>>n;
            for(k=0;k<n;k++)
                for(j=0;j<k+1;j++)
                    cin>>a[k][j];
            cout<<path(a,n,0,0)<<endl;
        }



        return 0;
    }
