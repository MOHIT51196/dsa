package graph.traversal;

import java.util.*;


public class BFS<T> {

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

    // using queue
    public void bfs(T root){
        Map<T, Boolean> visited = new LinkedHashMap<>();
        Queue<T> queue = new ArrayDeque<>();
        queue.add(root);
        visited.put(root, true);

        while(!queue.isEmpty()){
            T visitedNode = queue.poll();
            System.out.print(visitedNode + " --> ");

            if(adjacency.containsKey(visitedNode)){
                for (T node: adjacency.get(visitedNode)) {
                    if(!visited.getOrDefault(node, false)) {
                        queue.add(node);
                        visited.put(node, true);
                    }
                }
            }

        }
        System.out.print("*");
    }



    public static void main(String[] args) {
        BFS<Integer> gt = new BFS<>();
        gt.addEdge(1, 2);
        gt.addEdge(2, 3);
        gt.addEdge(2, 4);
        gt.addEdge(3, 4);
        gt.addEdge(1, 3);
        gt.addEdge(4, 5);
        gt.addEdge(1, 5);

        gt.bfs(1);

    }

}
