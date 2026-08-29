import java.util.Scanner;

public class Matriz {
        public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int mat[][] = new int[n][n];
        for(int i = 0; i < m; i++){
            int nodo1 = sc.nextInt();
            int nodo2 = sc.nextInt();
            mat[nodo1][nodo2] = 1;
            mat[nodo2][nodo1] = 1;
        }
        
        for(int i = 0; i < n; i++){
            System.out.println("Del nodo i "+i+ " podemos llegar a");
            for(int j = 0; j < n; j++){
                if(mat[i][j] == 1){
                    System.out.print("nodo "+j+" ");
                }
            }
        }
    }
}