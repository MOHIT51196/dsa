package graph.floyd_warshall;

import java.util.Arrays;

/*
 *  finding shortest paths in a directed weighted graph 
 *  with positive or negative edge weights
 */
public class FloydWarshall {
    final static int INF = 9999;

    public void floydWarshall(int[][] graph){
        int[][] dist = Arrays.copyOf(graph, graph.length);

        for (int i = 0; i < dist.length; i++) {
            for (int j = 0; j < dist.length; j++) {
                for (int k = 0; k < dist.length; k++) {
                    dist[i][j] = Math.min(dist[i][j] , dist[i][k] + dist[k][j]);
                }
            }
        }

        print(dist);
    }

    private void print(int[][] graph){
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                System.out.print(graph[i][j]);
                if(j < graph.length - 1) System.out.print(" | ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        FloydWarshall fw = new FloydWarshall();
        int graph[][] = { { 0, 3, INF, 5 }, { 2, 0, INF, 4 }, { INF, 1, 0, INF }, { INF, INF, 2, 0 } };
        fw.floydWarshall(graph);
    }
}
