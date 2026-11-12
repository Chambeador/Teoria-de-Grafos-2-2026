import java.util.*;
public class TopSort{
        public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        ArrayList<Integer> []grafo = new ArrayList[n];
        for(int i = 0; i < n; i++){
            grafo[i] = new ArrayList<>();
        }
        
        int [] gradoEn = new int[n];
        for(int i = 0; i < m; i++){
            int nodo1 = sc.nextInt(); nodo1--;
            int nodo2 = sc.nextInt(); nodo2--;
            grafo[nodo1].add(nodo2);
            gradoEn[nodo2] += 1;
        }
        
        Queue<Integer> cola = new LinkedList<Integer>();
        for(int nodo = 0; nodo < n; nodo++){
            if(gradoEn[nodo] == 0){
                //tenemos un nodo inicial
                cola.add(nodo);
            }
        }
        ArrayList<Integer> orden = new ArrayList<>();
        while(!cola.isEmpty()){
            int nodo = cola.poll(); //saca el primero de la cosa
            orden.add(nodo); //lo meto a la respuesta
            for(int vecino: grafo[nodo]){
                gradoEn[vecino]--;
                if(gradoEn[vecino] == 0){
                    //materia liberada meterlo a la cola
                    cola.add(vecino);
                }
            }
        }
        
        //debemos de imprimirlo
        for(int nodo: orden){
            System.out.print((nodo+1)+" ");
        }
    }   
}