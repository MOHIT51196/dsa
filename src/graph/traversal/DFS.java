package graph.traversal;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DFS<T> {


    private final Map<T, List<T>> adjacency = new LinkedHashMap<>();


    public void addEdge(T src, T dest){
        if(!adjacency.containsKey(src)) {
            List<T> list = new ArrayList<>();
            list.add(dest);
            adjacency.put(src, list);
        }
        else {
            adjacency.get(src).add(dest);
        }
        System.out.println("List of " + src + " = " + adjacency.get(src));

    }
    private void dfsTraversal(T root, Map<T, Boolean> visited){
        System.out.print(root + " --> ");
        visited.put(root, true);

        if(adjacency.containsKey(root)) {
            for (T node : adjacency.get(root)) {
                if(!visited.getOrDefault(node, false)) dfsTraversal(node, visited);
            }
        }
    }

    // using stack
    public void dfs(T root){
        Map<T, Boolean> visited = new LinkedHashMap<>();
        dfsTraversal(root, visited);
    }


    public static void main(String[] args) {
        DFS<Integer> gt = new DFS<>();
        gt.addEdge(1, 2);
        gt.addEdge(2, 3);
        gt.addEdge(2, 4);
        gt.addEdge(3, 4);
        gt.addEdge(1, 3);
        gt.addEdge(4, 5);
        gt.addEdge(1, 5);

        gt.dfs(1);

    }
}
