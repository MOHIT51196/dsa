package dsu;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class QuickUnion implements DSU {

    Map<Integer, Integer> parents = new LinkedHashMap<>();

    public QuickUnion(Set<Integer> values){
        for(int value : values) parents.put(value, value);
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
        parents.put(parent1, parent2);
    }

    public static void main(String[] args) {
        QuickUnion qf = new QuickUnion(Set.of(1, 3, 4, 7, 10, 6));


        qf.union(10, 7);
        System.out.println("Connected (7, 10) = " + qf.isConnected(7, 10));

        qf.union(1, 3);
        System.out.println("Parent of 1 = " + qf.find(1));

        qf.union(4, 6);
        System.out.println("Connected (3, 4) = " + qf.isConnected(3, 4));

        qf.union(3, 6);
        System.out.println("Connected (3, 4) = " + qf.isConnected(3, 4));
        System.out.println("Connected (1, 6) = " + qf.isConnected(1, 6));
        System.out.println("Connected (1, 7) = " + qf.isConnected(1, 7));
        System.out.println("Parent of 1 = " + qf.find(1));


        qf.union(4, 7);
        System.out.println("Connected (1, 7) = " + qf.isConnected(1, 7));

        System.out.println("Parent of 7 = " + qf.find(7));
        System.out.println("Parent of 1 = " + qf.find(1));
        System.out.println("Parent of 10 = " + qf.find(10));



    }

}
