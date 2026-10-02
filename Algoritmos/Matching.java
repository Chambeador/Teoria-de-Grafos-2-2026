import java.util.*;

public class Matching{
    static class Edge {
        int u, v;
        Edge(int u, int v) {
            this.u = u;
            this.v = v;
        }
    }

    static ArrayList<Edge> aristas = new ArrayList<>();
    static ArrayList<Edge> actual = new ArrayList<>();
    static ArrayList<Edge> match = new ArrayList<>();
    static boolean[] usado;


    public static void dfs(int i){
        if (i == aristas.size()) {
            if (actual.size() > match.size()){
                //sacar una copua del mejor y guardar
            }
            return;
        }
        
        Edge e = aristas.get(i);
        int u = e.u;
        int v = e.v;
        if (!usado[u] && !usado[v]) {
            usado[u] = true;
            usado[v] = true;
            actual.add(e);
            dfs(i + 1);
            actual.remove(actual.size()-1);
            usado[u] = false;
            usado[v] = false;
        }
        dfs(i + 1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        usado = new boolean[n];
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            aristas.add(new Edge(u, v));
        }
        dfs(0);
        System.out.println(match.size());
        for (Edge e : match)
            System.out.println(e.u+" "+e.v);
    }
}