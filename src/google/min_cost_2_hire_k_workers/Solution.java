package google.min_cost_2_hire_k_workers;

import java.util.PriorityQueue;

class Worker implements Comparable<Worker>{
    int quality;
    int wage;

    public Worker(int quality, int wage){
        this.wage = wage;
        this.quality = quality;
    }

    public double ratio(){
        return (double) wage / (double) quality;
    }

    public int compareTo(Worker w){
        return Double.compare(this.ratio(), w.ratio());
    }
};


public class Solution {

    private PriorityQueue<Worker> getWorkers(int[] quality, int[] wage){
        PriorityQueue<Worker> workers = new PriorityQueue<>();
        for(int i = 0; i< quality.length; i++){
            workers.add(new Worker(quality[i], wage[i]));
        }
        return workers;
    }

    public double mincostToHireWorkers(int[] quality, int[] wage, int k) {
        // sort everything via W/Q ratio in ascending order
        PriorityQueue<Worker> workers = getWorkers(quality, wage); // min heap
        PriorityQueue<Integer> qualities = new PriorityQueue<>((x1, x2) -> x2 - x1); // max heap

        double minHireCost = Integer.MAX_VALUE;
        int currCost = 0;
        while(!workers.isEmpty()){
            Worker w = workers.poll();
            qualities.add(w.quality);
            currCost += w.quality;

            if(qualities.size() > k) currCost -= qualities.poll();

            if(qualities.size() == k){
                minHireCost = Math.min(minHireCost, currCost * w.ratio());
            }
        }

        return minHireCost;
    }
}