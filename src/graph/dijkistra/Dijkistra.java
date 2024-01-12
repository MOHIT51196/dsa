package graph.dijkistra;

import java.util.*;

/*
* Finding shortest paths from source to all vertices 
* 
* Drawbacks
* 1. It cannot handle -ve weights (Use Bellman Ford)
* 1. It cannot handle -ve weights cycles
*/
class Dest {
    int node;
    int cost;
}
public class Dijkistra {
    Map<Integer, List<Dest>> adjacency = new LinkedHashMap<>();
    Map<Integer, Integer> distances = new LinkedHashMap<>();
    Map<Integer, Boolean> visited = new LinkedHashMap<>();

    public Dijkistra(){
        for(int node : adjacency.keySet()) {
            distances.put(node, Integer.MAX_VALUE);
            visited.put(node, false);
        }
    }

    public void dijkistra(int root){
        // serve the closest first
        PriorityQueue<Integer> que = new PriorityQueue<>((e1, e2) -> distances.get(e1) - distances.get(e2));
        distances.put(root, 0);
        que.add(root);

        while(!que.isEmpty()){
            int node = que.poll();
            if(visited.getOrDefault(node, false)) continue;
            visited.put(node, true);
            for(Dest destNode : adjacency.get(node)){
                if(distances.get(node) + destNode.cost < distances.get(destNode.node)){
                    distances.put(destNode.node, distances.get(node) + destNode.cost);
                    que.add(destNode.node);
                }
            }
        }
    }
}
