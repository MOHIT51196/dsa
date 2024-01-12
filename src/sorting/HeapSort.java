package sorting;

import java.util.List;

public class HeapSort implements Sort{

    private int leftChild(int pos) { return 2 * pos + 1; }
    private int rightChild(int pos) { return 2 * pos + 2; }
    private int parent(int pos) { return (pos - 1) / 2; }

    private void swap(List<Integer> arr, int pos1, int pos2){
        int temp = arr.get(pos1);
        arr.set(pos1, arr.get(pos2));
        arr.set(pos2, temp);
    }
    private void heapify(List<Integer> arr, int pos, int end){
        if(pos > end) return;

        int max = pos;
        int left = leftChild(pos);
        int right = rightChild(pos);

        if(left <= end && arr.get(left) > arr.get(max)) max = left;
        if(right <= end && arr.get(right) > arr.get(max)) max = right;

        if(max != pos){
            swap(arr, max, pos);
            heapify(arr, max, end);
        }
    }

    @Override
    public void sort(List<Integer> arr) {
        // create heap
        for (int i = arr.size()/2 -1; i>=0;  i--) heapify(arr, i, arr.size() - 1);

        for(int end = arr.size() - 1; end > 0; end-- ){
            swap(arr, 0, end );
            heapify(arr, 0, end - 1);
        }

    }
    public void show(List<Integer> heap){
        System.out.println("========= HEAP ==========");
        for (int i = 0; i <= (heap.size()/2) -1; i++) {
            System.out.println("\nNode = " + heap.get(i));
            if(leftChild(i) < heap.size()) System.out.println("Left Child = " + heap.get(leftChild(i)));
            if(rightChild(i) < heap.size()) System.out.println("Right Child = " + heap.get(rightChild(i)));
        }
    }

    public static void main(String[] args) {
        Initializer.init(new HeapSort());
    }

}
