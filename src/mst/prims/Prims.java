package mst.prims;

import mst.Edge;

import java.util.*;

class Dest{
    int node;
    int cost;

    public Dest(int node, int cost) {
        this.node = node;
        this.cost = cost;
    }
}

public class Prims {
    /*
    cost
    visited
    parents
     */
    private Map<Integer, Integer> costs = new TreeMap<>(); // k
    private Map<Integer, Boolean> visited = new LinkedHashMap<>(); // mst
    private Map<Integer, Integer> parents = new LinkedHashMap<>(); // parents


    public int prims(List<Edge> graph, int startNode){
        Map<Integer, List<Dest>> adjacency = createAdjacency(graph);
        for (int node : adjacency.keySet()) {
            costs.put(node, Integer.MAX_VALUE);
            visited.put(node, false);
            parents.put(node, -1);
        }

        // choose first node
        costs.put(startNode, 0);
        visited.put(startNode, true);
        parents.put(startNode, -1);

        for(int i = 0; i<adjacency.size(); i++){
            int minCostNode = getMinCostNode();
            visited.put(minCostNode, true);

            for(Dest dest: adjacency.get(minCostNode)){
                if(costs.get(dest.node) > dest.cost){
                    costs.put(dest.node, dest.cost);
                    parents.put(dest.node, minCostNode);
                }
            }

        }

        int totalCost = 0;
        for (int cost :costs.values()) totalCost += cost;
        return totalCost;
    }

    private int getMinCostNode(){
        int minCost = -1;
        int minCostNode = -1;
        for(Map.Entry<Integer, Integer> e : costs.entrySet() ){
            if(minCost > e.getValue() && !visited.get(e.getKey())) {
                minCostNode = e.getKey();
                minCost = e.getValue();
            }
        }
        return minCostNode;
    }

    private Map<Integer, List<Dest>> createAdjacency(List<Edge> graph){
        Map<Integer, List<Dest>> adjacency = new LinkedHashMap<>();
        for(Edge e : graph){
            if(adjacency.containsKey(e.src)){
                adjacency.get(e.src).add(new Dest(e.dest, e.cost));
                adjacency.get(e.dest).add(new Dest(e.src, e.cost));
            }
            else {
                adjacency.put(e.src, new ArrayList<>(List.of(new Dest(e.dest, e.cost))));
                adjacency.put(e.dest, new ArrayList<>(List.of(new Dest(e.src, e.cost))));
            }
        }
        return adjacency;
    }
}
