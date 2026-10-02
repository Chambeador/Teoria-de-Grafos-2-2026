import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;

public class BFS{
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
        int nodoInicial = 0;
        int [] capas = bfs(nodoInicial, grafo);
        for(int nivel: capas){
            System.out.print(nivel+" ");
        }
    }
    
    public static int [] bfs(int nodoI, ArrayList<Integer> [] g){
        Queue<Integer> cola = new LinkedList<>();
        cola.add(nodoI);
        int capas[] = new int [g.length];
        boolean vis[] = new boolean[g.length];
        capas[nodoI] = 0;
        vis[nodoI] = true;
        while(!cola.isEmpty()){
            int nodoCola = cola.poll();
            for(int vecino: g[nodoCola]){
                if(vis[vecino] == false){
                    vis[vecino] = true;
                    cola.add(vecino);
                    capas[vecino] = capas[nodoCola]+1;
                }
            }
        }
        return capas;
    }
}