package graph.bellman_ford;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * finding the shortest distance to all vertices from the source
 */

class Edge{
    int src;
    int dest;
    int weight;

    public Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "src=" + src +
                ", dest=" + dest +
                ", weight=" + weight +
                '}';
    }
}

public class BellmanFord {

    final static int INF = 9999;
    private List<Edge> getEdges(int[][] graph){
        final List<Edge> edges = new ArrayList<>();
        for (int i = 0; i <graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                if(graph[i][j] != INF && i != j) edges.add(new Edge(i, j, graph[i][j]));
            }
        }
//        edges.forEach(System.out::println);
        return edges;
    }

    public void bellmanFord(int[][] graph, int root){
        List<Edge> edges = getEdges(graph);

        int[] dist = new int[graph.length];
        Arrays.fill(dist, INF);
        dist[root] = 0;

        // OBJECTIVE : relaxing edges n-1 times, where n = no of vertices
        for (int i = 0; i < graph.length - 1; i++) {
            for (int j = 0; j < edges.size(); j++) {
                Edge e = edges.get(j);
                dist[e.dest] = Math.min(dist[e.dest], dist[e.src] + e.weight);
            }
        }

        // detecting negative cycles
        for (int j = 0; j < edges.size(); j++) {
            Edge e = edges.get(j);
            if(dist[e.dest] > dist[e.src] + e.weight) {
                System.out.println("Negative cycle detected in the graph");
                return;
            }
        }

        for (int i =0; i< dist.length; i++) System.out.println( root + " -> " + i + " = " + dist[i]);
    }

    public static void main(String[] args) {
        int graph[][] = { {0,   5,  INF, 10},
                {INF, 0,   3, INF},
                {INF, INF, 0,   1},
                {INF, INF, INF, 0}
        };
        BellmanFord bf = new BellmanFord();
        bf.bellmanFord(graph, 0);
    }
}
