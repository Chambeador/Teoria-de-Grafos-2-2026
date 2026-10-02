#include<bits/stdc++.h>
#define int long long
using namespace std;


struct Blossom {
    int n, m;
    vector<int> mt, p, d, bl;
    vector<vector<int>> b, g;

    Blossom(int n):n(n),m(n+n/2+5),mt(n,-1),p(m),d(m),bl(m),b(m),
        g(m,vector<int>(m,-1)) {}

    void add(int u,int v){g[u][v]=u;g[v][u]=v;}
    void mat(int u,int v){g[u][v]=g[v][u]=-1,mt[u]=v,mt[v]=u;}

    vector<int> tr(int x){
        vector<int> v;
        while(1){
            while(bl[x]!=x)x=bl[x];
            if(!v.empty()&&v.back()==x)break;
            v.push_back(x),x=p[x];
        }
        return v;
    }

    void con(int c,vector<int>&x,vector<int>&y){
        b[c].clear();
        int r=x.back();
        while(!x.empty()&&!y.empty()&&x.back()==y.back())
            r=x.back(),x.pop_back(),y.pop_back();

        b[c].push_back(r);
        b[c].insert(b[c].end(),x.rbegin(),x.rend());
        b[c].insert(b[c].end(),y.begin(),y.end());

        for(int i=0;i<=c;i++)g[c][i]=g[i][c]=-1;
        for(int z:b[c]){
            bl[z]=c;
            for(int i=0;i<c;i++)if(g[z][i]!=-1)
                g[c][i]=z,g[i][c]=g[i][z];
        }
    }

    vector<int> lift(vector<int>&v){
        vector<int> a;
        while(v.size()>1){
            int z=v.back();v.pop_back();
            if(z<n){a.push_back(z);continue;}

            int w=v.back(),k=b[z].size();
            int i=a.size()%2==0?
                find(b[z].begin(),b[z].end(),g[z][w])-b[z].begin():0;
            int j=a.size()%2?
                find(b[z].begin(),b[z].end(),g[z][a.back()])-b[z].begin():0;
            int di=(a.size()%2==0?i%2:j%2==0)?1:k-1;

            while(i!=j)
                v.push_back(b[z][i]),i=(i+di)%k;
            v.push_back(b[z][i]);
        }
        return a;
    }

    int solve(){
        for(int ans=0;;ans++){
            fill(d.begin(),d.end(),0);
            queue<int>q;

            for(int i=0;i<m;i++)bl[i]=i;
            for(int i=0;i<n;i++)if(mt[i]<0)
                q.push(i),p[i]=i,d[i]=1;

            int c=n;
            bool ok=0;

            while(!q.empty()&&!ok){
                int x=q.front();q.pop();
                if(bl[x]!=x)continue;

                for(int y=0;y<c;y++)if(bl[y]==y&&g[x][y]!=-1){
                    if(!d[y]){
                        p[y]=x,d[y]=2;
                        p[mt[y]]=y,d[mt[y]]=1;
                        q.push(mt[y]);
                    }
                    else if(d[y]==1){
                        auto X=tr(x),Y=tr(y);

                        if(X.back()==Y.back()){
                            con(c,X,Y);
                            q.push(c);
                            p[c]=p[b[c][0]],d[c]=1,c++;
                        }
                        else{
                            ok=1;
                            X.insert(X.begin(),y);
                            Y.insert(Y.begin(),x);

                            auto A=lift(X),C=lift(Y);
                            A.insert(A.end(),C.rbegin(),C.rend());

                            for(int i=0;i<A.size();i+=2){
                                mat(A[i],A[i+1]);
                                if(i+2<A.size())add(A[i+1],A[i+2]);
                            }
                        }
                        break;
                    }
                }
            }

            if(!ok)return ans;
        }
    }
    vector<pair<int,int>> getMatching(){
        solve();
        vector<pair<int,int>> res;
        for(int u=0;u<n;u++)
            if(mt[u]!=-1 && u<mt[u])
                res.push_back({u,mt[u]});
        return res;
    }
};


signed main(){
    int n,m; cin>>n>>m;
    Blossom bs(n);
    for(int i = 0; i < m; i++){
        int u,v; cin>>u>>v;
        bs.add(u,v);
    }
    vector<pair<int, int>> match = bs.getMatching();
    cout<<match.size()<<endl;
    for(auto [x, y]: match){
        cout<<x<<" "<<y<<endl;
    }
}