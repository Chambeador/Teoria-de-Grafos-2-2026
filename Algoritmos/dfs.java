import java.util.*;

public class DFSRecursivo{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        ArrayList<Integer> []grafo = new ArrayList[n];
        for(int i = 0; i < n; i++){
            grafo[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < m; i++){
            int nodo1 = sc.nextInt();
            int nodo2 = sc.nextInt();
            grafo[nodo1].add(nodo2);
            grafo[nodo2].add(nodo1);
        }
        
        boolean [] vis = new boolean[n];
        for(int i = 0; i < n; i++)vis[i] = false;
        int nodoInicial = 2;
        dfs(nodoInicial, grafo, vis);
    }
    
    public static void dfs(int nodoU, ArrayList<Integer>[] grafo, boolean [] vis){
        if(vis[nodoU] == false){
            System.out.print(nodoU+" --> ");
        }
        vis[nodoU] = true;
        for(int vecino: grafo[nodoU]){
            if(vis[vecino] == false){
                dfs(vecino, grafo, vis);
            }
        }
    }
}