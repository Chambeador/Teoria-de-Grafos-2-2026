import java.util.ArrayList;
import java.util.Scanner;

public class ListaAdy {
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
        
        for(int nodo = 0; nodo < n; nodo++){
            for(int vecino: grafo[nodo]){
                System.out.println(nodo+" --> "+vecino);
            }
        }
    }
}