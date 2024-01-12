package google.min_flights_to_destination;

import java.util.*;

public class Solution {

    Map<Integer, List<Integer>> prepareMap(List<List<Integer>> flights) {
        Map<Integer, List<Integer>> map = new LinkedHashMap<>();

        for(List<Integer> flight : flights){
            int start = flight.get(0);
            int end = flight.get(1);

            List<Integer> dests = map.getOrDefault(start, new ArrayList<>());
            dests.add(end);
            map.put(start, dests);

            dests = map.getOrDefault(end, new ArrayList<>());
            dests.add(start);
            map.put(end, dests);

        }
        return map;
    }

    class Node{
        int country;
        List<Integer> path;

        public Node(int country, List<Integer> path) {
            this.country = country;
            this.path = path;
        }
    }

    List<Integer> leastStops(List<List<Integer>> flights, List<Integer> banned,  int start, int end){
        Map<Integer, List<Integer>> map = prepareMap(flights);
        System.out.println(map);
        Map<Integer, Boolean> seen = new LinkedHashMap<>();

        Queue<Node> que = new ArrayDeque<>();
        que.add(new Node(start, new ArrayList<>()));
        seen.put(start, true);

        while (!que.isEmpty()){
            Node node = que.poll();

            for(int dest : map.getOrDefault(node.country, List.of())){

                if(dest == end ){
                    List<Integer> path = node.path;
                    path.add(node.country);
                    path.add(dest);
                    return path;
                }

                if(!banned.contains(dest) && !seen.getOrDefault(dest, false)){
                    seen.put(dest, true);
                    List<Integer> path = new ArrayList<>(node.path);
                    path.add(node.country);
                    System.out.println(node.country + "->" + dest + " : path = " + path);
                    que.add(new Node(dest, path));
                }
            }
        }
        return List.of();

    }


    public static void main(String[] args) {
        List<List<Integer>> inp = new ArrayList<>();
        inp.add(List.of(0, 1));
        inp.add(List.of(0, 3));
        inp.add(List.of(1, 2));
        inp.add(List.of(2, 4));
        inp.add(List.of(3, 5));
        inp.add(List.of(4, 5));
        inp.add(List.of(1, 5));

        Solution s = new Solution();
        System.out.println(s.leastStops(inp, List.of(3), 1, 5 ));
    }
}
