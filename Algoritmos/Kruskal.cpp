#include<bits/stdc++.h>

using namespace std;

struct Arista{
    int w,u,v;
};

void dfs(int u, vector<vector<int>>&g, vector<bool> &vis){
    vis[u] = true;
    for(int vecino: g[u]){
        if(vis[vecino] == false){
            dfs(vecino, g, vis);
        }
    }
}
bool ciclo(vector<vector<int>> &grafo, int u, int v){
    int n = grafo.size();
    vector<bool> vis(n, 0);
    dfs(u, grafo, vis);
    if(vis[v] == true) return true;
    else return false;
}

bool f(Arista &a, Arista &b){
    return a.w < b.w;
}
signed main(){
    int n,m; cin>>n>>m;
    vector<Arista> aristas(m);
    for(int i = 0; i < m; i++){
        int u,v,w; cin>>u>>v>>w;
        aristas[i] = {w,u,v};
    }
    //de esta forma se orden un vector en c++
    sort(aristas.begin(), aristas.end(), f);
    int mst = 0;
    vector<vector<int>> grafo(n);
    for(int i = 0; i < m; i++){
        Arista aristaAct = aristas[i];
        int u = aristaAct.u;
        int v = aristaAct.v;
        int peso = aristaAct.w;
        if(ciclo(grafo, u,v)){
            //si hay ciclo quqe hacemso?
            //no hacemos nada
        }else{
            //no hay ciclo
            mst += peso;
            //poner esa arista en el grafo
            grafo[u].push_back(v);
            grafo[v].push_back(u);
        }
    }
    //System.out.print(mst)
    cout<<mst<<endl;
}