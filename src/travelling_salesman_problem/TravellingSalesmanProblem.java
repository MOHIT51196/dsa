package travelling_salesman_problem;

public class TravellingSalesmanProblem {


    private int minHamiltonianCycle(int[][] dist, int bitmask, int pos){
        final int n = dist.length;
        final int ALL_VISITED = (1<<n) - 1; // 10000 - 1 = 1111

        if(bitmask == ALL_VISITED) return dist[pos][0];

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if((bitmask & (1<<i) ) == 0) {
                min = Math.min(min, dist[pos][i] + minHamiltonianCycle(dist, bitmask | (1<<i), i));
            }
        }

        return min;
    }

    public void tsp() {
        int[][] dist = {
                {0, 20, 42, 25},
                {20, 0, 30, 34},
                {42, 30, 0, 10},
                {25, 34, 10, 0}
        };
        int cost = minHamiltonianCycle(dist, 1, 0);
        System.out.println("Min Hamiltonian Cycle = " + cost);
    }

    public static void main(String[] args) {
        TravellingSalesmanProblem salesmanProblem = new TravellingSalesmanProblem();
        salesmanProblem.tsp();
    }
}
