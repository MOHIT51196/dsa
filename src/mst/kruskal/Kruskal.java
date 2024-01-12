package mst.kruskal;


import dsu.DSU;
import mst.Edge;

import java.util.*;

public class Kruskal implements DSU {
    PriorityQueue<Edge> edges = new PriorityQueue<Edge>((e1, e2) -> e1.cost - e2.cost);
    Map<Integer, Integer> parents = new LinkedHashMap<>();
    Map<Integer, Integer> sizes = new LinkedHashMap<>();

    private void make(){
        for (Edge e : edges) {
            if(!parents.containsKey(e.src)) parents.put(e.src, e.src);
            if(!parents.containsKey(e.dest)) parents.put(e.dest, e.dest);
            if(!sizes.containsKey(e.src)) sizes.put(e.src, 1);
            if(!sizes.containsKey(e.dest)) sizes.put(e.dest, 1);
        }
    }

    @Override
    public boolean isConnected(int n1, int n2) {
        return find(n1) == find(n2);
    }

    @Override
    public int find(int n) {
        return parents.get(n) == n ? n : find(parents.get(n));
    }

    @Override
    public void union(int n1, int n2) {
        int parent1 = find(n1);
        int parent2 = find(n2);
        if(parent1 == parent2) return;

        int size1 = sizes.get(n1);
        int size2 = sizes.get(n2);
        if(size1 > size2){
            parents.put(n2, n1);
            sizes.put(n1, size1 + size2);
        } else {
            parents.put(n1, n2);
            sizes.put(n2, size1 + size2);
        }
    }

    private int kruskal(List<Edge> graph){
        for (Edge e: graph) edges.add(e);

        int minCost = 0;
        make();
        while(!edges.isEmpty()){
            Edge edge = edges.poll();
            if(!isConnected(edge.src, edge.dest)) {
                union(edge.src, edge.dest);
                minCost += edge.cost;
            }
        }
        return minCost;
    }
}
