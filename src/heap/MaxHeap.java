package heap;

import java.util.ArrayList;
import java.util.List;

/*
* POINTS TO REMEMBER
* leaf nodes range = floor(n/2) to n-1
* non leaf node range = 0 to (n/2 -1)
* left child = 2n + 1
* right child = 2n + 2
* parent = (n - 1)/2
 */
public class MaxHeap {
    private List<Integer> heap = new ArrayList<>();


    private int parent(int pos) { return (pos - 1)/2; }

    private int leftChild(int pos) { return 2 * pos + 1; }

    private int rightChild(int pos) { return 2 * pos + 2; }

    private void swap(int pos1, int pos2){
        int temp = heap.get(pos1);
        heap.set(pos1, heap.get(pos2));
        heap.set(pos2, temp);
    }

    private void heapify(int pos){
        if(pos > heap.size() - 1) return;

        int max = pos;
        int left = leftChild(pos);
        int right = rightChild(pos);
        if(left < heap.size() && heap.get(max) < heap.get(left)) max = left;
        if(right < heap.size() && heap.get(max) < heap.get(right)) max = right;

        if(max != pos) {
            swap(pos, max);
            heapify(max);
        }
    }

    public void insert(int value){
        heap.add(value);
        int n = heap.size() - 1;
        while(n >= 0 && heap.get(n) > heap.get(parent(n))){
            swap(n, parent(n));
            n = parent(n);
        }
    }

    public void show(){
        System.out.println("========= HEAP ==========");
        for (int i = 0; i <= (heap.size()/2) -1; i++) {
            System.out.println("\nNode = " + heap.get(i));
            if(leftChild(i) < heap.size()) System.out.println("Left Child = " + heap.get(leftChild(i)));
            if(rightChild(i) < heap.size()) System.out.println("Right Child = " + heap.get(rightChild(i)));
        }
    }

    public int getMax(){ return heap.get(0); }

    public int extractMax() {
        int max = heap.get(0);
        swap(0, heap.size() - 1);
        heap.remove(heap.size() -1);
        heapify(0);
        return max;
    }

    public static void main(String[] args) {
        var input = List.of(687, -94, -492, 162, 148, 782, 756, -806, -481, -501);
        MaxHeap heap = new MaxHeap();
        input.forEach(heap::insert);
        heap.show();

        heap.insert(100);
        heap.show();

        System.out.println("Max = " + heap.extractMax());
        heap.show();
    }



}
